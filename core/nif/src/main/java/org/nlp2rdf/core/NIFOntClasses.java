package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from
 * http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 *
 * @author croeder
 *
 */
public enum NIFOntClasses {
    // Despite these being values in Java, they are classes in OWL.
    // Hence the initial capital.

     /* TODO add label -> TODO */
        Phrase,

     /* TODO add label -> TODO */
        Sentence,

     /* RFC 5147 string -> TODO */
        RFC5147String,

     /* String -> TODO */
        String,

     /* TODO add label -> TODO */
        Word,

     /* Offset based string -> TODO */
        OffsetBasedString,

     /* Context hash based string -> TODO */
        ContextHashBasedString,

     /* Structure -> A structure is a more or less arbitrary label for a partitioning of a string. We don not follow a strict approach for what a word, phrase, sentence, paragraph is.  */
        Structure,

     /* HTML String -> A subspecialisation of String. 
     */
        HTMLString,

     /* XPath string -> TODO */
        XPathString,

     /* Context -> TODO */
        Context;


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
