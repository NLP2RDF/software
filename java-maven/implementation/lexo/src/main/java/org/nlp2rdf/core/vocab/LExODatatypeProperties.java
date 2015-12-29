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
 * This class was automatically generated from http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#
 * @author croeder
 *
*/

public enum LExODatatypeProperties {

    /**
     * TODO add label -> A reason, why this part was not yet covered.
     */
    uncovered,

    /**
     * TODO add label -> a simple marker to check whether this part was used
     */
    skipped,

    /**
     * SPARQL construct query -> Put the valid SPARQL construct queries here.
     */
    construct,

    /**
     * TODO add label -> part which is used in the construction of class name
     */
    cnPart,

    /**
     * TODO add label -> gives the order of how the classname is constructed
     */
    cnOrder,

    /**
     * TODO add label -> TODO add comment
     */
    className;

    String uri;

    LExODatatypeProperties() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "lexo:" + name();
    }

    public DatatypeProperty getDatatypeProperty(OntModel model) {
        return model.createDatatypeProperty(getUri());
    }
}
