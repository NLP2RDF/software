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

package org.nlp2rdf.core.util;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;

import java.util.Comparator;

/**
 * User: hellmann
 * Date: 01.10.13
 */
public class WordComparator implements Comparator<Individual> {
    final DatatypeProperty beginIndex;


    public WordComparator(DatatypeProperty beginIndex) {
        this.beginIndex = beginIndex;
    }

    @Override
    public int compare(Individual individual, Individual individual1) {
        int a = individual.getPropertyValue(beginIndex).asLiteral().getInt();
        int b = individual1.getPropertyValue(beginIndex).asLiteral().getInt();
        return a - b;
    }

}
