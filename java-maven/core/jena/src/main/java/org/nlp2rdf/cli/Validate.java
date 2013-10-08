package org.nlp2rdf.cli;

import com.hp.hpl.jena.ontology.OntModel;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.SPARQLValidator;
import org.nlp2rdf.core.vocab.RLOGOntClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * User: hellmann
 * Date: 05.07.13
 */
public class Validate {

    public static void main(String[] args) throws IOException {

        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/validate#");
        try {
            OptionSet options = ParameterParser.getOption(parser, args);


            // print help screen
            if (options.has("h")) {
                String addHelp = "";
                throw new ParameterException(addHelp);
            }

            //check whether web service and start, if necessary
            if (options.has("start")) {
                int portNumber = (Integer) options.valueOf("port");
                System.err.println("Starting Web service at port " + portNumber);
                System.err.println("web service not implemented yet");


                System.exit(0);

            }

            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);
            String outformat = nifParameters.getOutputFormat();
            OntModel model = nifParameters.getInputModel();


            SPARQLValidator sparqlValidator = null;
            String defaultTestsuiteFile = "org/uni-leipzig/persistence/nlp2rdf/testcase/lib/nif-2.0-suite.ttl";

            if (options.hasArgument("testsuite")) {
                File ttt = (File) options.valueOf("testsuite");
                if (!ttt.exists()) {
                    ParameterParser.die(parser, "testsuite ttl file does not exist: " + ttt.getAbsolutePath());
                }
                sparqlValidator = SPARQLValidator.getInstance(ttt);
            } else {
                sparqlValidator = SPARQLValidator.getInstance(defaultTestsuiteFile);
            }
            System.err.println("NIF Validator for defaultTestsuiteFile version " + sparqlValidator.getVersion() + ", " + sparqlValidator.getTests().size() + " tests total.");


            if (outformat.equals("text")) {
                model.add(sparqlValidator.validate(model));

            } else if (outformat.equals("turtle") || outformat.equals("rdfxml") || outformat.equals("ntriples")) {
                sparqlValidator.setQuiet(true);
                model.add(sparqlValidator.validate(model));
                if (options.hasArgument("outfile")) {
                    File outfile = (File) options.valueOf("outfile");
                    model.write(new FileOutputStream(outfile), Format.toJena(outformat));
                } else {
                    model.write(System.out, Format.toJena(outformat));
                }
            } else {
                model.add(sparqlValidator.validate(model));
            }


            System.err.println(model.listIndividuals(model.createClass(RLOGOntClasses.Entry.getUri())).toSet().size() + " log messages found (could be debug messages, errors are displayed separately).");
            // TODO: some handling for inaccessible files or overwriting existing files
            //File f = (File) options.valueOf("o");

            // if plain and file option is given, redirect System.out to a file
            /*if (options.has("o") && (!options.has("f") || options.valueOf("f").equals("plain"))) {
               PrintStream printStream = new PrintStream(new FileOutputStream(f));
               System.setOut(printStream);
           } */
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
        }


    }


}

