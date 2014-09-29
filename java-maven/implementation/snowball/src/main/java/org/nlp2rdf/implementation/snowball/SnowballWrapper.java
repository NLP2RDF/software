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

package org.nlp2rdf.implementation.snowball;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.ext.EnglishStemmer;
import org.tartarus.snowball.ext.PorterStemmer;

import java.io.StringReader;
import java.security.InvalidParameterException;
import java.util.*;

/**
 * A Wrapper for Tartarus' Snowball Stemmer. The name of a class from
 * org.tartarus.stanfordcore.ext. can be given to initialize the stemmer see:
 * http://lucene.apache.org/java/2_4_0/api/contrib-stanfordcore/index.html
 * <p/>
 * This decorator attaches the stem to each :Word it finds.
 * <p/>
 * User: Sebastian Hellmann -
 * http://bis.informatik.uni-leipzig.de/SebastianHellmann
 */

public class SnowballWrapper {
	private static Logger log = LoggerFactory.getLogger(SnowballWrapper.class);

	/**
	 * For the English PorterStemmer
	 */
	public SnowballWrapper() {
		this("PorterStemmer");

	}

	/**
	 * @param stemmerClass
	 *            a class from the following list
	 *            http://lucene.apache.org/java/2_4_0
	 *            /api/contrib-stanfordcore/index.html
	 */
	public SnowballWrapper(String stemmerClass) {
		// openNLPTokenizer = new OpenNLPTokenizer();
		// try {
		// decoratee = (SnowballProgram)
		// Class.forName("org.tartarus.stanfordcore.ext." +
		// stemmerClass).newInstance();
		// } catch (Exception e) {
		// String msg =
		// "Correct class was not given please use e.g. \"PorterStemmer\"  from: http://lucene.apache.org/java/2_4_0/api/contrib-stanfordcore/index.html\n"
		// + "Received: " + stemmerClass +
		// " transformed to org.tartarus.stanfordcore.ext." + stemmerClass;
		// log.error(msg, e);
		// throw new InvalidParameterException(msg);
		// }

	}

	private final Set<String> stopWords = new HashSet<String>(
			Arrays.asList(new String[] { "i", "me", "my", "myself", "we",
					"our", "ours", "ourselves", "you", "your", "yours",
					"yourself", "yourselves", "he", "him", "his", "himself",
					"she", "her", "hers", "herself", "it", "its", "itself",
					"they", "them", "their", "theirs", "themselves", "what",
					"which", "who", "whom", "this", "that", "these", "those",
					"am", "is", "are", "was", "were", "be", "been", "being",
					"have", "has", "had", "having", "do", "does", "did",
					"doing", "would", "should", "could", "ought", "i'm",
					"you're", "he's", "she's", "it's", "we're", "they're",
					"i've", "you've", "we've", "they've", "i'd", "you'd",
					"he'd", "she'd", "we'd", "they'd", "i'll", "you'll",
					"he'll", "she'll", "we'll", "they'll", "isn't", "aren't",
					"wasn't", "weren't", "hasn't", "haven't", "hadn't",
					"doesn't", "don't", "didn't", "won't", "wouldn't",
					"shan't", "shouldn't", "can't", "cannot", "couldn't",
					"mustn't", "let's", "that's", "who's", "what's", "here's",
					"there's", "when's", "where's", "why's", "how's", "a",
					"an", "the", "and", "but", "if", "or", "because", "as",
					"until", "while", "of", "at", "by", "for", "with", "about",
					"against", "between", "into", "through", "during",
					"before", "after", "above", "below", "to", "from", "up",
					"down", "in", "out", "on", "off", "over", "under", "again",
					"further", "then", "once", "here", "there", "when",
					"where", "why", "how", "all", "any", "both", "each", "few",
					"more", "most", "other", "some", "such", "no", "nor",
					"not", "only", "own", "same", "so", "than", "too", "very" }));

	public void processText2(String prefix, Individual context,
			URIScheme urischeme, OntModel model) {
		String contextString = context
				.getPropertyValue(
						NIFDatatypeProperties.isString
								.getDatatypeProperty(model)).asLiteral()
				.getString();
		/**
		 * model.listIndividuals(NIFOntClasses) for (Word w : Word.list(model))
		 * { try { w.addStem(stem(w.getAnchorOf()).toLowerCase()); if
		 * (stopWords.contains(w.getAnchorOf())) { StopWord.create(w.getURI(),
		 * model); }
		 * 
		 * } catch (Exception e) { log.warn("Stemming failed for " +
		 * w.getAnchorOf() + ", " + w.getURI(), e); } }
		 **/
	}

	public SnowballProgram decoratee;

	/**
	 * public void processText(String prefix, URIGenerator urigenerator, String
	 * text, OntModel model) { TreeMap<Span, List<Span>> tokenizedText =
	 * openNLPTokenizer.tokenizeText(text); Text2RDF text2RDF = new Text2RDF();
	 * Individual context = text2RDF.createDocumentAnnotation(prefix, text,
	 * urigenerator, model); text2RDF.generateNIFModel(prefix, text,
	 * tokenizedText, urigenerator, context, model); processNIFModel(model);
	 * //add additional data new Text2RDF().addNextAndPreviousProperties(prefix,
	 * text, urigenerator, model); }
	 **/

	public void processText(Individual context, OntModel inputModel,
			OntModel outputModel, NIFParameters nifParameters) {

		String contextString = context
				.getPropertyValue(
						NIFDatatypeProperties.isString
								.getDatatypeProperty(inputModel)).asLiteral()
				.getString();
		String prefix = nifParameters.getPrefix();
		URIScheme urischeme = nifParameters.getUriScheme();

		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit"); // ner, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(contextString);
		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and
		// has values with custom types
		List<CoreMap> sentences = document
				.get(CoreAnnotations.SentencesAnnotation.class);

		// get all the sentences and words and read it in an intermediate
		// structure
		// NOTE: this can be greatly optimized of course
		// for now it is just simple and cheap to implement it like this
		int wordCount = 0;
		TreeMap<Span, List<Span>> tokenizedText = new TreeMap<Span, List<Span>>();
		for (CoreMap sentence : sentences) {
			Span sentenceSpan = new Span(
					sentence.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class),
					sentence.get(CoreAnnotations.CharacterOffsetEndAnnotation.class));
			List<Span> wordSpans = new ArrayList<Span>();
			for (CoreLabel coreLabel : sentence
					.get(CoreAnnotations.TokensAnnotation.class)) {
				wordSpans
						.add(new Span(
								coreLabel
										.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class),
								coreLabel
										.get(CoreAnnotations.CharacterOffsetEndAnnotation.class)));
				wordCount++;
			}
			tokenizedText.put(sentenceSpan, wordSpans);
		}

		Text2RDF t = new Text2RDF();
		t.generateNIFModel(prefix, context, urischeme, inputModel,
				tokenizedText);
		outputModel.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(),
				"Finished creating " + tokenizedText.size()
						+ " sentence(s) with " + wordCount + " word(s) ",
				RLOGIndividuals.DEBUG, this.getClass().getCanonicalName(),
				null, null));

		// traversing the words in the current sentence
		// a CoreLabel is a CoreMap with additional token-specific methods
		for (CoreMap sentence : sentences) {

			for (CoreLabel token : sentence
					.get(CoreAnnotations.TokensAnnotation.class)) {
				Span wordSpan = new Span(
						token.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class),
						token.get(CoreAnnotations.CharacterOffsetEndAnnotation.class));
				// the word should exist already
				Individual wordIndividual = outputModel.getIndividual(urischeme
						.generate(prefix, contextString, wordSpan));

				if (wordIndividual == null) {
					log.error("SKIPPING: word was not found in the model: "
							+ urischeme.generate(prefix, contextString,
									wordSpan));
					continue;
				}

				/********************************
				 * Stem
				 ******/
				// EnglishStemmer stem = new EnglishStemmer();

				String word = wordSpan.getCoveredText(contextString).toString()
						.toLowerCase();

				PorterStemmer stem = new PorterStemmer();
				stem.setCurrent(word);
				stem.stem();

				String stemmedWord = stem.getCurrent();

				if (!(stopWords.contains(word)))
					if(!(word.equals(stemmedWord)))
					wordIndividual.addProperty(NIFDatatypeProperties.stem
							.getDatatypeProperty(outputModel), stemmedWord);
			}
		}
	}
}