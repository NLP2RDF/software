package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 * @author croeder
 */
public enum NIFObjectProperties {

    /**
     * TODO add label -> see nif:nextWord 
     */
    nextWordTrans,

    /**
     * TODO add label -> TODO add comment
     */
    subString,

    /**
     * TODO add label -> see nif:nextWord 
     */
    previousWord,

    /**
     * reference context -> The context a string occurs in. This property is functional in the validation model (nif-core-val.ttl). Each String instance should only have one reference context.
     */
    referenceContext,

    /**
     * TODO add label -> TODO add comment
     */
    superString,

    /**
     * TODO add label -> see nif:nextWord 
     */
    previousWordTrans,

    /**
     * TODO add label -> see nif:nextSentence 
     */
    previousSentence,

    /**
     * olia link -> This property links a string to a URI from one of the OLiA Annotation model, e.g. http://purl.org/olia/penn.owl#NNP 
     */
    oliaLink,

    /**
     * has first word -> This property links sentences to their first word. Itsi
     */
    firstWord,

    /**
     * TODO add label -> TODO add comment
     */
    superStringTrans,

    /**
     * TODO add label -> This property (and the others) can be used to traverse words, it can not be assumed that no gaps between words exist, i.e. string adjacency is not mandatory. 
     */
    nextWord,

    /**
     * TODO add label -> see nif:nextSentence
     */
    nextSentenceTrans,

    /**
     * has first word -> This property links sentences to their last word.
     */
    lastWord,

    /**
     * TODO add label -> TODO add comment
     */
    subStringTrans,

    /**
     * TODO add label -> This property links words to their sentence.
     */
    sentence,

    /**
     * has word -> This property links sentences to their words.
     */
    word,

    /**
     * TODO add label -> This property (and the others) can be used to traverse nif:Sentences, it can not be assumed that no gaps or whitespaces between Sentences exist, i.e. string adjacency is not mandatory. 
     */
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
