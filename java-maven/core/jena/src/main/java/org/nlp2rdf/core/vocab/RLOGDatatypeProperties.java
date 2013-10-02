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
    date,

    /**
     * code id -> The numerical value of the code, e.g. 200, 404 or 42.
     */
    codeId,

    /**
     * class name -> Outputs the fully qualified class name of the caller issuing the logging request.
     */
    className;

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
