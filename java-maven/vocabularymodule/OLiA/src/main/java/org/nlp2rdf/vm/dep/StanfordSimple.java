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

package org.nlp2rdf.vm.dep;

import com.hp.hpl.jena.rdf.model.Model;

public class StanfordSimple {

    public static final String NS = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/dep/stanford#";

    public static void addStanfordSimplePrefix(Model model) {
        model.setNsPrefix("stanford", NS);
    }

    public static String[] getURIforEdgeLabel(String edgeLabel) {
        int index = edgeLabel.indexOf("_");

        if (index == -1) {
            return new String[] {NS + edgeLabel};
        } else {
            return new String[] {NS + edgeLabel, NS + edgeLabel.substring(0, index)};
        }
    }
}
	
