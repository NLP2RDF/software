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
     * Consecutive String -> A URI Scheme for NIF which is able to refer to a single, consecutive string in a context. Note that any scheme subclassing this class, requires the existence of beginIndex, endIndex and referenceContext . 
    This is an abstract class and should not be serialized. 
    
     */
    CString,

    /**
     * TODO add label -> TODO add comment
     */
    ContextOccurrence,

    /**
     * TODO add label -> TODO add comment
     */
    NormalizedCollectionOccurrence,

    /**
     * Phrase -> A nif:Phrase can be a nif:String, that is a chunk of several words or a word itself (e.g. a NounPhrase as a Named Entity). The term is underspecified and can be compatible with many defintitions of phrase. Please subClass it to specify the meaning (e.g. for Chunking or Phrase Structure Grammar). Example: ((My dog)(also)(likes)(eating (sausage))) 
    Changelog:
    * 0.1.1 fixed spelling
    
     */
    Phrase,

    /**
     * Sentence -> 
    A sentence.
    Changelog:
    * 0.1.1 fixed spelling, added language tag
    
     */
    Sentence,

    /**
     * RFC 5147 string -> cf. http://tools.ietf.org/html/rfc5147 
    Changelog:
    * 1.1.0 changed subclass to CString .
    
     */
    RFC5147String,

    /**
     * String -> Individuals of this class are string, i.e. Unicode characters. 
    
    NIF-Stanbol (nif-stanbol.ttl):
    subclassOf nifs:Annotation because it "annotates" strings for example with begin and end index. The class is similar to fise:TextAnnotation
     
    
     */
    String,

    /**
     * Consecutive String Instantiation -> An abitrary URI (e.g. a URN) for an arbitrary string of the context. This is exactly the same as TextAnnotations are currently implemented in Stanbol.
     */
    CStringInst,

    /**
     * TODO add label -> TODO add comment
     */
    NormalizedContextOccurrence,

    /**
     * Paragraph -> 
    A paragraph.
    Changelog:
    * 0.1.1 fixed spelling, added language tag
    
     */
    Paragraph,

    /**
     * Label String -> Instances of this class represent a label independent from any context. This class is a subClassOf skosxl:Label, but disjoint with nif:String (see nif-core-val.ttl). We included this class in nif-core, because we are proposing the following scheme for it: 'label_'+urlencode($term). Might be replaces with lemon in the future.  
     */
    LabelString,

    /**
     * TODO add label -> TODO add comment
     */
    CollectionOccurrence,

    /**
     * Word -> 
    Assigning this class to a nif:String has definitory character. 
    A string that can be considered a word or a punctuation mark, the sentence 'He enters the room.' for example has 5 words. In general, the division into nif:Word is done by a tokenizer. Instances of this class are defined as a string, that is a 'meaningful' unit of characters. The class is not descriptive, but prescriptive. All NIF tools are supposed to either honor the current assignment of nif:Word or possibly correct it, if that is intended by the developer. 
    The class has not been named 'Token' as the NLP definition of 'Token' is more similar to our definition of nif:String in general, e.g. "can't" could be one, two or three tokens.
    Changelog:
    * 0.1.1 fixed spelling
    
     */
    Word,

    /**
     * Offset-based String -> cf. Linked-Data Aware URI Schemes for Referencing Text Fragments by Sebastian Hellmann, Jens Lehmann und Sören Auer in EKAW 2012 http://jens-lehmann.org/files/2012/ekaw_nif.pdf
	
	requires the existence of begin, endIndex and referenceContext
	
	Changelog: 
	* 1.0.0 originally a plain text string was attached to this uri scheme, but this was removed later, because of complaints and complexity. 
	* 1.1.0 subclassing of nif:CString
    
     */
    OffsetBasedString,

    /**
     * Context Hash Based String ->  cf. https://www.google.de/search?q=Linked-Data+Aware+URI+Schemes+for+Referencing+Text
     */
    ContextHashBasedString,

    /**
     * Structure -> A structure is a more or less arbitrary label for a partitioning of a string. We do not follow a strict approach for what a word, phrase, sentence, title, paragraph is. These labels enable the definition processes for tool chains, e.g. tool analyses nif:Paragraph and calculates term frequency. 
     */
    Structure,

    /**
     * Occurring String -> Instances of this class represent all occurring strings which match this string within the same context. Requires the presence of nif:anchorOf.  Definition: All strings in the context, that match, if you search for the literal of nif:anchorOf.  The pattern for this scheme is 'occ_'+urlencode($anchorOf) . 
	
	To express that this string is a word or phrase combine with a "structure" class.
	It is a scheme to refer to all substrings with an exact string match in one context. If such a URI is used, it implies, that the word occurs at least once in the given context. Otherwise nif:LabelString should be used. 
     */
    OccurringString,

    /**
     * URI Scheme -> A URI Scheme for NIF, subclasses need to define guidelines on the URI Scheme as well as the text it refers to. This class is just to keep some order, and should not be serialized. 
    
    This is an abstract class and should not be serialized. 
    
    Changelog:
    * 0.1.1 changed comment
    
     */
    URIScheme,

    /**
     * Title -> 
    A title within a text.
    Changelog:
    * 0.1.1 fixed spelling, added language tag
    
     */
    Title,

    /**
     * Context -> The string used to calculate the indexes for, if used with nif:referenceContext, see http://svn.aksw.org/papers/2013/ISWC_NIF/public.pdf 
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
