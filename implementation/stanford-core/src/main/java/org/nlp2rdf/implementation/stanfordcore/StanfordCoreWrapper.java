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
import com.hp.hpl.jena.vocabulary.OWL;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.nlp2rdf.core.NIFDatatypeProperties;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.vocabularymodule.olia.OLiAVocabulary;
import org.nlp2rdf.vocabularymodule.olia.models.Penn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;


/**
 * The basic code was taken from the ClearTK Project
 * http://code.google.com/p/cleartk
 * who have written a UIMA wrapper.
 * The original file by Steven Bethard can be found here:
 * http://code.google.com/p/cleartk/source/browse/trunk/cleartk-stanford-corenlp/src/main/java/org/cleartk/stanford/StanfordCoreNLPAnnotator.java
 * Licence http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * @author Sebastian Hellmann - http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */

public class StanfordCoreWrapper {
    private static Logger log = LoggerFactory.getLogger(StanfordCoreWrapper.class);

    public void processText(String prefix, String context, URIScheme urischeme, OntModel model) {

        /**
         * Prepare Stanford
         **/
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        //props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        //props.put("annotators", "tokenize, ssplit, pos, lemma, parse, ner"); // ner,  dcoref");
        //props.put("annotators", "tokenize, ssplit, pos, lemma, parse"); // ner,  dcoref");
        props.put("annotators", "tokenize, ssplit, pos, lemma"); // ner,  dcoref");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // create an empty Annotation just with the given text
        Annotation document = new Annotation(context);

        // run all Annotators on this text
        pipeline.annotate(document);

        // these are all the sentences in this document
        // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        //get all the sentences and words and read it in an intermediate structure
        //NOTE: this can be greatly optimized of course
        // for now it is just simple and cheap to implement it like this
        TreeMap<Span, List<Span>> tokenizedText = new TreeMap<Span, List<Span>>();
        for (CoreMap sentence : sentences) {
            Span sentenceSpan = new Span(sentence.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class), sentence.get(CoreAnnotations.CharacterOffsetEndAnnotation.class));
            List<Span> wordSpans = new ArrayList<Span>();
            for (CoreLabel coreLabel : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                wordSpans.add(new Span(coreLabel.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class), coreLabel.get(CoreAnnotations.CharacterOffsetEndAnnotation.class)));
            }
            tokenizedText.put(sentenceSpan, wordSpans);
        }

        /**
         * Basic Model Setup
         **/
        //get parameters for the URIGenerator
        Text2RDF text2RDF = new Text2RDF();
        Individual contextIndividual = text2RDF.createContextIndividual(prefix, context, urischeme, model);

        text2RDF.generateNIFModel(prefix, context, urischeme, model, tokenizedText);


        // traversing the words in the current sentence
        // a CoreLabel is a CoreMap with additional token-specific methods
        for (CoreMap sentence : sentences) {


            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                Span wordSpan = new Span(token.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class), token.get(CoreAnnotations.CharacterOffsetEndAnnotation.class));
                //the word should exist already
                Individual wordIndividual = model.getIndividual(urischeme.generate(prefix, context, wordSpan));

                if (wordIndividual == null) {
                    log.error("SKIPPING: word was not found in the model: " + urischeme.generate(prefix, context, wordSpan));
                    continue;
                }
                /********************************
                 * Lemma
                 ******/

                wordIndividual.addProperty(NIFDatatypeProperties.lemma.getDatatypeProperty(model), token.get(CoreAnnotations.LemmaAnnotation.class));

                /********************************
                 * POS tag
                 ******/

                // this is the POS tag of the token
                String posTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                List<String> oliaIndividual = (List<String>) Penn.hasTag.get(posTag);
                List<String> oliaClasses = (List<String>) Penn.hasTag.get(posTag);

                for (String s : oliaIndividual) {
                    wordIndividual.addProperty(OLiAVocabulary.getOliaIndividualProperty(model), model.createIndividual(oliaIndividual.get(0), OWL.Thing));
                }

                for (String s : oliaClasses) {
                    wordIndividual.addProperty(OLiAVocabulary.getOliCategoryProperty(model), s);
                }
            }
            // word.addPosTag(posTag);
            //String oliaIndividual = null;
            //if ((oliaIndividual = penn.getIndividualURIForTag(posTag)) != null) {
            //    word.addOliaLink(Thing.create(oliaIndividual, model));
            //}

            //adding pos classes from olia and olia-top
            /* THIS WAS MOVED TO AN EXTRA MODULE
            Set<String> classes = penn.getClassURIsForTag(posTag);
            for (String classUri : classes) {
                log.info("found: " + classUri + " for: " + posTag);
                OntModel hierarchy = penn.getHierarchy(classUri);
                for (ExtendedIterator<OntClass> it = hierarchy.listClasses(); it.hasNext(); ) {
                    OntClass oc = it.next();
                //use all classes
                //if (oc.getURI().startsWith("http://purl.org/olia/olia-top.owl") || oc.getURI().startsWith("http://purl.org/olia/olia.owl")) {
                    w.addOntClass(diff.createResource(oc.getURI()));
                //}
                }
                //Copy the hierarchy
                diff.add(hierarchy);
            }
            */

        }//end token
    }
}


