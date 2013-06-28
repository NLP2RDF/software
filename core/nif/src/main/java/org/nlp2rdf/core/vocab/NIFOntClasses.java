package org.nlp2rdf.core.vocab;

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

    /**
     * TODO add label -> TODO add comment
     */
    ContextOccurrence,

    /**
     * TODO add label -> TODO add comment
     */
    NormalizedCollectionOccurrence,

    /**
     * phrase -> A nif:Phrase can be a nif:String, that is a chunk of several words or a word itself (e.g. a NounPhrase as a Named Entity). The term is underspecified and can be compatible with many defintitions of phrase. Please subClass it to specify the meaning (e.g. for Chunking or Phrase Structure Grammar). Example: ((My dog)(also)(likes)(eating (sausage))) 
     */
    Phrase,

    /**
     * Occuring String -> Instances of this class represent all occuring words with a matching string within the same context. The pattern for this scheme is 'occ_'+urlencode($anchorOf) . If the text is tokenizedd with nif:Word, instances of this class would be the same as the combination of all instances, that have the same nif:anchorOf and the same nif:referenceContext. It is a scheme to refer to all tokens with the exact same string in one text. If such a URI is used, it implies, that the word occurs at least once in the given context. Otherwise nif:LabelString should be used. 
     */
    OccuringWord,

    /**
     * sentence -> a sentence.
     */
    Sentence,

    /**
     * RFC 5147 string -> cf. http://tools.ietf.org/html/rfc5147 
     */
    RFC5147String,

    /**
     * String ->  see http://svn.aksw.org/papers/2013/ISWC_NIF/public.pdf 
     */
    String,

    /**
     * TODO add label -> TODO add comment
     */
    NormalizedContextOccurrence,

    /**
     * paragraph -> a paragraph.
     */
    Paragraph,

    /**
     * Label String -> Instances of this class represent a label independent from any context. This class is a subClassOf skosxl:Label, but disjoint with nif:String (see nif-core-val.ttl). We included this class in nif-core, because we are proposing the following scheme for it: 'label_'+urlencode($term).   
     */
    LabelString,

    /**
     * TODO add label -> TODO add comment
     */
    CollectionOccurrence,

    /**
     * word -> A string that can be considered a word or a punctuation mark, the sentence 'He enters the room.' for example has 5 words. In general, the division into nif:Word is done by a Tokenizer. Instances of this class should be a string, that is a 'meaningful' unit of characters. The class has not been named 'Token' as the NLP definition of 'Token' is more similar to our definition of nif:String in general. 
     */
    Word,

    /**
     * Offset based string -> cf. https://www.google.de/search?q=Linked-Data+Aware+URI+Schemes+for+Referencing+Text
     */
    OffsetBasedString,

    /**
     * Context hash based string ->  cf. https://www.google.de/search?q=Linked-Data+Aware+URI+Schemes+for+Referencing+Text
     */
    ContextHashBasedString,

    /**
     * Structure -> A structure is a more or less arbitrary label for a partitioning of a string. We do not follow a strict approach for what a word, phrase, sentence, paragraph is. 
     */
    Structure,

    /**
     * HTML String -> experimental, a subspecialisation of String. 
     */
    HTMLString,

    /**
     * URI Scheme -> A URI Scheme for NIF, subclasses need to define guidelines on the URI Scheme as well as the text it refers to. This class is just to keep some order, please don't serialize.
     */
    URIScheme,

    /**
     * title -> a title within a text.
     */
    Title,

    /**
     * XPath string -> TODO add comment
     */
    XPathString,

    /**
     * Context ->  see http://svn.aksw.org/papers/2013/ISWC_NIF/public.pdf 
     */
    Context;


    String uri;

    NIFOntClasses() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#" + name();
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
