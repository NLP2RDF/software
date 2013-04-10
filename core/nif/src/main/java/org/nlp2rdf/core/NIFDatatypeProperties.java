package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author roederc
 */
public enum NIFDatatypeProperties {
    anchorOf,
    isString,
    lemma;

    String uri;

    NIFDatatypeProperties() {
        this.uri = NIFNamespaces.NIF + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }


    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
