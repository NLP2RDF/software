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

package org.nlp2rdf.core.impl;

import org.junit.Test;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.impl.MD5Based;
import org.nlp2rdf.core.impl.OffsetBased;

import java.util.HashMap;

/**
 * User: Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class TestUriGenerators {

    public static final HashMap<String, String> texts = new HashMap<String, String>();


    static {
        texts.put("simple", "Other than that, we are unsure. \n");
        texts.put("difficult", "Doctor of Philosophy, abbreviated Ph.D. or D.Phil. in English-speaking countries and Dr. Phil. or similar in other countries, for the Latin philosophiae doctor, meaning \"teacher in philosophy\", is an advanced academic degree awarded by universities. \n");

        texts.put("ambigue", "A rose is a rose is a rose is a rose is a rose. ");
        texts.put("three", "Doctor of Philosophy, abbreviated Ph.D. or D.Phil. in English-speaking countries and Dr. Phil. or similar in other countries, for the Latin philosophiae doctor, meaning \"teacher in philosophy\", is an advanced academic degree awarded by universities. \n" +
                "Other than that, we are unsure. \n" +
                "A rose is a rose is a rose is a rose is a rose. ");
        texts.put("gibberish", "dfsldfslk kjdjkf kfjkjk");
        texts.put("one_word", "Yeah");
    }


    @Test
    public void testURIGeneration() {
        String base = "http://example.org/test/";
        //String mydocument = "http://example.org/test/doc";
        for (String key : texts.keySet()) {
            //new Text2RDF().process(base, texts.get(key), new OpenNLPTokenizer(), new MD5Based());
            //new Text2RDF().process(base, texts.get(key), new OpenNLPTokenizer(), new OffsetBased());
        }
        //new Text2RDF().process(base, texts.get("simple"), new MD5Based());
        //log.info(m.makeUri("http://example.org/test/", text, new Span(0, 10)));
    }

     @Test
    public void testSpans() {
        String base = "http://example.org/test/";
        //String mydocument = "http://example.org/test/doc";
        /*for (String key : texts.keySet()) {
            new Text2RDF().process(base, texts.get(key), new FakeTokenizer(), new MD5Based());
            new Text2RDF().process(base, texts.get(key), new FakeTokenizer(), new OffsetBased());
        } */
        //new Text2RDF().process(base, texts.get("simple"), new MD5Based());
        //log.info(m.makeUri("http://example.org/test/", text, new Span(0, 10)));
    }

}
