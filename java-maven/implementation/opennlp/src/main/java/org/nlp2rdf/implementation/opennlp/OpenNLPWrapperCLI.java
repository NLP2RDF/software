package org.nlp2rdf.implementation.opennlp;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.*;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.util.Arrays.asList;

public class OpenNLPWrapperCLI {
	private static Logger log = LoggerFactory.getLogger(OpenNLPWrapperCLI.class);
	
	public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/opennlp#");
        // TODO as a courtesy to windows users
        // 
        ParameterParser.addCLIParameter(parser);
        parser.acceptsAll(asList("modelFolder"), "The folder containing the NLP models to be used").withRequiredArg().ofType(String.class).describedAs("folder");
        parser.acceptsAll(asList("language"), "A string denoting the language of the model used, usually 2 characters").withRequiredArg().ofType(String.class).describedAs("Language string");
        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options, "");
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);

            if(!nifParameters.getOptions().has("modelFolder")) {
    			log.error("No model folder specified, please specify OpenNLP model location via -modelFolder");
    			return;
    		}
            
            //customize
            OntModel model = nifParameters.getInputModel();
            
            int x = 0;
            for (ExtendedIterator<Individual> it = model.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it.hasNext(); ) {
                Individual context = it.next();
                OpenNLPWrapper w = new OpenNLPWrapper(context,nifParameters,model);
                w.processText(context, nifParameters);
                x++;
            }    
            model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
            NIFNamespaces.addNifPrefix(model);
            model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
            model.setNsPrefix("p", nifParameters.getPrefix());
            if(!nifParameters.getOptions().has("outfile")) {
				model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
			} else {
				BufferedWriter writer = new BufferedWriter(new FileWriter(nifParameters.getOptions().valueOf("outfile").toString()));
				model.write(writer, Format.toJena(nifParameters.getOutputFormat()));
				writer.close();
			}
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
