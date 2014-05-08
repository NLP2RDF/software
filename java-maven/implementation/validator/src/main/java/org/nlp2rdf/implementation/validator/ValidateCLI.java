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


/**
 * User: hellmann
 * Date: 08.09.13
 *  mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i src/test/resources/nif-erroneous-model.ttl"
 */
public class ValidateCLI {

    public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/stanfordcore#");
        // TODO as a courtesy to windows users
        // parser.acceptsAll(asList("outfile"), "a NIF RDF file with the result of validation as RDF, only takes effect, if outformat is 'turtle' or 'rdfxml'").withRequiredArg().ofType(File.class).describedAs("RDF file");
        ParameterParser.addOutFileParameter(parser);
        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options, "");
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);
            String outformat = nifParameters.getOutputFormat();
            OntModel outputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, ModelFactory.createDefaultModel());
            inputModel.add(nifParameters.getInputModel());
            inputModel.createOntology(nifParameters.getPrefix());
            nifParameters.setInputModel(inputModel);

            if (outformat.equals("text")) {
                outputModel.add(RDFUnitWrapper.validate(inputModel));

            } else if (outformat.equals("turtle") || outformat.equals("rdfxml") || outformat.equals("ntriples")) {
                //sparqlValidator.setQuiet(true);
                outputModel.add(RDFUnitWrapper.validate(inputModel));
                if (options.hasArgument("outfile")) {
                    File outfile = (File) options.valueOf("outfile");
                    outputModel.write(new FileOutputStream(outfile), Format.toJena(outformat));
                } else {
                    outputModel.write(System.out, Format.toJena(outformat));
                }
            } else {
                outputModel.add(RDFUnitWrapper.validate(inputModel));
            }


            System.err.println(outputModel.listIndividuals(outputModel.createClass(RLOGOntClasses.Entry.getUri())).toSet().size() + " log messages found (could be debug messages, errors are displayed separately).");
            // TODO: some handling for inaccessible files or overwriting existing files

        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
