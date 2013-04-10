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

    @Deprecated
    public static final String NAMESPACE = "http://nlp2rdf.lod2.eu/schema/nif/";

    String uri;

    NIFDatatypeProperties() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

	@Deprecated
    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("nif", NAMESPACE);
    }

    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
