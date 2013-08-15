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

package org.nlp2rdf.ontology.impl;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.apache.log4j.Logger;
import org.nlp2rdf.ontology.OntologyLoader;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class SimpleLoader implements OntologyLoader {
    private static final Logger log = Logger.getLogger(SimpleLoader.class);

    @Override
    public OntModel loadOntology(String ontologyUri) {
        return loadOntology(ontologyUri, OntModelSpec.OWL_DL_MEM);
    }

    @Override
    public OntModel loadOntology(String ontologyUri, OntModelSpec spec) {
        OntModel model = ModelFactory.createOntologyModel(spec);
        model.read(ontologyUri);
        return model;
    }

    @Override
    public void loadImports(OntModel m) {
        Set<String> loaded = new HashSet<String>();
        for (String one : m.listImportedOntologyURIs()) {
            if (loaded.add(one) == false) {
                log.debug("skipping " + one + " (already load)");
                continue;
            }
            OntModel sm = loadOntology(one);
            loadImports(sm);
            m.addSubModel(sm);
        }
    }
}
