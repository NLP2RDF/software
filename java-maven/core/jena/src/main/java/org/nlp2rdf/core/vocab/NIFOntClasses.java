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
     * RFC 5147 String -> cf. http://tools.ietf.org/html/rfc5147 URIs of this class have to conform with the syntax of RFC 5147 in a way that the end on a valid identifier, if you remove the prefix. Note that unlike RFC 5147 NIF does not requrire '#' URIs. So valid URIs are http://example.org#char=0,28 , http://example.org/whatever/char=0,28 , http://example.org/nif?char=0,28
    Changelog:
    * 1.1.0 changed subclass to CString .
    * 1.1.1 typo in rdfs:label and extension of comment
    
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
     * Label String -> (NOTE: this is a real early draft) Instances of this class represent a label independent from any context. This class is a subClassOf skosxl:Label, but disjoint with nif:String (see nif-core-val.ttl). We included this class in nif-core, because we are proposing the following scheme for it: 'label_'+urlencode($term). Might be replaces with lemon in the future.  
     */
    LabelString,

    /**
     * TODO add label -> TODO add comment
     */
    CollectionOccurrence,

    /**
     * Word -> 
    The Word class represents strings that are tokens or words. A string is a Word, if it is a word. We don't nitpic about whether it is a a pronoun, a name, a punctuation mark or an apostrophe or whether it is separated by white space or something else. The string 'He enters the room.' for example has 5 words. Words are assigned by a tokenizer NIF Implementation. 
    
    We adopted the definition style from foaf:Person, see here: http://xmlns.com/foaf/spec/#term_Person We are well aware that the world out there is much more complicated, but we are ignorant about it, for the following reasons:
    
    1. NIF has a client-server and the client has the ability to dictate the tokenization to the server (i.e. the NIF Implementation) by sending properly tokenized NIF annotated with nif:Word. All NIF Implementations are supposed to honor and respect the current assignment of the Word class. Thus the client should decide which NIF Implementation should create the tokenization. Therefore this class is not descriptive, but prescriptive.
    2. The client may choose to send an existing tokenization to a NIF Implementation, with the capability to change (for better or for worse) the tokenization. 
    
   
    The class has not been named 'Token' as the NLP definition of 'token' is descriptive (and not well-defined), while the assignment of what is a Word and what not is definatory, e.g. "can't" could be described as one, two or three tokens or defined as being one, two or three words. For further reading, we refer the reader to: By all these lovely tokens... Merging conflicting tokenizations by Christian Chiarcos, Julia Ritz, and Manfred Stede. Language Resources and Evaluation 46(1):53-74 (2012) or the short form: http://www.aclweb.org/anthology/W09-3005
    
    There the task at hand is to merge two tokenization T_1 and T_2 which is normally not the case in the NIF world.
    
    Changelog:
    * 0.1.1 fixed spelling
    * 0.2.1 added a proper definition.
    
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
     * Context Collection -> An entity grouping different nif:Context via the nif:context property. This is often the same as a dcoument collection, but is named Context in NIF, because NIF describes on Unicode Strings.
     */
    ContextCollection,

    /**
     * Context Hash Based String ->  cf. https://www.google.de/search?q=Linked-Data+Aware+URI+Schemes+for+Referencing+Text
     */
    ContextHashBasedString,

    /**
     * Structure -> A structure is a more or less arbitrary label for a partitioning of a string. We do not follow a strict approach for what a word, phrase, sentence, title, paragraph is. These labels enable the definition processes for tool chains, e.g. tool analyses nif:Paragraph and calculates term frequency. 
     */
    Structure,

    /**
     * Occurring String -> (NOTE: this is a real early draft) Instances of this class represent all occurring strings which match this string within the same context. Requires the presence of nif:anchorOf.  Definition: All strings in the context, that match, if you search for the literal of nif:anchorOf.  The pattern for this scheme is 'occ_'+urlencode($anchorOf) . 
	
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
