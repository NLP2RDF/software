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

package org.nlp2rdf.ontology.olia;

import org.junit.Test;

import java.util.Set;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 *         Created: 27.05.11
 */
public class TestOLiAOntology {

    static {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect!!!
        if (!assertsEnabled) throw new RuntimeException("Asserts must be enabled!!!");
    }


    @Test
    public void test() {
        //OLiAOntology oo =  new OLiAManager().getOLiAOntology("http://nachhalt.sfb632.uni-potsdam.de/owl/stts.owl");
        // OLiAOntology oo = new OLiAManager().getOLiAOntology("http://nachhalt.sfb632.uni-potsdam.de/owl/penn.owl");
        //OLiAOntology oo = new OLiAManager().getOLiAOntology("http://nachhalt.sfb632.uni-potsdam.de/owl/penn-link.rdf");
        //OLiAOntology oo = new OLiAManager().getOLiAOntology("http://purl.oclc.org/olia/penn-link.rdf");
        OLiAOntology oo = new OLiAManager().getOLiAOntology("http://purl.org/olia/penn-link.rdf");
        Set<String> uris = oo.getClassURIsForTag("CD");
        for (String s : uris) {
            oo.getHierarchy(s);
            //System.out.println(oo.getHierarchy(s));
        }

        uris = oo.getClassURIsForTag("DT");
        for (String s : uris) {
            oo.getHierarchy(s);
            //System.out.println(oo.getHierarchy(s));
        }

        //new OLiAOntology("http://nachhalt.sfb632.uni-potsdam.de/owl/penn-link.rdf");
    }
}
