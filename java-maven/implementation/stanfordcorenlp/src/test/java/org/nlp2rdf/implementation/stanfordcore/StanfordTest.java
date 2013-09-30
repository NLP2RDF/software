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


import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.junit.Test;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.RFC5147String;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFOntClasses;
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
import java.util.HashMap;

/**
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 *         Created: 30.07.11
 */
public class StanfordTest {

    private static Logger logger = LoggerFactory.getLogger(StanfordWrapper.class);


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
            String input = sb.toString();
            int targetLength = input.length();
            OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            URIScheme uriScheme = new RFC5147String();
            String prefix =    "http://example.org/test/";
            Individual context = new Text2RDF().createContextIndividual(prefix,input,uriScheme,m);
            NIFParameters nifParameters = new NIFParameters(m, new HashMap<String, String>(),prefix, null, uriScheme, null, "turtle");


            new StanfordWrapper().processText(nifParameters.getPrefix(), context, uriScheme, m,nifParameters);
            //m.write(System.out, Format.toJena(nifParameters.getOutputFormat()));
           // logger.info(m.toString());
            //Assert.assertEquals(expected, m.toString());
        } catch (IOException ioe) {
            //if offline do nothing
        }
    }
}
