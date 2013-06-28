package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 * @author croeder
 *
*/

public enum NIFDatatypeProperties {

    /**
     * right suffix -> For each string you can include a snippet (e.g. 10-40 characters of text), that occurs immediately after the subject string.
     */
    suffix,

    /**
     * Part of speech tag -> To include the pos tag as it comes out of the NLP tool as RDF Literal. This property is discouraged to use alone, please use oliaLink and oliaCategory.  
     */
    posTag,

    /**
     * lemma -> The lemma(s) of the nif:String.
     */
    lemma,

    /**
     * confidence of OLiA link -> The confidence is relative to the tool and can be between 0.0 and 1.0 
     */
    oliaLinkConfidence,

    /**
     * begin index -> The begin index of a character range as defined in http://tools.ietf.org/html/rfc5147#section-2.2.1 and http://tools.ietf.org/html/rfc5147#section-2.2.2, measured as the gap between two characters, starting to count from 0 (the position before the first character of a text). 
    Example: Index "2" is the postion between "Mr" and "."  in "Mr. Sandman".
    Note: RFC 5147 is re-used for the definition of character ranges. RFC 5147 is assuming a text/plain MIME type. NIF builds upon Unicode and is content agnostic. 
    Requirement (1): This property has the same value the "Character position" of RFC 5147 and it must therefore be an xsd:nonNegativeInteger .  
    Requirement (2): The index of the subject string MUST be calculated relative to the nif:referenceContext of the subject. If available, this is the rdf:Literal of the nif:isString property.
     */
    beginIndex,

    /**
     * sentiment value -> Between -1 negative and 1 positive 
     */
    sentimentValue,

    /**
     * confidence of OLiA category -> The confidence is relative to the tool and can be between 0.0 and 1.0 
     */
    oliaCategoryConfidence,

    /**
     * end index -> The end index of a character range as defined in http://tools.ietf.org/html/rfc5147#section-2.2.1 and http://tools.ietf.org/html/rfc5147#section-2.2.2, measured as the gap between two characters, starting to count from 0 (the position before the first character of a text). 
    Example: Index "2" is the postion between "Mr" and "."  in "Mr. Sandman".
    Note: RFC 5147 is re-used for the definition of character ranges. RFC 5147 is assuming a text/plain MIME type. NIF builds upon Unicode and is content agnostic. 
    Requirement (1): This property has the same value the "Character position" of RFC 5147 and it must therefore be an xsd:nonNegativeInteger .  
    Requirement (2): The index of the subject string MUST be calculated relative to the nif:referenceContext of the subject. If available, this is the rdf:Literal of the nif:isString property.
     */
    endIndex,

    /**
     * prefix -> For each string you can include a snippet (e.g. 10-40 characters of text), that occurs immediately before the subject string.
     */
    prefix,

    /**
     * stem -> The stem(s) of the nif:String.
     */
    stem,

    /**
     * anchor of -> The string, which the uri is representing as an RDF Literal. This property is nice to have, but can be re-created from the context.
     */
    anchorOf,

    /**
     * is string -> The reference text as rdf:Literal for this nif:Context resource.  
    NIF highly recommends to always include the reference text (i.e. the context) as an rdf:Literal.
    Note, that the isString property is *the* place to keep the string itself in RDF.
    All other nif:Strings and URI Schemes relate to the text of this property to calculate character position and indices. 
     */
    isString;

    String uri;

    NIFDatatypeProperties() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
