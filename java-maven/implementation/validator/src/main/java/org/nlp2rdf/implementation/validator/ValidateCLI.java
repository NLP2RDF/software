package org.nlp2rdf.implementation.validator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.*;
import org.nlp2rdf.core.vocab.RLOGOntClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * User: hellmann
 * Date: 08.09.13
 *  mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i src/test/resources/nif-erroneous-model.ttl"
 */
public class ValidateCLI {

    public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/validator#");
        ParameterParser.addCLIParameter(parser);


        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options, "");

            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);

            // enable RDFS reasoning in inputmodel
            OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, ModelFactory.createDefaultModel());
            inputModel.add(nifParameters.getInputModel());
            inputModel.createOntology(nifParameters.getPrefix());


            String outformat = nifParameters.getOutputFormat();

            OntModel outputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());

            OutputStream outputStream;
            if (options.hasArgument("outfile")) {
                File outfile = (File) options.valueOf("outfile");
                outputStream = new FileOutputStream(outfile);
            } else {
                outputStream = System.out;

            }



            switch (outformat){

                // treat them the same
                case "turtle":;
                case "rdfxml":;
                case "ntriples":{
                    outputModel.add(RDFUnitWrapper.validate(inputModel));
                    outputModel.write(outputStream, Format.toJena(outformat));
                    break;
                    }

                case "html": {
                    outputModel.add(RDFUnitWrapper.validate(inputModel));
                    //TODO add HTML option, maybe there is a better way to execute the validator now
                    break;
                    }
                case "text": {
                    outputModel.add(RDFUnitWrapper.validate(inputModel));
                    outputStream.write(outputModel.toString().getBytes());
                    break;
                    }
            }

            System.err.println(outputModel.listIndividuals(outputModel.createClass(RLOGOntClasses.Entry.getUri())).toSet().size() + " log messages found (could be debug messages, errors are displayed separately).");
            // TODO: some handling for inaccessible files or overwriting existing files

        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
