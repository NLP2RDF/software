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

    @Deprecated
    public static final String NAMESPACE = "http://nlp2rdf.lod2.eu/schema/nif/";

    String uri;

    NIFObjectProperties() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("nif", NAMESPACE);
    }

    public ObjectProperty getObjectProperty(OntModel model) {
        return model.createObjectProperty(getUri());
    }
}
