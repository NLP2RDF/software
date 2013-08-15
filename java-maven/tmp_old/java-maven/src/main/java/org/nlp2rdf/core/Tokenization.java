package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import eu.lod2.nlp2rdf.schema.sso.Sentence;
import eu.lod2.nlp2rdf.schema.sso.Word;
import org.nlp2rdf.core.urischemes.URIScheme;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * User: hellmann
 * Date: 13.02.13
 */
public class Tokenization {


    public OntModel addTokenization(Individual context,  TreeMap<Span, List<Span>> tokenizedText, URIScheme uriGenerator, OntModel model) {
        ObjectProperty referenceContext = model.createObjectProperty(stringOntologyUrl + "referenceContext");
    }


    /**
     * adds sso:Sentence and sso:Word to it
     * adds the word property and additionally, though optional, the firstWord and lastWord property
     * <p/>
     * Note that the expected tokenizer matches the opennlp tokenizer well,
     * so instead of forcing a tokenizer to match the interface it might be smarter to rewrite this method.
     *
     * @param prefix
     * @param text
     * @param tokenizedText
     * @param uriGenerator
     * @param context       use null if you want to opt out
     * @param model
     * @return the model filled with NIF
     */
    public OntModel generateNIFModel(String prefix, String text, TreeMap<Span, List<Span>> tokenizedText, URIGenerator uriGenerator, Individual context, OntModel model) {
        assert tokenizedText != null && text != null && uriGenerator != null && prefix != null;
        ObjectProperty referenceContext = model.createObjectProperty(stringOntologyUrl + "referenceContext");

        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor("generateBasicNIFModel").start();
        int wordCount = 0;
        try {
            //set basic prefixes
            model.setNsPrefix("sso", structuredSentenceOntologyUrl);
            model.setNsPrefix("str", stringOntologyUrl);
            for (Span sentenceSpan : tokenizedText.descendingKeySet()) {
                Sentence sentence = createStringAnnotationForClass(Sentence.class, prefix, text, sentenceSpan, uriGenerator, model);
                //assign str:substring to document
                if (context != null) {
                    model.getIndividual(sentence.getURI()).addProperty(referenceContext, context);
                    //document.addSubString(sentence);

                }
                //System.exit(0);

                //detect words
                List<Span> wordSpans = new ArrayList<Span>(tokenizedText.get(sentenceSpan));
                wordCount += wordSpans.size();
                for (int i = 0; i < wordSpans.size(); i++) {
                    Span wordSpan = wordSpans.get(i);
                    // Span absoluteWordSpan = new Span(wordSpan, sentenceSpan.getStart());
                    Word word = createStringAnnotationForClass(Word.class, prefix, text, wordSpan, uriGenerator, model);
                    //add the firstWord property (optional and redundant to sso:word)
                    if (i == 0) {
                        //excluded for NIF 2.0
                        //sentence.setFirstWord(word);
                    }
                    //add the lasttWord property (optional and redundant to sso:word)
                    if (i == (wordSpans.size() - 1)) {
                        //excluded for NIF 2.0
                        //sentence.setLastWord(word);
                    }
                    //this is important for the str:subStringTrans inference
                    //excluded for NIF 2.0
                    //sentence.addWord(word);
                    model.getIndividual(word.getURI()).addProperty(referenceContext, context);

                    if (log.isTraceEnabled()) {
                        StringBuilder logging = new StringBuilder();
                        logging.append("\nword: " + wordSpan.getCoveredText(text));
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


    /*public void addAdditionalProperties(String prefix, String text, TreeMap<Span, Span[]> sentencesAndWords, URIGenerator uriGenerator, OntModel m) {

        List<IString> sentences = new ArrayList<IString>(Sentence.list(m));
        List<Span> spans = URIGeneratorHelper.getSpans(sentences, prefix, text, uriGenerator);
        Collections.sort(spans, new Comparator<Span>() {
            @Override
            public int compare(Span span, Span span1) {
                return span.compareTo(span1);
            }
        });
    } */


    public TreeMap<Span, List<Span>> getTokenization(String prefix, String text, URIGenerator uriGenerator, OntModel model) {
        TreeMap<Span, List<Span>> tokenizedText = new TreeMap<Span, List<Span>>();

        for (Sentence sentence : Sentence.list(model)) {
            Span sentenceSpan = uriGenerator.getSpanFor(prefix, sentence.getURI(), text);
            List<Span> wordSpans = new ArrayList<Span>();
            for (Word word : sentence.listWord()) {
                Span wordSpan = uriGenerator.getSpanFor(prefix, word.getURI(), text);
                wordSpans.add(wordSpan);
            }
            tokenizedText.put(sentenceSpan, wordSpans);
        }
        return tokenizedText;
    }
}
