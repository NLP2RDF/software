package org.nlp2rdf.implementation.spotlight;

import static java.util.Arrays.asList;

import java.io.IOException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;


public class SpotlightCLI {
	private static Logger log = LoggerFactory.getLogger(SpotlightCLI.class);
    public static void main(String[] args) throws IOException {
        OptionParser parser =  ParameterParser.getParser(args, "http://cli.nlp2rdf.org/snowball#");
        // TODO as a courtesy to windows users
        // parser.acceptsAll(asList("outfile"), "a NIF RDF file with the result of validation as RDF, only takes effect, if outformat is 'turtle' or 'rdfxml'").withRequiredArg().ofType(File.class).describedAs("RDF file");
        ParameterParser.addCLIParameter(parser);
        parser.acceptsAll(asList("confidence"), "The confidence level (0 to 1)").withRequiredArg().ofType(String.class).describedAs("confidence");
     
        
        SpotlightWrapper s = new SpotlightWrapper();
     
		try {
            OptionSet options = ParameterParser.getOption(parser, args);
				ParameterParser.handleHelpAndWS(options, "");
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);
            if(!nifParameters.getOptions().has("confidence")) {
    			log.error("No confidence level specified, please specify the confidence level via -confidence");
    			return;
    		}   

            //customize
            OntModel model = nifParameters.getInputModel();
        
            //some stats
            Monitor mon = MonitorFactory.getTimeMonitor(s.getClass().getCanonicalName()).start();
            
            int x = 0;
            for (ExtendedIterator<Individual> it = model.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it.hasNext(); ) {
                Individual context = it.next();
                s.processText(context, model, model, nifParameters);
                x++;
            }
            model.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
        
            } catch (Exception e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
            }
    }
}
