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

        OptionParser parser = CLIParameterParser.getParser(args, "http://cli.nlp2rdf.org/validate#");
        OptionSet options = CLIParameterParser.getOption(parser, args);
        NIFParameters nifParameters = CLIParameterParser.parseOptions(parser, options);
        String outformat = nifParameters.getOutputFormat();
        OntModel model = nifParameters.getInputModel();


        SPARQLValidator sparqlValidator = null;
        String testsuite = "org/uni-leipzig/persistence/nlp2rdf/testcase/lib/nif-2.0-suite.ttl";
        if (options.hasArgument("testsuite")) {
            File ttt = (File) options.valueOf("testsuite");
            if (!ttt.exists()) {
                CLIParameterParser.die(parser, "testsuite ttl file does not exist: " + ttt.getAbsolutePath());
            }
            sparqlValidator = SPARQLValidator.getInstance(ttt);
        } else {
            sparqlValidator = SPARQLValidator.getInstance(testsuite);
        }
        System.err.println("NIF Validator for testsuite version " + sparqlValidator.getVersion() + ", " + sparqlValidator.getTests().size() + " tests total.");


        OntModel validation;
        if (outformat.equals("text")) {
            validation = sparqlValidator.validate(model);

        } else if (outformat.equals("turtle") || outformat.equals("rdfxml") || outformat.equals("ntriples")) {
            sparqlValidator.setQuiet(true);
            validation = sparqlValidator.validate(model);
            if (options.hasArgument("outfile")) {
                File outfile = (File) options.valueOf("outfile");
                validation.write(new FileOutputStream(outfile), Format.toJena(outformat));
            } else {
                validation.write(System.out, Format.toJena(outformat));
            }
        } else {
            validation = sparqlValidator.validate(model);
        }

        System.err.println(validation.listIndividuals(validation.createClass(RLOGOntClasses.Entry.getUri())).toSet().size() + " messages found.");
        // TODO: some handling for inaccessible files or overwriting existing files
        //File f = (File) options.valueOf("o");

        // if plain and file option is given, redirect System.out to a file
        /*if (options.has("o") && (!options.has("f") || options.valueOf("f").equals("plain"))) {
           PrintStream printStream = new PrintStream(new FileOutputStream(f));
           System.setOut(printStream);
       } */

    }


}

