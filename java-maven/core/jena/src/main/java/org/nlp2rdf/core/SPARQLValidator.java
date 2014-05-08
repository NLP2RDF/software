package org.nlp2rdf.core;


import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.DCTerms;
import org.apache.jena.riot.RiotException;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: hellmann
 * Date: 01.07.13
 *                    mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.cli.Validate" -Dexec.args="-i src/test/resources/nif-erroneous-model.ttl"
 * replaced by RDFUnitWrapper
 */
@Deprecated
public class SPARQLValidator {
    private static Logger log = LoggerFactory.getLogger(SPARQLValidator.class);

    private final OntModel testsuite;
    private final String version;
    private final List<String> tests;
    private final String sparqlPrefix;
    private final String logPrefix;

    //no logging, e.g. on CLI
    private boolean quiet = false;


    public static SPARQLValidator getInstance() {
        return getInstance("org/uni-leipzig/persistence/nlp2rdf/ontologies/testcase/lib/nif-2.0-suite.ttl");
    }

    public static SPARQLValidator getInstance(String suiteresource) {
        InputStream is = SPARQLValidator.class.getClassLoader().getResourceAsStream(suiteresource);
        // load the test cases into Jena
        OntModel testsuite = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        testsuite.read(is, "", "N3");
        return new SPARQLValidator(testsuite, RLOGSLF4JBinding.defaultlogprefix);
    }

    public static SPARQLValidator getInstance(File suiteresource) throws FileNotFoundException {
        // load the test cases
        OntModel testsuite = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        testsuite.read(new FileReader(suiteresource), "", "N3");
        return new SPARQLValidator(testsuite, RLOGSLF4JBinding.defaultlogprefix);
    }

    public SPARQLValidator(OntModel testsuite, String logPrefix) {
        this.logPrefix = logPrefix;
        this.testsuite = testsuite;
        //TODO make suite selectable
        String suiteUri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/testcases/lib/nif-2.0-suite.ttl#NIFCoreValidationSuite";
        Individual NIFCoreValidationSuite = testsuite.getIndividual(suiteUri);
        Property sp = testsuite.createProperty("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/stc#sparqlPrefix");
        version = NIFCoreValidationSuite.getPropertyValue(DCTerms.hasVersion).asLiteral().getString();
        sparqlPrefix = NIFCoreValidationSuite.getPropertyValue(sp).asLiteral().getString();


        //FIXME TODO this is strictly not correct, but working unless another testsuite is added in the owl file
        StmtIterator stmtit = testsuite.listStatements(null, testsuite.getProperty("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/stc#sparql"), (String) null);
        tests = new ArrayList<String>();
        for (; stmtit.hasNext(); ) {
            Statement s = stmtit.nextStatement();
            tests.add(sparqlPrefix + "\n" + s.getString());
        }

    }


    public Model validate(OntModel toBeValidated, String query) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        NIFNamespaces.addRLOGPrefix(model);
        model.setNsPrefix("mylog", logPrefix);
        QueryExecution qe = QueryExecutionFactory.create(query, toBeValidated);
        ResultSet rs = qe.execSelect();

        for (; rs.hasNext(); ) {
            QuerySolution qs = rs.next();
            Resource relatedResource = qs.getResource("resource");
            Resource logLevel = qs.getResource("logLevel");
            Literal message = qs.getLiteral("message");
            RLOGIndividuals rli;
            String uri = logLevel.getURI();
            if (RLOGIndividuals.FATAL.getUri().equals(uri)) {
                rli = RLOGIndividuals.FATAL;
            } else if (RLOGIndividuals.ERROR.getUri().equals(uri)) {
                rli = RLOGIndividuals.ERROR;
            } else if (RLOGIndividuals.WARN.getUri().equals(uri)) {
                rli = RLOGIndividuals.WARN;
            } else if (RLOGIndividuals.INFO.getUri().equals(uri)) {
                rli = RLOGIndividuals.INFO;
            } else if (RLOGIndividuals.DEBUG.getUri().equals(uri)) {
                rli = RLOGIndividuals.DEBUG;
            } else if (RLOGIndividuals.TRACE.getUri().equals(uri)) {
                rli = RLOGIndividuals.TRACE;
            } else {
                rli = RLOGIndividuals.ALL;
            }

            String m = (message == null) ? "null at " + relatedResource : message.getString();
            model.add(RLOGSLF4JBinding.log(logPrefix, m, rli, this.getClass().getCanonicalName(), relatedResource.getURI(), (quiet) ? null : log));

        }
        return model;
    }

    public OntModel validate(OntModel toBeValidated) {
        //prepare output
        OntModel output = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        output.setNsPrefix("rlog", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#");


        for (String q : tests) {
            try {
                output.add(validate(toBeValidated, q));
            } catch (RiotException re) {
                log.error(q, re);
            }
        }
        return output;
    }

    public static String getQuery(String file) {
        return convertStreamToString(SPARQLValidator.class.getClassLoader().getResourceAsStream(file));
    }


    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String getLogPrefix() {
        return logPrefix;
    }

    public boolean isQuiet() {
        return quiet;
    }

    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    public OntModel getTestsuite() {
        return testsuite;
    }

    public String getVersion() {
        return version;
    }

    public List<String> getTests() {
        return tests;
    }

    public String getSparqlPrefix() {
        return sparqlPrefix;
    }
}
