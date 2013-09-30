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

package org.nlp2rdf.implementation.stanfordcore;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Test;
import org.nlp2rdf.core.urischemes.OffsetBasedString;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.implementation.stanfordcorenlp.StanfordWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 *         Created: 30.07.11
 */
public class StanfordTest {

    private static Logger logger = LoggerFactory.getLogger(StanfordWrapper.class);

    private static String expected =
            "<ModelCom   {"
                    + "http://purl.org/olia/penn.owl#NN @rdf:type owl:Thing; "
                    + "http://purl.org/olia/penn.owl#DT @rdf:type owl:Thing; "
                    + "http://nlp2rdf.lod2.eu/schema/nif/sentence @rdf:type owl:ObjectProperty; "
                    + "http://test/test/offset_18_19 @http://nlp2rdf.lod2.eu/schema/nif/module/olia/oliaCategory \"http://purl.org/olia/penn.owl#fullStop\"; "
                    + "http://test/test/offset_18_19 @http://nlp2rdf.lod2.eu/schema/nif/module/olia/oliaIndividual http://purl.org/olia/penn.owl#fullStop; "
                    + "http://test/test/offset_18_19 @http://nlp2rdf.lod2.eu/schema/nif/lemma \".\"; "
                    + "http://test/test/offset_18_19 @http://nlp2rdf.lod2.eu/schema/nif/sentence http://test/test/offset_0_19; "
                    + "http://test/test/offset_18_19 @http://nlp2rdf.lod2.eu/schema/nif/referenceContext \"This is a sentence. \"; "
                    + "http://test/test/offset_18_19 @rdf:type http://nlp2rdf.lod2.eu/schema/nif/Word; "
                    + "http://test/test/offset_18_19 @rdf:type http://nlp2rdf.lod2.eu/schema/nif/OffsetBasedString; "
                    + "http://test/test/offset_18_19 @rdf:type http://nlp2rdf.lod2.eu/schema/nif/String; "
                    + "http://nlp2rdf.lod2.eu/schema/nif/Sentence @rdf:type owl:Class; "
                    + "http://nlp2rdf.lod2.eu/schema/nif/String @rdf:type owl:Class; "
                    + "http://test/test/offset_5_7 @http://nlp2rdf.lod2.eu/schema/nif/module/olia/oliaCategory \"http://purl.org/olia/penn.owl#VBZ\"; "
                    + "http://test/test/offset_5_7 @http://nlp2rdf.lod2.eu/schema/nif/module/olia/oliaIndividual http://purl.org/olia/penn.owl#VBZ; "
                    + "http://test/test/offset_5_7 @http://nlp2rdf.lod2.eu/schema/nif/lemma \"be\"; "
                    + "http://test/test/offset_5_7 @http://nlp2rdf.lod2.eu/schema/nif/sentence http://test/test/offset_0_19..."
                    + "} | >";

    @Test
    public void testStanford() throws URISyntaxException, MalformedURLException {
        URL u = new URI("http://persistence.uni-leipzig.org/nlp2rdf/specification/example/david_lynch_dune_quoteid_124.txt").toURL();

        try {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(u.openStream()));

            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
                sb.append("\n");
            }
            in.close();
            System.out.println(sb.toString().length());
            sb.toString().length();

            //TODO
            OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            URIScheme uriScheme = new OffsetBasedString();

            // new StanfordCoreWrapper().processText("http://test/test/", "This is a sentence. ", uriScheme, m);
            logger.info(m.toString());
            //Assert.assertEquals(expected, m.toString());
        } catch (IOException ioe) {
            //if offline do nothing
        }
    }
}
