/***************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                            */
/*  Note: If you need parts of NLP2RDF in another licence due to licence   */
/*  incompatibility, please mail hellmann@informatik.uni-leipzig.de        */
/*                                                                         */
/*  This file is part of NLP2RDF.                                          */
/*                                                                         */
/*  NLP2RDF is free software; you can redistribute it and/or modify        */
/*  it under the terms of the GNU General Public License as published by   */
/*  the Free Software Foundation; either version 3 of the License, or      */
/*  (at your option) any later version.                                    */
/*                                                                         */
/*  NLP2RDF is distributed in the hope that it will be useful,             */
/*  but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the           */
/*  GNU General Public License for more details.                           */
/*                                                                         */
/*  You should have received a copy of the GNU General Public License      */
/*  along with this program. If not, see <http://www.gnu.org/licenses/>.   */
/***************************************************************************/

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
