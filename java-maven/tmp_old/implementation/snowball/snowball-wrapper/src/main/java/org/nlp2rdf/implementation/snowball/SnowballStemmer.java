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
import eu.lod2.nlp2rdf.schema.sso.StopWord;
import eu.lod2.nlp2rdf.schema.sso.Word;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.URIGenerator;
import org.nlp2rdf.implementation.opennlp.OpenNLPTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tartarus.snowball.SnowballProgram;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * A Wrapper for Tartarus' Snowball Stemmer.
 * The name of a class from org.tartarus.stanfordcore.ext.  can be given to initialize the stemmer
 * see: http://lucene.apache.org/java/2_4_0/api/contrib-stanfordcore/index.html
 * <p/>
 * This decorator attaches the stem to each :Word it finds.
 * <p/>
 * User: Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */
public class SnowballStemmer extends SnowballProgram {
    private static Logger log = LoggerFactory.getLogger(SnowballStemmer.class);

    public SnowballProgram decoratee;
    private final OpenNLPTokenizer openNLPTokenizer;


    private final Set<String> stopWords = new HashSet<String>(Arrays.asList(new String[]{"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its", "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing", "would", "should", "could", "ought", "i'm", "you're", "he's", "she's", "it's", "we're", "they're", "i've", "you've", "we've", "they've", "i'd", "you'd", "he'd", "she'd", "we'd", "they'd", "i'll", "you'll", "he'll", "she'll", "we'll", "they'll", "isn't", "aren't", "wasn't", "weren't", "hasn't", "haven't", "hadn't", "doesn't", "don't", "didn't", "won't", "wouldn't", "shan't", "shouldn't", "can't", "cannot", "couldn't", "mustn't", "let's", "that's", "who's", "what's", "here's", "there's", "when's", "where's", "why's", "how's", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very"}));

    /**
     * For the English PorterStemmer
     */
    public SnowballStemmer() {
        this("PorterStemmer");

    }

    /**
     * @param stemmerClass a class from the following list http://lucene.apache.org/java/2_4_0/api/contrib-stanfordcore/index.html
     */
    public SnowballStemmer(String stemmerClass) {
        openNLPTokenizer = new OpenNLPTokenizer();
        try {
            decoratee = (SnowballProgram) Class.forName("org.tartarus.stanfordcore.ext." + stemmerClass).newInstance();
        } catch (Exception e) {
            String msg = "Correct class was not given please use e.g. \"PorterStemmer\"  from: http://lucene.apache.org/java/2_4_0/api/contrib-stanfordcore/index.html\n" + "Received: " + stemmerClass + " transformed to org.tartarus.stanfordcore.ext." + stemmerClass;
            log.error(msg, e);
            throw new InvalidParameterException(msg);
        }

    }

    public void processText(String prefix, URIGenerator urigenerator, String text, OntModel model) {
        TreeMap<Span, List<Span>> tokenizedText = openNLPTokenizer.tokenizeText(text);
        Text2RDF text2RDF = new Text2RDF();
        Individual context = text2RDF.createDocumentAnnotation(prefix, text, urigenerator, model);
        text2RDF.generateNIFModel(prefix, text, tokenizedText, urigenerator, context, model);
        processNIFModel(model);
        //add additional data
        new Text2RDF().addNextAndPreviousProperties(prefix, text, urigenerator, model);
    }

    public void processNIFModel(OntModel model) {
        for (Word w : Word.list(model)) {
            try {
                w.addStem(stem(w.getAnchorOf()).toLowerCase());
                if (stopWords.contains(w.getAnchorOf())) {
                    StopWord.create(w.getURI(), model);
                }

            } catch (Exception e) {
                log.warn("Stemming failed for " + w.getAnchorOf() + ", " + w.getURI(), e);
            }
        }
    }


    public String stem(String token) {
        decoratee.setCurrent(token);
        decoratee.stem();
        return decoratee.getCurrent();
    }

    @Override
    public boolean stem() {
        return decoratee.stem();
    }


}
