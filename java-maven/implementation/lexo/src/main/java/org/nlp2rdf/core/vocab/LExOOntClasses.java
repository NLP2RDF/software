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
 * http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#
 *
 * @author croeder
 *
 */
public enum LExOOntClasses {
// Despite these being values in Java, they are classes in OWL.
// Hence the initial capital.

    /**
     * GenerationRule -> Describes the rules
     */
    GenRule,

    /**
     * TODO add label -> TODO add comment
     */
    ClassPart,

    /**
     * TODO add label -> TODO add comment
     */
    AxiomPart,

    /**
     * TODO add label -> TODO add comment
     */
    AxiomDescriptor,

    /**
     * TODO add label -> TODO add comment
     */
    Axiom;


    String uri;

    LExOOntClasses() {
        this.uri = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/lexo#" + name();
    }

    public String getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "lexo:" + name();
    }

    public OntClass getOntClass(OntModel model) {
        return model.createClass(getUri());
    }
}
