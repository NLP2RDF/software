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

import com.hp.hpl.jena.ontology.Individual;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.urischemes.URIScheme;

import java.util.Comparator;

/**
 */
public class URIComparator implements Comparator<Individual> {

    final String prefix;
    final String text;
    final URIScheme uriScheme;

    public URIComparator(String prefix, String text, URIScheme uriScheme) {
        this.prefix = prefix;
        this.text = text;
        this.uriScheme = uriScheme;
    }

    @Override
    public int compare(Individual o1, Individual o2) {
        Monitor mon = MonitorFactory.getTimeMonitor("compareSpansOfURIs").start();
        try {
            if (o1.getURI().equals(o2)) {
                return 0;
            }
            //TODO refactor
            //Span a = uriScheme.parse(prefix, o1.getURI(), text);
            //Span b = uriScheme.getSpanFor(prefix, o2.getURI(), text);
            //return a.compareTo(b);
            return 0;
        } finally {
            mon.stop();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }
}
