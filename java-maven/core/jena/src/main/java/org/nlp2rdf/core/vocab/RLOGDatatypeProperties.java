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

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#
 * @author croeder
 *
*/

public enum RLOGDatatypeProperties {

    /**
     * logLevel -> Attention: the ids here can change, they are just used to define an order over the levels!
     */
    priority,

    /**
     * Logging message -> TODO add comment
     */
    message,

    /**
     * Logging date in datetime -> TODO add comment
     */
    date,

    /**
     * code id -> The numerical value of the code, e.g. 200, 404 or 42.
     */
    codeId,

    /**
     * class name -> Outputs the fully qualified class name of the caller issuing the logging request.
     */
    className;

    String uri;

    RLOGDatatypeProperties() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "rlog:" + name();
    }

    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
