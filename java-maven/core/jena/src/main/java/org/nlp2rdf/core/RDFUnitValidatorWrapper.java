package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.aksw.rdfunit.validate.wrappers.RDFUnitStaticValidator;
import org.aksw.rdfunit.validate.wrappers.RDFUnitTestSuiteGenerator;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shellmann on 1/2/16.
 */
public class RDFUnitValidatorWrapper {
    private static Logger log = LoggerFactory.getLogger(RDFUnitValidatorWrapper.class);

    static {
        RDFUnitStaticValidator.initWrapper(
                new RDFUnitTestSuiteGenerator.Builder()
                        .addLocalResourceOrSchemaURI("nif", "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.ttl", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#")
                        .build()
        );

    }



    public void process( OntModel inputModel, OntModel outputModel , NIFParameters nifParameters) {
        Monitor mon = MonitorFactory.getTimeMonitor(RDFUnitStaticValidator.class.getCanonicalName()).start();

        // Convert model to OntModel
        Model validationResults = RDFUnitStaticValidator.validate(inputModel);
        outputModel.add(validationResults);
        double lv = mon.stop().getLastValue();
        log.debug("model validated with RDFUnit validated: "+lv );
        String finalMessage = "validated with RDFUnit in " + lv + " ms.  producing " + validationResults.size() + " triples";
        outputModel.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, RDFUnitStaticValidator.class.getCanonicalName(), null, null));
        outputModel.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
    }
}
