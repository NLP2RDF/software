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

package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author kurzum
 */
public class NIFNamespaces {
    public static final String BASE = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/";
    public static final String NIF = BASE + "nif-core#";
    public static final String RLOG = BASE + "rlog#";
    public static final String LExO = BASE + "vm/lexo#";

    public static void addNifPrefix(Model model) {
        model.setNsPrefix("nif", NIF);
    }

    public static void addRLOGPrefix(Model model) {
        model.setNsPrefix("rlog", RLOG);
    }
    public static void addLExOPrefix(Model model) {
        model.setNsPrefix("lexo", LExO);
    }

}
