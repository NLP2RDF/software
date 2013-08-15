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

package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Test;
import org.nlp2rdf.core.impl.OffsetBased;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Sebastian Hellmann
 *         Date: 11/8/11
 */
public class TestText2RDF {

    @Test
    public void testTokenizer() {
        URIGenerator g = new OffsetBased();
        OntModel ret = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        TreeMap<Span, List<Span>> sentencesAndWords = new TreeMap<Span, List<Span>>();
        String text = "This is a sentence.This is a sentence.";
        sentencesAndWords.put(new Span(0, 19), Arrays.asList(new Span[]{new Span(0, 4), new Span(5, 7), new Span(8, 9), new Span(10, 18), new Span(18, 19)}));
        sentencesAndWords.put(new Span(19, 38), Arrays.asList(new Span[]{new Span(19, 23), new Span(24, 26), new Span(27, 28), new Span(29, 37), new Span(37, 38)}));
        Text2RDF t = new Text2RDF();
        Individual d = t.createDocumentAnnotation("", text, g, ret);
        t.generateNIFModel("", text, sentencesAndWords, g, d, ret);
    }
}
