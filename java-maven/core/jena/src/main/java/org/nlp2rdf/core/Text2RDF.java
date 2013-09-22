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
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.NIFObjectProperties;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Sebastian Hellmann
 */
public class Text2RDF {
    private static Logger log = LoggerFactory.getLogger(Text2RDF.class);

    public Individual createContextIndividual(String prefix, String contextString, URIScheme uriScheme, OntModel model) {
        Span span = new Span(0, contextString.length());
        String uri = uriScheme.generate(prefix, contextString, span);
        Individual context = model.createIndividual(uri, model.createClass(uriScheme.getOWLClassURI()));
        context.addOntClass(NIFOntClasses.Context.getOntClass(model));
        context.addLiteral(NIFDatatypeProperties.isString.getDatatypeProperty(model), model.createLiteral(contextString));
        context.addProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(model), span.getStart() + "");
        context.addProperty(NIFDatatypeProperties.endIndex.getDatatypeProperty(model), span.getEnd() + "");
        return context;
    }


    public Individual createCStringIndividual(String prefix, Individual context, Span span, URIScheme uriScheme, OntModel model) {
        String contextString = context.getPropertyValue(NIFDatatypeProperties.isString.getDatatypeProperty(model)).asLiteral().getString();
        String uri = uriScheme.generate(prefix, contextString, new Span[]{span});
        Individual string = model.createIndividual(uri, model.createClass(uriScheme.getOWLClassURI()));
        string.addLiteral(NIFDatatypeProperties.anchorOf.getDatatypeProperty(model), model.createLiteral(span.getCoveredText(contextString).toString()));
        string.addProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(model), span.getStart() + "");
        string.addProperty(NIFDatatypeProperties.endIndex.getDatatypeProperty(model), span.getEnd() + "");
        string.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(model), context);
        return string;
    }


    public void generateNIFModel(String prefix, Individual context, URIScheme uriScheme, OntModel model, TreeMap<Span, List<Span>> tokenizedText) {
        assert tokenizedText != null && context != null && uriScheme != null && prefix != null;

        String contextString = context.getPropertyValue(NIFDatatypeProperties.isString.getDatatypeProperty(model)).asLiteral().getString();

        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor("generateNIFModel").start();

        int wordCount = 0;
        try {
            for (Span sentenceSpan : tokenizedText.descendingKeySet()) {
                //String sentenceUri = uriScheme.generate(prefix, contextString, sentenceSpan);
                Individual sentence = createCStringIndividual(prefix, context, sentenceSpan, uriScheme, model);
                sentence.addOntClass(NIFOntClasses.Sentence.getOntClass(model));

                //detect words
                List<Span> wordSpans = new ArrayList<Span>(tokenizedText.get(sentenceSpan));
                wordCount += wordSpans.size();
                for (int i = 0; i < wordSpans.size(); i++) {
                    Span wordSpan = wordSpans.get(i);
                    Individual wordIndividual = createCStringIndividual(prefix, context, wordSpan, uriScheme, model);
                    wordIndividual.addOntClass(NIFOntClasses.Word.getOntClass(model));
                    wordIndividual.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(model), context);
                    wordIndividual.addProperty(NIFObjectProperties.sentence.getObjectProperty(model), sentence);

                    if (log.isTraceEnabled()) {
                        StringBuilder logging = new StringBuilder();
                        logging.append("\nword: " + wordSpan.getCoveredText(contextString));
                        logging.append("\nabsolute sentence position [start|end]: " + sentenceSpan.getStart() + "|" + sentenceSpan.getEnd());
                        logging.append("\nabsolute word position [start|end]: " + wordSpan.getStart() + "|" + wordSpan.getEnd());
                        log.trace(logging.toString());
                    }
                }
            }
        } finally {
            mon.stop();
        }
    }


}
