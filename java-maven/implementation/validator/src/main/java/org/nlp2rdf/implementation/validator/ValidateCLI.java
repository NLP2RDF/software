package org.nlp2rdf.implementation.validator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.exceptions.TripleWriterException;
import org.aksw.rdfunit.io.DataWriter;
import org.aksw.rdfunit.io.HTMLResultsWriter;
import org.aksw.rdfunit.io.RDFStreamWriter;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RDFUnitWrapperForNIF;
import org.nlp2rdf.core.vocab.RLOGOntClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * User: hellmann
 * Date: 08.09.13
 * mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i src/test/resources/nif-erroneous-model.ttl"
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

            // Initialize the results models
            Model validationResults = RDFUnitWrapperForNIF.validate(inputModel);
            outputModel.add(validationResults);

            //Default writer (RDFUnit)
            DataWriter outputWriter = null;

            switch (outformat) {

                // treat them the same
                case "turtle":
                case "rdfxml":
                case "ntriples":
                    outputWriter = new RDFStreamWriter(outputStream, Format.toJena(outformat));
                    break;
                case "html": {
                    outputWriter = HTMLResultsWriter.create(TestCaseExecutionType.rlogTestCaseResult, outputStream);
                    break;
                }
                case "text": {
                    outputStream.write(outputModel.toString().getBytes());
                    break;
                }
            }

            // Write the output if outputWriter not null
            try {
                if (outputWriter != null)
                    outputWriter.write(outputModel);
            } catch (TripleWriterException e) {
                System.err.println("Cannot write to output: " + e.getMessage());
                e.printStackTrace();
            }

            System.err.println(outputModel.listIndividuals(outputModel.createClass(RLOGOntClasses.Entry.getUri())).toSet().size() + " log messages found (could be debug messages, errors are displayed separately).");
            // TODO: some handling for inaccessible files or overwriting existing files

        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }

    }
}
