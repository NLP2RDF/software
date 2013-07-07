package org.nlp2rdf.cli;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.SPARQLValidator;

import java.io.*;
import java.net.URL;

import static java.util.Arrays.asList;

/**
 * User: hellmann
 * Date: 05.07.13
 */
public class Validate {

    public static void main(String[] args) throws IOException {

        OptionParser parser = new OptionParser();
        parser.acceptsAll(asList("h", "?", "help"), "Show help.");

        parser.acceptsAll(asList("i", "inputfile"), "a NIF RDF file, specify format with --format").withRequiredArg().ofType(File.class).describedAs("RDF file");
        parser.acceptsAll(asList("o", "outputfile"), "a NIF RDF file with the result of validation as RDFs").withRequiredArg().ofType(File.class).describedAs("RDF file");
        parser.acceptsAll(asList("u", "url"), "retrieve NIF RDF directly from the web service, e.g. 'http://example.org/demo', requires --textfile")
                .withRequiredArg().ofType(URL.class).describedAs("url");
        parser.acceptsAll(asList("t", "textfile"), "a textfile to send to the webservice for validation").withRequiredArg().describedAs("plain text file").ofType(File.class);
        parser.acceptsAll(asList("f", "format"), "specifies format (rdfxml,turtle,ntriples)").withRequiredArg().defaultsTo("rdfxml");
        parser.acceptsAll(asList("outputformat"), "specifies output format (rdfxml,turtle,ntriples)").withRequiredArg().defaultsTo("turtle");


        // parse options and display a message for the user in case of problems
        OptionSet options = null;
        try {
            options = parser.parse(args);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + ". Use -? to get help.");
            System.exit(0);
        }

        // print help screen
        if (options.has("?")) {
            parser.printHelpOn(System.out);
            String addHelp = "";
            System.out.println();
            System.out.println(addHelp);
            System.exit(0);
            // main script
        }

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        OntModel validation;


        if (options.hasArgument("i")) {
            File f = (File) options.valueOf("i");
            model.read(new FileInputStream(f), "", Format.toJena((String) options.valueOf("f")));
            validation = new SPARQLValidator().validate(model);
        } else if (options.hasArgument("u")) {
            throw new RuntimeException("Not implemented yet");
        }

        // TODO: some handling for inaccessible files or overwriting existing files
        File f = (File) options.valueOf("o");

        // if plain and file option is given, redirect System.out to a file
        if (options.has("o") && (!options.has("f") || options.valueOf("f").equals("plain"))) {
            PrintStream printStream = new PrintStream(new FileOutputStream(f));
            System.setOut(printStream);
        }

        //"( "ftp" ).withRequiredArg();

        /*parser.acceptsAll(asList("e", "endpoint"), "SPARQL endpoint URL to be used.")
                .withRequiredArg().ofType(URL.class);
        parser.acceptsAll(asList("g", "graph"),
                "URI of default graph for queries on SPARQL endpoint.").withOptionalArg()
                .ofType(URI.class);
        parser.acceptsAll(asList("r", "resource"),
                "The resource for which enrichment axioms should be suggested.").withOptionalArg().ofType(URI.class);
        parser.acceptsAll(asList("o", "output"), "Specify a file where the output can be written.")
                .withOptionalArg().ofType(File.class);
        // TODO: other interesting formats: html, manchester, sparul
        parser.acceptsAll(asList("f", "format"),
                "Format of the generated output (plain, rdf/xml, turtle, n-triples).").withOptionalArg()
                .ofType(String.class).defaultsTo("plain");
        parser.acceptsAll(asList("t", "threshold"),
                "Confidence threshold for suggestions. Set it to a value between 0 and 1.").withOptionalArg()
                .ofType(Double.class).defaultsTo(0.7);
        parser.acceptsAll(asList("l", "limit"),
                "Maximum number of returned axioms per axiom type. Set it to -1 if all axioms above the threshold should be returned.").withOptionalArg()
                .ofType(Integer.class).defaultsTo(10);
        parser.acceptsAll(asList("i", "inference"),
                "Specifies whether to use inference. If yes, the schema will be loaded into a reasoner and used for computing the scores.").withOptionalArg().ofType(Boolean.class).defaultsTo(true);
        parser.acceptsAll(asList("s", "serialize"), "Specify a file where the ontology with all axioms can be written.")
                .withRequiredArg().ofType(File.class);
        parser.acceptsAll(asList("a", "annotations"),
                "Specifies whether to save scores as annotations.").withOptionalArg().ofType(Boolean.class).defaultsTo(true);
        parser.acceptsAll(asList("chunksize"),
                "Specifies the chunk size for the query result as the approach is incrementally.").withRequiredArg().ofType(Integer.class).defaultsTo(1000);
        parser.acceptsAll(asList("maxExecutionTimeInSeconds"),
                "Specifies the max execution time for each algorithm run and each entity.").withRequiredArg().ofType(Integer.class).defaultsTo(10);
        parser.acceptsAll(asList("omitExistingAxioms"),
                "Specifies whether return only axioms which not already exist in the knowlegde base.").withOptionalArg().ofType(Boolean.class).defaultsTo(false);
        OptionSpec<String> allowedNamespacesOption = parser.accepts("ns").withRequiredArg().ofType(String.class)
                .withValuesSeparatedBy(',');


        //username and password if endpoint is protected
        parser.acceptsAll(asList("u", "username"), "Specify the username.")
                .withOptionalArg().ofType(String.class);
        parser.acceptsAll(asList("pw", "password"), "Specify the password.")
                .withOptionalArg().ofType(String.class);   */

        // parse options and display a message for the user in case of problems

        /*
        else {
            // check that endpoint was specified
            if (!options.hasArgument("endpoint")) {
                System.out.println("Please specify a SPARQL endpoint (using the -e option).");
                System.exit(0);
            }

            // create SPARQL endpoint object (check that indeed a URL was given)
            URL endpoint = null;
            try {
                endpoint = (URL) options.valueOf("endpoint");
            } catch (OptionException e) {
                System.out.println("The specified endpoint appears not be a proper URL.");
                System.exit(0);
            }
            URI graph = null;
            try {
                graph = (URI) options.valueOf("graph");
            } catch (OptionException e) {
                System.out.println("The specified graph appears not be a proper URL.");
                System.exit(0);
            }
            URI resourceURI = null;
            try {
                resourceURI = (URI) options.valueOf("resource");
            } catch (OptionException e) {
                System.out.println("The specified resource appears not be a proper URI.");
                System.exit(0);
            }
            //set credentials if needed
            if (options.has("username") && options.has("password")) {
                final String username = (String) options.valueOf("username");
                final String password = (String) options.valueOf("password");
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password.toCharArray());
                    }
                });
            }


            LinkedList<String> defaultGraphURIs = new LinkedList<String>();
            if (graph != null) {
                defaultGraphURIs.add(graph.toString());
            }
            SparqlEndpoint se = new SparqlEndpoint(endpoint, defaultGraphURIs, new LinkedList<String>());

            // sanity check that endpoint/graph returns at least one triple
            String query = "SELECT * WHERE {?s ?p ?o} LIMIT 1";
            SparqlQuery sq = new SparqlQuery(query, se);
            try {
                ResultSet q = sq.send();
                while (q.hasNext()) {
                    q.next();
                }
            } catch (QueryExceptionHTTP e) {
                System.out.println("Endpoint not reachable (check spelling).");
                System.exit(0);
            }

            // map resource to correct type
            Entity resource = null;
            if (options.valueOf("resource") != null) {
                resource = new SPARQLTasks(se).guessResourceType(resourceURI.toString(), true);
                if (resource == null) {
                    throw new IllegalArgumentException("Could not determine the type (class, object property or data property) of input resource " + options.valueOf("resource"));
                }
            }

            boolean useInference = (Boolean) options.valueOf("i");
//			boolean verbose = (Boolean) options.valueOf("v");
            double threshold = (Double) options.valueOf("t");
            int maxNrOfResults = (Integer) options.valueOf("l");
            if (maxNrOfResults == -1) {
                maxNrOfResults = Integer.MAX_VALUE;
            }

            int chunksize = (Integer) options.valueOf("chunksize");
            int maxExecutionTimeInSeconds = (Integer) options.valueOf("maxExecutionTimeInSeconds");
            boolean omitExistingAxioms = (Boolean) options.valueOf("omitExistingAxioms");

            // TODO: some handling for inaccessible files or overwriting existing files
            File f = (File) options.valueOf("o");

            // if plain and file option is given, redirect System.out to a file
            if (options.has("o") && (!options.has("f") || options.valueOf("f").equals("plain"))) {
                PrintStream printStream = new PrintStream(new FileOutputStream(f));
                System.setOut(printStream);
            }

            //extract namespaces to which the analyzed entities will be restricted
            List<String> allowedNamespaces = options.valuesOf(allowedNamespacesOption);

            Enrichment e = new Enrichment(se, resource, threshold, maxNrOfResults, useInference, false, chunksize, maxExecutionTimeInSeconds, omitExistingAxioms);
            e.setAllowedNamespaces(allowedNamespaces);
            e.start();

            SparqlEndpointKS ks = new SparqlEndpointKS(se);

            // print output in correct format
            if (options.has("f")) {
                List<AlgorithmRun> runs = e.getAlgorithmRuns();
                List<OWLAxiom> axioms = new LinkedList<OWLAxiom>();
                for (AlgorithmRun run : runs) {
                    axioms.addAll(e.toRDF(run.getAxioms(), run.getAlgorithm(), run.getParameters(), ks));
                }
                Model model = e.getModel(axioms);
                OutputStream os = options.has("o") ? new FileOutputStream((File) options.valueOf("o")) : System.out;

                if (options.valueOf("f").equals("turtle")) {
                    if (options.has("o")) {
                        model.write(new FileOutputStream(f), "TURTLE");
                    } else {
                        System.out.println("ENRICHMENT[");
                        model.write(System.out, "TURTLE");
                        System.out.println("]");
                    }
                } else if (options.valueOf("f").equals("rdf/xml")) {
                    if (options.has("o")) {
                        model.write(new FileOutputStream(f), "RDF/XML");
                    } else {
                        System.out.println("ENRICHMENT[");
                        model.write(System.out, "RDF/XML");
                        System.out.println("]");
                    }
                } else if (options.valueOf("f").equals("n-triples")) {
                    if (options.has("o")) {
                        model.write(new FileOutputStream(f), "N-TRIPLES");
                    } else {
                        System.out.println("ENRICHMENT[");
                        model.write(System.out, "N-TRIPLES");
                        System.out.println("]");
                    }
                }
            }
            //serialize ontology
            if (options.has("s")) {
                File file = (File) options.valueOf("s");
                try {
                    OWLOntology ontology = e.getGeneratedOntology(options.has("a"));
                    OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                    OWLManager.createOWLOntologyManager().saveOntology(ontology, new RDFXMLOntologyFormat(), os);
                } catch (OWLOntologyStorageException e1) {
                    throw new Error("Could not save ontology.");
                }
            }


        }  */

    }

}
