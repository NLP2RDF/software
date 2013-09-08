package org.nlp2rdf.cli;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.urischemes.RFC5147String;
import org.nlp2rdf.core.urischemes.URIScheme;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static java.util.Arrays.asList;

/**
 * User: hellmann
 * Date: 07.09.13
 */
public class CLIParameterParser {


    public static OptionParser getParser(String[] args, String prefix) throws IOException {

        OptionParser parser = new OptionParser();
        parser.acceptsAll(asList("h", "help"), "Show help.");
        parser.acceptsAll(asList("f", "informat"), "specifies input format (text,rdfxml,turtle,ntriples)").withRequiredArg().defaultsTo("turtle");
        parser.acceptsAll(asList("t", "intype"), "specifies input type (direct,file,url)").withRequiredArg().defaultsTo("file");
        parser.acceptsAll(asList("i", "input"), "the actual input, per default a file with relative path with NIF RDF in turtle").withRequiredArg();
        parser.acceptsAll(asList("o", "outformat"), "specifies output format (text, rdfxml, turtle, ntriples), text, means errors are written to stdout as log messages").withRequiredArg().defaultsTo("text");
        parser.acceptsAll(asList("outfile"), "a NIF RDF file with the result of validation as RDF, only takes effect, if outformat is 'turtle' or 'rdfxml'").withRequiredArg().ofType(File.class).describedAs("RDF file");
        parser.acceptsAll(asList("testsuite"), "for debugging the testsuite, a local turtle file, that contains the testsuite").withRequiredArg().ofType(File.class).describedAs("a .ttl file with a test suite");
        //TODO:
        parser.acceptsAll(asList("p", "prefix"), "specifies the prefix of the URIs").withRequiredArg().defaultsTo(prefix);
        parser.acceptsAll(asList("lp", "logprefix"), "specifies the prefix of the URIs").withRequiredArg().defaultsTo("text");
        parser.acceptsAll(asList("u", "urischeme"), "specifies the prefix of the URIs").withRequiredArg().defaultsTo("text");

        // parse options and display a message for the user in case of problems

        return parser;

    }

    public static OptionSet getOption(OptionParser parser, String[] args) throws IOException {
        OptionSet options = null;
        try {
            options = parser.parse(args);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Use -? to get help.");
            System.exit(0);
        }

        // print help screen
        if (options.has("h")) {
            String addHelp = "";
            die(parser, addHelp);
            // main script
        }
        return options;
    }

    public static NIFParameters parseOptions(OptionParser parser, OptionSet options) throws IOException {

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());


        //String inputtype = "file";
        String inputtype = (String) options.valueOf("t");
        String outformat = (String) options.valueOf("o");
        // String outformat = "text";
        // if (options.hasArgument("o")) {
        //    outformat = (String) options.valueOf("o");
        // }
        /* if (options.hasArgument("t")) {
            inputtype = (String) options.valueOf("t");
            if (inputtype.equals("direct")) {
                die(parser, "intype=direct (that is via command line) not implemented yet");
            }
        }*/

        String informat = "turtle";
        if (options.hasArgument("f")) {
            informat = (String) options.valueOf("f");
            if (informat.equals("json") || informat.equals("text")) {
                die(parser, "informat=json|text not implemented yet");
            }
        }

        if (options.hasArgument("i")) {
            String format = Format.toJena(informat);
            String input = (String) options.valueOf("i");
            InputStream is = null;
            try {
                if (inputtype.equals("file")) {
                    is = new FileInputStream(new File(input));
                } else if (inputtype.equals("url")) {
                    is = new URI(input).toURL().openStream();
                } else if (inputtype.equals("direct")) {
                    is = System.in;
                } else {
                    die(parser, "Option --intype=" + inputtype + " not known, use file|url");
                }
            } catch (FileNotFoundException fne) {
                fne.printStackTrace();
                die(parser, "ERROR: file not found, maybe you have to switch --intype=url, file=" + input);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                die(parser, "ERROR: malformed URL in parameter input=" + input);
            }
            model.read(is, "", format);
        } else {
            die(parser, "input parameter required");
        }
        options.valueOf("u");
        URIScheme uriScheme = new RFC5147String();

        return new NIFParameters(model, options, (String) options.valueOf("p"), (String) options.valueOf("lp"), uriScheme, null, outformat);
    }


    public static void die(OptionParser parser, String addHelp) throws IOException {
        parser.printHelpOn(System.out);
        System.out.println();
        System.out.println(addHelp);
        System.exit(0);
    }


}
