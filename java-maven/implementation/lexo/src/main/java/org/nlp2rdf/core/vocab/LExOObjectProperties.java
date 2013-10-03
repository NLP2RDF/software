package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#
 * @author croeder
 */
public enum LExOObjectProperties {

    /**
     * axiom target -> target for binary axioms
     */
    axTarget,

    /**
     * axiom source -> Please put the Generation Rule here in the construct query
     */
    axSource,

    /**
     * axiom descriptor  -> The property is called axDesc because it describes what axioms are explicated by the rules and how the LExO engine should generate OWL axioms from it
     */
    axDesc;

    String uri;

    LExOObjectProperties() {
        this.uri = "$preferredNamespaceUri" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "$preferredNamespacePrefix:" + name();
    }


    public ObjectProperty getObjectProperty(OntModel model) {
        return model.createObjectProperty(getUri());
    }
}
