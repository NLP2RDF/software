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
import org.nlp2rdf.core.urischemes.URISchemeHelper;
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

    public Individual createContextIndividual(String prefix, String context, URIScheme uriScheme, OntModel model) {
        Individual contextIndividual = createNIFIndividual(prefix, context, new Span(0, context.length()), uriScheme, model);
        contextIndividual.addOntClass(NIFOntClasses.Context.getOntClass(model));
        contextIndividual.addLiteral(NIFDatatypeProperties.isString.getDatatypeProperty(model), context);
        return contextIndividual;
    }

    public static Individual createNIFIndividual(String prefix, String context, Span span, URIScheme uriScheme, OntModel model) {
        return createNIFIndividual(prefix, context, new Span[]{span}, uriScheme, model);
    }

    public static Individual createNIFIndividual(String prefix, String context, Span[] spans, URIScheme uriScheme, OntModel model) {
        //make the uri and add the class for the URI recipe
        String uri = uriScheme.generate(prefix, context, spans);
        Individual string = model.createIndividual(uri, NIFOntClasses.String.getOntClass(model));
        string.addOntClass(model.createClass( uriScheme.getOWLClassURI()));
        for (String text : URISchemeHelper.getCoveredTexts(spans, context)) {
            string.addLiteral(NIFDatatypeProperties.anchorOf.getDatatypeProperty(model), text);
        }
        return string;
    }

    public OntModel generateNIFModel(String prefix, String context, URIScheme uriScheme, OntModel model, TreeMap<Span, List<Span>> tokenizedText) {
        assert tokenizedText != null && context != null && uriScheme != null && prefix != null;

        Individual contextIndividual = createContextIndividual(prefix, context, uriScheme, model);

        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor("generateNIFModel").start();

        int wordCount = 0;
        try {
            for (Span sentenceSpan : tokenizedText.descendingKeySet()) {
                String sentenceUri = uriScheme.generate(prefix, context, sentenceSpan);
                Individual sentenceIndividual = createNIFIndividual(prefix, context, sentenceSpan, uriScheme, model);
                sentenceIndividual.addOntClass(NIFOntClasses.Sentence.getOntClass(model));
                sentenceIndividual.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(model), context);

                //detect words
                List<Span> wordSpans = new ArrayList<Span>(tokenizedText.get(sentenceSpan));
                wordCount += wordSpans.size();
                for (int i = 0; i < wordSpans.size(); i++) {
                    Span wordSpan = wordSpans.get(i);
                    Individual wordIndividual = createNIFIndividual(prefix, context, wordSpan, uriScheme, model);
                    wordIndividual.addOntClass(NIFOntClasses.Word.getOntClass(model));
                    wordIndividual.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(model), context);
                    wordIndividual.addProperty(NIFObjectProperties.sentence.getObjectProperty(model), sentenceIndividual);

                    if (log.isTraceEnabled()) {
                        StringBuilder logging = new StringBuilder();
                        logging.append("\nword: " + wordSpan.getCoveredText(context));
                        logging.append("\nabsolute sentence position [start|end]: " + sentenceSpan.getStart() + "|" + sentenceSpan.getEnd());
                        logging.append("\nabsolute word position [start|end]: " + wordSpan.getStart() + "|" + wordSpan.getEnd());
                        log.trace(logging.toString());
                    }
                }
            }
            return model;
        } finally {
            mon.stop();
            log.debug("Finished creating " + tokenizedText.size() + " sentence with " + wordCount + " words, " + mon.getLastValue() + " ms.) ");
        }
    }


}
