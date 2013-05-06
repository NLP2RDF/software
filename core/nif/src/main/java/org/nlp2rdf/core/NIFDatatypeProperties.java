package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 * @author croeder
 *
*/

public enum NIFDatatypeProperties {

     /* Part of speech tag -> To include the pos tag as it comes out of the NLP tool as RDF Literal. This property is discouraged to use alone, please use oliaLink and oliaCategory.   */
        posTag,

     /* stem -> To add the stem. */
        stem,

     /* lemma -> To add the lemma. */
        lemma,

     /* right context -> For each string you can include a snippet (e.g. 20-40 characters of text), that is left or right of the subject string.  */
        rightContext,

     /* left context -> For each string you can include a snippet (e.g. 20-40 characters of text), that is left or right of the subject string.  */
        leftContext,

     /* reference context -> The end index of the string relative to the content of isString in the reference context, defined by RFC 5147.  */
        endIndex,

     /* begin index -> The begin index of the string relative to the content of isString in the reference context, defined by RFC 5147.  */
        beginIndex,

     /* anchor of -> The string, which the uri is representing as an RDF Literal. This property is nice to have, but can be re-created from the context. */
        anchorOf,

     /* is string -> The string, which the uri is representing as an RDF Literal. This property is mandatory for the Context. Note, that the isString property is *the* place to keep the string itself in RDF, all other Strings and URI Scheme relate to it and can be calculated and reproduced. */
        isString;

    String uri;

    NIFDatatypeProperties() {
        this.uri = NIFNamespaces.NIF + name();
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
