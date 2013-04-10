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

package org.nlp2rdf.implementation.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.nlp2rdf.core.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 */
public class OpenNLPTokenizer {
    private static Logger log = LoggerFactory.getLogger(OpenNLPTokenizer.class);
    public static final String RESOURCEPATH = "org/nlp2rdf/implementation/opennlp/";

    //the model is threadsafe according to the javadoc
    private static TokenizerModel tokenizerModel = null;
    private static SentenceModel sentenceModel = null;

    private TokenizerME tokenizerME = null;
    private SentenceDetectorME sentenceDetectorME = null;

    public OpenNLPTokenizer() {
        tokenizerME = new TokenizerME(getTokenizerModel());
        sentenceDetectorME = new SentenceDetectorME(getSentenceModel());
    }

    public TreeMap<Span, List<Span>> tokenizeText(String text) {
        //get all the sentences and words
        TreeMap<Span, List<Span>> tokenizedText = new TreeMap<Span, List<Span>>();
        for (Span sentenceSpan : this.detectSentences(text)) {
            List<Span> wordSpans = new ArrayList<Span>();
            String sentenceText = sentenceSpan.getCoveredText(text).toString();
            for (Span wordSpan : this.detectWords(sentenceText)) {
                wordSpans.add(new Span(wordSpan, sentenceSpan.getStart()));
            }
            tokenizedText.put(sentenceSpan, wordSpans);
        }
        return tokenizedText;
    }

    public synchronized Span[] detectSentences(String text) {
        opennlp.tools.util.Span[] spans = sentenceDetectorME.sentPosDetect(text);
        SpanDecorator[] ret = new SpanDecorator[spans.length];
        for (int i = 0; i < spans.length; i++) {
            ret[i] = new SpanDecorator(spans[i]);
        }
        return ret;
    }

    public synchronized Span[] detectWords(String sentence) {
        //the Tokenizer is not Thread-safe!
        opennlp.tools.util.Span[] spans = tokenizerME.tokenizePos(sentence);
        SpanDecorator[] ret = new SpanDecorator[spans.length];
        for (int i = 0; i < spans.length; i++) {
            ret[i] = new SpanDecorator(spans[i]);
        }
        return ret;
    }


    private SentenceModel getSentenceModel() {
        if (sentenceModel == null) {
            try {
                InputStream modelIn = this.getClass().getClassLoader().getResourceAsStream(RESOURCEPATH + "en-sent.bin");
                try {
                    sentenceModel = new SentenceModel(modelIn);
                } finally {
                    modelIn.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }
        return sentenceModel;
    }


    private TokenizerModel getTokenizerModel
            () {
        if (tokenizerModel == null) {
            try {
                InputStream modelIn = this.getClass().getClassLoader().getResourceAsStream(RESOURCEPATH + "en-token.bin");
                try {
                    tokenizerModel = new TokenizerModel(modelIn);
                } finally {
                    modelIn.close();
                }
            } catch (IOException e) {
                log.error("", e);
            }
        }

        return tokenizerModel;
    }

}
