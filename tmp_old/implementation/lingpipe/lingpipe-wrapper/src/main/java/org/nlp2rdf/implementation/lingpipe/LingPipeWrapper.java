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

package org.nlp2rdf.implementation.lingpipe;

import com.aliasi.hmm.HiddenMarkovModel;
import com.aliasi.hmm.HmmDecoder;
import com.aliasi.tag.ScoredTagging;
import com.aliasi.tag.Tagging;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.util.Streams;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import eu.lod2.nlp2rdf.schema.tools.Factory;
import org.nlp2rdf.ontology.olia.OLiAManager;
import org.nlp2rdf.ontology.olia.OLiAOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * @author
 */

public class LingPipeWrapper {
    private static Logger log = LoggerFactory.getLogger(LingPipeWrapper.class);
    private final OLiAOntology brown;
    private static final String MODEL_PATH = "tbsl/models/lingpipe/pos-en-general-brown.HiddenMarkovModel";
    private static final int TOP_K = 5;

    private HmmDecoder tagger;

    static {
        /***************************
         * Important requirement...
         */
        Factory.registerCustomClasses();
    }


    public LingPipeWrapper(OLiAManager oLiAManager) {
        brown = oLiAManager.getOLiAOntology("http://purl.org/olia/brown-link.rdf");
        try {
            InputStream fileIn = this.getClass().getClassLoader().getResourceAsStream(MODEL_PATH);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            HiddenMarkovModel hmm = (HiddenMarkovModel) objIn.readObject();
            Streams.closeQuietly(objIn);
            tagger = new HmmDecoder(hmm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /* execute this with
       //on project root
       mvn install
       cd implementation/lingpipe/lingpipewrapper
       mvn compile exec:java -e  -Dexec.mainClass="org.nlp2rdf.implementation.lingpipe.LingPipeWrapper"
     */

    public static void main(String[] args) {
        //OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        LingPipeWrapper lpw = new LingPipeWrapper(new OLiAManager());
        //lpw.processText("http://test/test/", "This is a sentence. ", "offset", m);
        String sentence = "Currently this tagger is only implemented to work with one sentence only, i.e. sentence splitting is missing!";
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println("********************************************************");
        System.out.println(lpw.tag(sentence));
        System.out.println("********************************************************");
        System.out.println(lpw.tagTopK(sentence));
    }


    public void processText(String prefix, String text, String urirecipe, OntModel diff) {


    }

    public String tag(String sentence) {
        com.aliasi.tokenizer.Tokenizer tokenizer = IndoEuropeanTokenizerFactory.INSTANCE.tokenizer(sentence.toCharArray(), 0, sentence.length());
//		Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(cs,0,cs.length);
        String[] tokens = tokenizer.tokenize();
        List<String> tokenList = Arrays.asList(tokens);
        Tagging<String> tagging = tagger.tag(tokenList);

        return tagging.toString();
    }

    public List<String> tagTopK(String sentence) {
        List<String> taggedSentences = new ArrayList<String>();

        com.aliasi.tokenizer.Tokenizer tokenizer = IndoEuropeanTokenizerFactory.INSTANCE.tokenizer(sentence.toCharArray(), 0, sentence.length());
//		Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(cs,0,cs.length);
        String[] tokens = tokenizer.tokenize();
        List<String> tokenList = Arrays.asList(tokens);
        Iterator<ScoredTagging<String>> taggingIter = tagger.tagNBest(tokenList, TOP_K);
        while (taggingIter.hasNext()) {
            taggedSentences.add(taggingIter.next().toString());
        }
        return taggedSentences;
    }


}


