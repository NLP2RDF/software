package org.nlp2rdf.core;

import com.hp.hpl.jena.rdf.model.Model;
import org.aksw.rdfunit.RDFUnit;
import org.aksw.rdfunit.Utils.RDFUnitUtils;
import org.aksw.rdfunit.Utils.TestUtils;
import org.aksw.rdfunit.enums.TestCaseExecutionType;
import org.aksw.rdfunit.exceptions.TripleReaderException;
import org.aksw.rdfunit.io.DataModelReader;
import org.aksw.rdfunit.io.DataReader;
import org.aksw.rdfunit.io.RDFDereferenceReader;
import org.aksw.rdfunit.sources.DumpSource;
import org.aksw.rdfunit.sources.SchemaSource;
import org.aksw.rdfunit.sources.Source;
import org.aksw.rdfunit.tests.TestCase;
import org.aksw.rdfunit.tests.TestSuite;
import org.aksw.rdfunit.tests.executors.TestExecutor;
import org.aksw.rdfunit.tests.executors.monitors.SimpleTestExecutorMonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * User: Dimitris Kontokostas
 * Wraps RDFUnit for NIF
 * Created: 5/6/14 5:35 PM
 */
public class RDFUnitWrapper {

    // These url are relative to RDFUnit v0.4
    private static String nifManualTests = "https://raw.githubusercontent.com/AKSW/RDFUnit/89a851b05783289c8c20027e5d3c80bb0d266e14/data/tests/Manual/persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core/nif.tests.Manual.ttl";
    private static String nifOntology = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#";

    // Keep this static and initialize only once on startup / first run
    private static TestSuite testSuite = null;

    private RDFUnitWrapper() {

    }

    private static TestSuite getTestSuite() {
        if (testSuite == null) {
            synchronized (RDFUnitWrapper.class) {
                if (testSuite == null) {


                    DataReader manualTestCaseReader = new RDFDereferenceReader(nifManualTests);

                    // Instantiate manual test cases
                    Collection<TestCase> manualTestCases;
                    try {
                        manualTestCases = TestUtils.instantiateTestsFromModel(manualTestCaseReader.read());
                    } catch (TripleReaderException e) {
                        // Create an empty collection
                        manualTestCases = new ArrayList<TestCase>();
                    }

                    // Generate test cases from ontology (do this every time in case ontology changes)
                    RDFUnit rdfunit = new RDFUnit();
                    try {
                        rdfunit.initPatternsAndGenerators(
                                RDFUnitUtils.getPatternsFromResource(),
                                RDFUnitUtils.getAutoGeneratorsFromResource());
                    } catch (TripleReaderException e) {
                        // fatal error / send only manual test cases
                        testSuite = new TestSuite(manualTestCases);
                        return testSuite; // do not execute further
                    }

                    DataReader ontologyReader = new RDFDereferenceReader(nifOntology);

                    Source schema = new SchemaSource("nif", nifOntology, ontologyReader);
                    Collection<TestCase> autoTestCases = TestUtils.instantiateTestsFromAG(rdfunit.getAutoGenerators(), schema);

                    Collection<TestCase> allTestCases = new ArrayList<TestCase>();
                    allTestCases.addAll(autoTestCases);
                    allTestCases.addAll(manualTestCases);

                    testSuite = new TestSuite(allTestCases);
                }
            }
        }

        return testSuite;
    }

    public static Model validate(final Model input) {

        SimpleTestExecutorMonitor testExecutorMonitor = new SimpleTestExecutorMonitor();
        TestExecutor testExecutor = TestExecutor.initExecutorFactory(TestCaseExecutionType.rlogTestCaseResult);
        testExecutor.addTestExecutorMonitor(testExecutorMonitor);

        Source modelSource = new DumpSource(
                "prefix", // prefix
                "uri",    //uri
                new DataModelReader(input), // the input model as a DataReader
                Arrays.asList(  // List of associated ontologies (these will be loaded in the testing model)
                        new SchemaSource("nif", nifOntology, new RDFDereferenceReader(nifOntology)))
        );

        testExecutor.execute(modelSource, getTestSuite(), 0);

        return testExecutorMonitor.getModel();
    }
}