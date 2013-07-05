package org.nlp2rdf.core;


import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import org.nlp2rdf.core.vocab.RLOGDatatypeProperties;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.core.vocab.RLOGObjectProperties;
import org.nlp2rdf.core.vocab.RLOGOntClasses;
import org.slf4j.Logger;

import java.util.Calendar;

/**
 * @author kurzum
 */
public class RLOGSLF4JBinding {
    private static int counter = 0;
    public static String defaultlogprefix = "http://nlp2rdf.lod2.eu/instance/log/id_";

    public static Model log(String message, RLOGIndividuals level) {
        return log(defaultlogprefix, message, level, null, null);
    }

    public static Model log(String message, RLOGIndividuals level, Logger logger) {
        return log(defaultlogprefix, message, level, null, logger);
    }

    public static Model log(String logPrefix, String message, RLOGIndividuals level, Logger logger) {
        return log(logPrefix, message, level, null, logger);
    }


    public static OntModel log(String logPrefix, String message, RLOGIndividuals level, String resourceURI, Logger logger) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        model.setNsPrefix("rlog", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#");

        Resource r = model.createResource(logPrefix + level.name() + "_" + getCounter() + "_" + System.currentTimeMillis());
        r.addProperty(RDF.type, model.createResource(RLOGOntClasses.Entry.getUri()));
        r.addProperty(RLOGObjectProperties.level.getObjectProperty(model), model.createResource(level.getUri()));
        r.addProperty(RLOGDatatypeProperties.message.getDatatypeProperty(model), message);
        XSDDateTime date = new XSDDateTime(Calendar.getInstance());
        r.addProperty(RLOGDatatypeProperties.date.getDatatypeProperty(model), date.timeLexicalForm(), date.getNarrowedDatatype());

        if (resourceURI != null) {
            r.addProperty(RLOGObjectProperties.resource.getObjectProperty(model), model.createResource(resourceURI));
        }

        if (logger != null) {
            r.addProperty(RLOGDatatypeProperties.className.getDatatypeProperty(model), logger.getName());
            switch (level) {
                case TRACE:
                    logger.trace(message);
                    break;
                case DEBUG:
                    logger.debug(message);
                    break;
                case INFO:
                    logger.info(message);
                    break;
                case WARN:
                    logger.warn(message);
                    break;
                case ERROR:
                    logger.error(message);
                    break;
                case FATAL:
                    logger.error(message);
                    break;

            }

        }

        return model;
    }

    private static synchronized int getCounter() {
        return counter++;
    }

}