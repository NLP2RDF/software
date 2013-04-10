package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author roederc
 */
public enum NIFObjectProperties {

    referenceContext,
    word,
    sentence;

    String uri;

    NIFObjectProperties() {
        this.uri = NIFNamespaces.NIF + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }


    public ObjectProperty getObjectProperty(OntModel model) {
        return model.createObjectProperty(getUri());
    }
}
