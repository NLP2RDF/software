package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author croeder
 */
public enum NIFOntClasses {

	// Despite these being values in Java, they are classes in OWL.
	// Hence the initial capital.
    String,
    Context,
    Sentence,
    Word,
    Phrase;

    @Deprecated
    public static final String NAMESPACE = "http://nlp2rdf.lod2.eu/schema/nif/";

    String uri;

    NIFOntClasses() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

	/**
	 * @deprecated why here?
	 */
	@Deprecated
    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("nif", NAMESPACE);
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
