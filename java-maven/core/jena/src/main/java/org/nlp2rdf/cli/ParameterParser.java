package org.nlp2rdf.cli;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.apache.jena.riot.RiotParseException;
import org.nlp2rdf.core.*;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.urischemes.URISchemeHelper; 

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static java.util.Arrays.asList;

/**
 * User: hellmann Date: 07.09.13
 */
public class ParameterParser {

	public static OptionParser parser = null;

	public static OptionParser getParser() {
		if (parser == null)
			parser = new OptionParser();
		return parser;

	}

	public static NIFParameters CLIbefore(String[] args, OptionParser parser,
			String addHelp) throws ParameterException, IOException {
		ParameterParser.addCLIParameter(parser);
		OptionSet options = ParameterParser.getOption(parser, args);
		ParameterParser.handleHelpAndWS(options, addHelp);
		return ParameterParser.parseOptions(options, false);

	}

	public static void CLIAfter(OntModel outputModel,
			NIFParameters nifParameters) throws IOException {
		NIFNamespaces.addNifPrefix(outputModel);
		NIFNamespaces.addRLOGPrefix(outputModel);
		outputModel.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		outputModel.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
		outputModel.setNsPrefix("p", nifParameters.getPrefix());

		if (nifParameters.getOptions().has("outfile")) {
			FileWriter fw = new FileWriter((File) nifParameters.getOptions()
					.valueOf("outfile"));
			outputModel.write(fw,
					Format.toJena(nifParameters.getOutputFormat()));

		} else {

			outputModel.write(System.out,
					Format.toJena(nifParameters.getOutputFormat()));
		}

	}

	public static OptionParser getParser(String[] args, String defaultPrefix)
			throws IOException {

		// all default and required options according to:
		// http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html
		if (parser == null)
			parser = new OptionParser();
		parser.acceptsAll(asList("h", "help"), "Show help.");
		parser.acceptsAll(asList("info"), "Display implementation information.");

		// web service
		parser.acceptsAll(asList("start"),
				"Starts the Web Service, if implemented.");
		parser.acceptsAll(asList("port"),
				"If start is called, this param specifies the port number. ")
				.withRequiredArg().ofType(Integer.class).defaultsTo(8896);
		parser.acceptsAll(asList("c", "config"),
				"a string specifying the config of the component.")
				.withRequiredArg();

		// parameter that are expected to exist
		parser.acceptsAll(
				asList("f", "informat"),
				"specifies input format  as turtle, json-ld or text (or optionally as rdfxml, ntriples, html, ...)")
				.withRequiredArg().defaultsTo("turtle");
		parser.acceptsAll(asList("t", "intype"),
				"specifies input type (direct,url, file)").withRequiredArg()
				.defaultsTo("direct");
		parser.acceptsAll(
				asList("i", "input"),
				"the actual input data, retrieved (a) via stdin (intype=direct, --input - ,  NIF-CLI), (b) given directly (--input \"\", NIF-CLI) ,  via POST/GET (intype=direct, NIF-WS), via URL (intype=url) or via file (intype=file, NIF-CLI)")
				.withRequiredArg();
		parser.acceptsAll(
				asList("o", "outformat"),
				"specifies output format as turtle, json-ld or text (or optionally ntriples, rdfxml, html, etc.)")
				.withRequiredArg().defaultsTo("turtle");
		parser.acceptsAll(asList("l", "languageDetection"),
				"enables language detection feature");
		parser.acceptsAll(asList("p", "prefix"),
				"specifies the prefix of the generated URIs").withRequiredArg()
				.defaultsTo(defaultPrefix);
		parser.acceptsAll(asList("lp", "logprefix"),
				"specifies the prefix of the generated log URIs")
				.withRequiredArg()
				.defaultsTo(RLOGSLF4JBinding.defaultlogprefix);
		parser.acceptsAll(asList("u", "urischeme"),
				"specifies the syntax of the identifier of the URIs")
				.withRequiredArg().defaultsTo("RFC5147String");

		// TODO:

		// parse options and display a message for the user in case of problems

		return parser;

	}

	@Deprecated
	public static void addTestsuiteParameter(OptionParser parser) {
		parser.acceptsAll(asList("testsuite"),
				"for debugging the testsuite, a local turtle file, that contains the testsuite")
				.withRequiredArg().ofType(File.class)
				.describedAs("a .ttl file with a test suite");

	}

	/**
	 * This is a helper function that add additional parameters for CLI clients
	 * 
	 * @param parser
	 */
	public static void addCLIParameter(OptionParser parser) {
		parser.acceptsAll(asList("outfile"),
				"on CLI the output is written to the file specified via outfile")
				.withRequiredArg().ofType(File.class)
				.describedAs("output file");
	}

	public static void handleHelpAndWS(OptionSet options, String addHelp)
			throws ParameterException, IOException {
		// print help screen
		if (options.has("h")) {
			throw new ParameterException(addHelp);
		}

		// check whether web service and start, if necessary

		if (options.hasArgument("start")) {
			int portNumber = (Integer) options.valueOf("port");
			NIFParameters nifParameters = ParameterParser.parseOptions(options,
					true);
			System.err.println("Starting Web service at port " + portNumber);
			/*
			 * Server server = new Server(8080); server.start(); server.join();
			 * System.err.println("web service not implemented yet");
			 */
			System.exit(0);

		}
	}

	/**
	 * Parses the NIF options into an object, note that "start" and "port" have
	 * to be treated separately
	 *
	 * @param options
	 * @param isWebService
	 *            options are parsed from a webservices
	 * @return
	 * @throws IOException
	 * @throws ParameterException
	 */
	public static NIFParameters parseOptions(OptionSet options,
			boolean isWebService) throws IOException, ParameterException {
		OntModel model = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());

		String inputtype = (String) options.valueOf("t");
		String outformat = (String) options.valueOf("o");
		String informat = (String) options.valueOf("f");
		URIScheme uriScheme = URISchemeHelper.getInstance((String) options
				.valueOf("f"));

		/** Implementation check **/
		switch (informat) {
		case "turtle":
			break;
		case "rdfxml":
			break;
		case "text":
			break;
		default:
			throw new ParameterException("informat=" + informat
					+ " not implemented yet");
		}

		String input = (String) options.valueOf("i");
		if (isWebService && (input == null || !options.hasArgument("i"))) {
			throw new ParameterException(
					"Parameter input=$data was not set properly");
		}

		InputStream is = null;
		try {

			if (inputtype.equals("direct")) {
				if (input == null) {
					throw new ParameterException(
							"input can not be empty, on CLI use '-i -  for stdin' or curl --data-urlencode @-");
				} else if (input.equals("-")) {
					is = new BufferedInputStream(System.in);
				} else {
					if (isWebService) {
						is = new ByteArrayInputStream(input.getBytes());
					} else {
						// this is a workaround to build a more robust cli,
						// which shows mercy for forgetting the -t option
						System.err
								.println("you forgot the \"-t file\" or \"-t url\" option, but I am ok, assuming \"-t file\"");
						if (new File(input).exists()) {
							inputtype = "file";
						}
					}
				}
			}

			if (inputtype.equals("file")) {
				is = new FileInputStream(new File(input));
			} else if (inputtype.equals("url")) {
				is = new URI(input).toURL().openStream();
			} else if (inputtype.equals("direct")) {
				is = new ByteArrayInputStream(input.getBytes());
			} else {
				throw new ParameterException("Option --intype=" + inputtype
						+ " not known, use direct|file|url");
			}
		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
			throw new ParameterException(
					"ERROR: file not found, maybe you have to switch --intype=url, file="
							+ input);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new ParameterException(
					"ERROR: malformed URL in parameter input=" + input);
		}

		// case -l parameter setted, language detection will be enabled.
		if (informat.equals("text")) {
			new Text2RDF().createContextIndividual(
					(String) options.valueOf("p"), toString(is), uriScheme,
					model);

		} else {
			try {
				model.read(is, "", Format.toJena(informat));
			} catch (NullPointerException e) {
				throw new ParameterException(
						"an error has occured while reading informat="
								+ informat + ", intype=" + inputtype
								+ " and input=" + input.substring(0, 20)
								+ "...", e);
			} catch (RiotParseException rpe) {
				throw new ParameterException(
						"The RDF in the format "
								+ informat
								+ "is not well formed, please check parameter intype and informat",
						rpe);
			}
		}

		NIFParameters np = new NIFParameters(model, options,
				(String) options.valueOf("p"), (String) options.valueOf("lp"),
				uriScheme, null, outformat);
		// set additional parameters
		np.setConfig((String) options.valueOf("config"));
		return np;
	}

	public static OptionSet getOption(OptionParser parser, String[] args)
			throws IOException, ParameterException {
		OptionSet options = null;
		try {
			options = parser.parse(args);
		} catch (Exception e) {
			throw new ParameterException("Error: " + e.getMessage()
					+ ". Use -h to get help.", e);
		}

		return options;
	}

	public static void die(OptionParser parser, String addHelp)
			throws IOException {
		parser.printHelpOn(System.out);
		System.out.println();
		System.out.println(addHelp);
		System.exit(0);
	}

	public static String toString(InputStream in) throws IOException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} finally {
			in.close();
			out.close();
		}
		return out.toString();
	}

}
