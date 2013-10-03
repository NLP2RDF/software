package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from
 * http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#
 *
 * @author croeder
 *
 */
public enum LExOOntClasses {
// Despite these being values in Java, they are classes in OWL.
// Hence the initial capital.

    /**
     * GenerationRule -> Describes the rules
     */
    GenRule,

    /**
     * TODO add label -> TODO add comment
     */
    ClassPart,

    /**
     * TODO add label -> TODO add comment
     */
    AxiomPart,

    /**
     * TODO add label -> TODO add comment
     */
    AxiomDescriptor,

    /**
     * TODO add label -> TODO add comment
     */
    Axiom;


    String uri;

    LExOOntClasses() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "lexo:" + name();
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
