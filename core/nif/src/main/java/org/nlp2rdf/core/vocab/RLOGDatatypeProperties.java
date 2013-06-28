package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#
 * @author croeder
 *
*/

public enum RLOGDatatypeProperties {

    /**
     * logLevel -> Attention: the ids here can change, they are just used to define an order over the levels!
     */
    priority,

    /**
     * Logging message -> TODO add comment
     */
    message,

    /**
     * Logging date in datetime -> TODO add comment
     */
    date;

    String uri;

    RLOGDatatypeProperties() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "rlog:" + name();
    }

    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
