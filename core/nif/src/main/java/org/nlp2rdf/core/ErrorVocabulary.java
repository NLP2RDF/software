package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * User: hellmann
 * Date: 16.03.13
 */
@Deprecated
public enum ErrorVocabulary {
    //Classes
    Error,

    //DatatypeProperties
    fatal,
    message;

    public static final String NAMESPACE = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/error#";

    String uri;

    ErrorVocabulary() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "error:" + name();
    }

    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("error", NAMESPACE);
    }

    /**
     * Classes *
     */

    public static OntClass getErrorClass(OntModel model) {
        return model.createClass(Error.getUri());
    }


    /**
     * DatatypeProperty *
     */

    public static DatatypeProperty getFatalProperty(OntModel model) {
        return model.createDatatypeProperty(fatal.getUri());
    }
    public static DatatypeProperty getMessageProperty(OntModel model) {
        return model.createDatatypeProperty(message.getUri());
    }

}
