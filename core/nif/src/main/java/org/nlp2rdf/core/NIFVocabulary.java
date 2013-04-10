package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * User: hellmann
 * Date: 27.02.13
 */
@Deprecated
public enum NIFVocabulary {
    //Classes
    String,
    Context,
    Sentence,
    Word,
    Phrase,

    //DatatypeProperties
    anchorOf,
    isString,
    lemma,

    //ObjectProperties
    referenceContext,
    word,
    sentence;

    @Deprecated
    public static final String NAMESPACE = "http://nlp2rdf.lod2.eu/schema/nif/";

    String uri;

    NIFVocabulary() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }

    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("nif", NAMESPACE);
    }

	public DatatypeProperty getProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
	}

    /**
     * Classes *
     */

    public static OntClass getContextClass(OntModel model) {
        return model.createClass(Context.getUri());
    }

    public static OntClass getStringClass(OntModel model) {
        return model.createClass(String.getUri());
    }

    public static OntClass getSentenceClass(OntModel model) {
        return model.createClass(Sentence.getUri());
    }

    public static OntClass getWordClass(OntModel model) {
        return model.createClass(Word.getUri());
    }


    /**
     * ObjectProperties *
     */


    public static ObjectProperty getReferenceContextProperty(OntModel model) {
        return model.createObjectProperty(referenceContext.getUri());
    }

    public static ObjectProperty getWordProperty(OntModel model) {
        return model.createObjectProperty(word.getUri());
    }

    public static ObjectProperty getSentenceProperty(OntModel model) {
        return model.createObjectProperty(sentence.getUri());
    }

    /**
     * DatatypeProperty *
     */

	/**
	 * @deprecated, use the getProperty(OntModel) member function instead
	 */
	@Deprecated
    public static DatatypeProperty getAnchorOfProperty(OntModel model) {
        return model.createDatatypeProperty(anchorOf.getUri());
    }

	/**
	 * @deprecated, use the getProperty(OntModel) member function instead
	 */
	@Deprecated
    public static DatatypeProperty getIsStringtProperty(OntModel model) {
        return model.createDatatypeProperty(isString.getUri());
    }

	/**
	 * @deprecated, use the getProperty(OntModel) member function instead
	 */
	@Deprecated
    public static DatatypeProperty getLemmaProperty(OntModel model) {
        return model.createDatatypeProperty(lemma.getUri());
    }
}
