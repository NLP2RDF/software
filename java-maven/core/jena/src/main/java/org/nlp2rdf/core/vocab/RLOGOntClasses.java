package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from
 * http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#
 *
 * @author croeder
 *
 */
public enum RLOGOntClasses {
// Despite these being values in Java, they are classes in OWL.
// Hence the initial capital.

    /**
     * Log Level -> look here: http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html 
     */
    Level,

    /**
     * Log Entry -> An entry in a log.
     */
    Entry;


    String uri;

    RLOGOntClasses() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "rlog:" + name();
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
