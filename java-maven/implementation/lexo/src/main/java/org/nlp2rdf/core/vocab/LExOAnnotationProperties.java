package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#
 */
public enum LExOAnnotationProperties {

    /**
     * axiom property -> property for OWL property estrictions axioms
     */
    axProperty;

    String uri;

    LExOAnnotationProperties() {
        this.uri = "$preferredNamespaceUri" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "$preferredNamespacePrefix:" + name();
    }


    public AnnotationProperty getAnnotationProperty(OntModel model) {
        return model.createAnnotationProperty(getUri());
    }
}
