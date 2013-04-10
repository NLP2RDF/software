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

import org.nlp2rdf.ontology.ClasspathLoader;
import org.nlp2rdf.ontology.OntologyLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class OLiAManager {

    private Map<String, OLiAOntology> url2OLiAOntology = new HashMap<String, OLiAOntology>();

    private OntologyLoader ontologyLoader = new ClasspathLoader();

    public OLiAManager() {
    }

    public OLiAManager(OntologyLoader ontologyLoader) {
        this.ontologyLoader = ontologyLoader;
    }

    public OLiAOntology getOLiAOntology(String ontologyUrl) {
        OLiAOntology oo;
        if ((oo = url2OLiAOntology.get(ontologyUrl)) != null) {
            return oo;
        }
        oo = new OLiAOntology(ontologyUrl, ontologyLoader);
        url2OLiAOntology.put(ontologyUrl, oo);
        return oo;
    }


    public OntologyLoader getOntologyLoader() {
        return ontologyLoader;
    }

    public void setOntologyLoader(OntologyLoader ontologyLoader) {
        this.ontologyLoader = ontologyLoader;
    }
}
