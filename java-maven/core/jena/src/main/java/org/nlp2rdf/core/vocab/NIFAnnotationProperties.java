/******************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                               */
/*                                                                            */
/*  Licensed under the Apache License, Version 2.0 (the "License");           */
/*  you may not use this file except in compliance with the License.          */
/*  You may obtain a copy of the License at                                   */
/*                                                                            */
/*      http://www.apache.org/licenses/LICENSE-2.0                            */
/*                                                                            */
/*  Unless required by applicable law or agreed to in writing, software       */
/*  distributed under the License is distributed on an "AS IS" BASIS,         */
/*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  */
/*  See the License for the specific language governing permissions and       */
/*  limitations under the License.                                            */
/******************************************************************************/

package org.nlp2rdf.core.vocab;

import com.hp.hpl.jena.ontology.AnnotationProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#
 */
public enum NIFAnnotationProperties {

    /**
     * OLiA Category -> This property links a string to the OLiA Reference model. It provides a direct link for querying, thus it is an optimization. 
    Changelog:
    0.1.1 - added subproperty of nif:classAnnotation as per http://svn.aksw.org/papers/2012/PeoplesWeb/public_preprint.pdf‎ page 21 
    0.1.2 - removed domain axiom for annotationproperty
    
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
