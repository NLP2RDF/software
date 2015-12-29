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

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from
 * http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#
 *
 * @author croeder
 *
 */
public enum RLOGOntClasses {
// Despite these being values in Java, they are classes in OWL.
// Hence the initial capital.

    /**
     * Status Code -> A status code which can occur in an application. Instances of this class must put the name of the status as rdfs:label and describe it with rdfs:comment.
     */
    StatusCode,

    /**
     * Log Level -> look here: http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html 
     */
    Level,

    /**
     * Log Entry -> An entry in a log.
     */
    Entry;


    String uri;

    RLOGOntClasses() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "rlog:" + name();
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
