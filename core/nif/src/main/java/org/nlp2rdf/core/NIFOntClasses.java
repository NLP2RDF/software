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

    String uri;

    NIFOntClasses() {
        this.uri = NIFNamespaces.NIF + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
