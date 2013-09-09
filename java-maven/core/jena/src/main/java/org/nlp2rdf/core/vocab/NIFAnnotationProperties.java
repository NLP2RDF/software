package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 */
public enum NIFAnnotationProperties {

    /**
     * OLiA property link -> This property links a property of type inter or other to a URI from one of the OLiA Annotation model, e.g. http://purl.org/olia/stanford.owl#prep 
    Changelog:
    0.1.0 - added subproperty of nif:annotation as per http://svn.aksw.org/papers/2012/PeoplesWeb/public_preprint.pdf‎ page 21 
    
     */
    oliaPropLink,

    /**
     * OLiA Category -> This property links a string to the OLiA Reference model. It provides a direct link for querying, thus it is an optimization. 
    Changelog:
    0.1.1 - added subproperty of nif:classAnnotation as per http://svn.aksw.org/papers/2012/PeoplesWeb/public_preprint.pdf‎ page 21 
    
     */
    oliaCategory,

    /**
     * classAnnotation -> 
      see Towards Web-Scale Collaborative Knowledge Extraction  http://svn.aksw.org/papers/2012/PeoplesWeb/public_preprint.pdf‎ page 12 .
      Changelog:
      * 0.1.1 Fixed page number
    
     */
    classAnnotation,

    /**
     * category -> A simple annotation for machine learning purposes. The object can be anything, e.g. the literal "A. PRESS: Reportage" from Brown or any URI.  
	ChangeLog:
	* 0.2.0 changed nif:class to nif:category due to conflict with Java 
	
     */
    category;

    String uri;

    NIFAnnotationProperties() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "nif:" + name();
    }


    public AnnotationProperty getAnnotationProperty(OntModel model) {
        return model.createAnnotationProperty(getUri());
    }
}
