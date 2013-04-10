package org.nlp2rdf.vocabularymodule.olia;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * User: hellmann
 * Date: 27.02.13
 */
public enum OLiAVocabulary {
    //Classes

    //DatatypeProperties

    //ObjectProperties
    oliaIndividual,

    //AnnotationProperties
    oliaCategory;

    @Deprecated
    public static final String NAMESPACE = "http://nlp2rdf.lod2.eu/schema/nif/module/olia/";

    String uri;

    OLiAVocabulary() {
        this.uri = NAMESPACE + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif-olia:" + name();
    }

    public static void addPrefixes(OntModel model) {
        model.setNsPrefix("nif-olia", OLiAVocabulary.NAMESPACE);
    }

    /**
     * Classes *
     */

    /**
     * ObjectProperties *
     */


    public static ObjectProperty getOliaIndividualProperty(OntModel model) {
        return model.createObjectProperty(OLiAVocabulary.oliaIndividual.getUri());
    }

    /**
     * DatatypeProperty *
     */

    /**
     * AnnotationProperties *
     */

    public static AnnotationProperty getOliCategoryProperty(OntModel model) {
        return model.createAnnotationProperty(OLiAVocabulary.oliaCategory.getUri());
    }


}
