package org.nlp2rdf.core;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import org.nlp2rdf.core.vocab.RLOGDatatypeProperties;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.core.vocab.RLOGObjectProperties;
import org.nlp2rdf.core.vocab.RLOGOntClasses;
import org.slf4j.Logger;

/**
 * @author kurzum
 */
public class RLOGSLF4JBinding {
    private static int counter = 0;
    private static String defaultlogprefix = "http://nlp2rdf.lod2.eu/instance/log/id_";

    public static Model log(String message, RLOGIndividuals level) {
        return log(defaultlogprefix, message, level, null);
    }

    public static Model log(String message, RLOGIndividuals level, Logger logger) {
        return log(defaultlogprefix, message, level, logger);
    }


    public static Model log(String logPrefix, String message, RLOGIndividuals level, Logger logger) {
        OntModel model = ModelFactory.createOntologyModel();
        NIFNamespaces.addRLOGPrefix(model);

        Resource r = model.createResource(logPrefix + getCounter() + "_" + System.currentTimeMillis() + "_" + level.toString());
        r.addProperty(RDF.type, model.createResource(RLOGOntClasses.Entry.getUri()));
        r.addProperty(RLOGObjectProperties.level.getObjectProperty(model), model.createResource(level.getUri()));
        r.addProperty(RLOGDatatypeProperties.message.getDatatypeProperty(model), message);
        r.addProperty(RLOGDatatypeProperties.date.getDatatypeProperty(model), ISOTime.xsddatetime(System.currentTimeMillis()), ISOTime.getXSDDatetime());
        if (logger != null) {
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