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

package org.nlp2rdf;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Test;
import org.nlp2rdf.core.Text2RDF;

/**
 * This test validates the ontologies needed for NIF
 * especially the Content Negtiation done by the ontologies/nlp2rdf.lod2.eu.schema/.htaccess should be Jena compatible
 * <p/>
 * User: Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class OntologyValidation {

    @Test
    public void testOntologies() {
        downloadOntology(Text2RDF.stringOntologyUrl);
        downloadOntology(Text2RDF.structuredSentenceOntologyUrl);
    }

    public void downloadOntology(String ontUrl) {
        OntModel ret = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        ret.read(ontUrl);
    }

}
