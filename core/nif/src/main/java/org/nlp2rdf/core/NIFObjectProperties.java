package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 * @author croeder
 */
public enum NIFObjectProperties {

     /* TODO add label -> $do.comment */
        nextWordTrans,

     /* TODO add label -> $do.comment */
        subString,

     /* TODO add label -> $do.comment */
        previousWord,

     /* reference context -> $do.comment */
        referenceContext,

     /* TODO add label -> $do.comment */
        superString,

     /* TODO add label -> $do.comment */
        previousWordTrans,

     /* TODO add label -> $do.comment */
        previousSentence,

     /* olia link -> $do.comment */
        oliaLink,

     /* TODO add label -> $do.comment */
        superStringTrans,

     /* TODO add label -> $do.comment */
        nextWord,

     /* TODO add label -> $do.comment */
        nextSentenceTrans,

     /* TODO add label -> $do.comment */
        subStringTrans,

     /* TODO add label -> $do.comment */
        nextSentence;

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
