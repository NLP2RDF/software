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

package org.nlp2rdf.webservice;

/**
 * User: Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class TestNIFServices {
    public static String base = "http://nlp2rdf.lod2.eu/NIFConverter/";
    public static String text = "This is a sentence. ";

    /*@Test
    public void testNIFConverter() throws Exception {
        NIFConverter nc = new NIFConverter();

        OntModel model = new Text2RDF().processAsDocument(base, text, new FakeTokenizer(), new MD5Based());
        NIFParameters np = new NIFParameters(text, base, null, "offset", model, null);
        OntModel diff = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        nc.execute(np, diff);
    }


    @Test
    public void testNIFStemmer() throws Exception {
        NIFStemmer nc = new NIFStemmer();
        Map<String, String> param = new HashMap<String, String>();
        param.put("stemmer", "Porter");

        OntModel model = new Text2RDF().processAsDocument(base, text, new FakeTokenizer(), new MD5Based());
        NIFParameters np = new NIFParameters(text, base, null, "offset", model, param);
        OntModel diff = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        nc.execute(np, diff);
        Assert.assertTrue(diff.size() == 10);
    } */
}
