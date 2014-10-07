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

package org.nlp2rdf.implementation.spotlight;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

/**
 * A Wrapper for DBPedia Spotlight Author: Ciro Baron Neto This demo uses the
 * work cited here:
 * https://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki/Citation
 * #statistical
 */

public class SpotlightWrapper {
	private static Logger log = LoggerFactory.getLogger(SpotlightWrapper.class);

	// spotlight properties
	public Hashtable<String, List<String>> h = new Hashtable<>();
	String dbpediaResourceLink = "http://dbpedia.org/resource/";
	// String spotlightAPI = "http://spotlight.dbpedia.org/";
	String spotlightAPI = "http://spotlight.sztaki.hu:2222/";
	String confidence = "0.1";
	String support = "0";
	String policy = "whitelist";
	String disambiguator = "Default";

	public void processText(Individual context, OntModel inputModel,
			OntModel outputModel, NIFParameters nifParameters) {

		String contextString = context
				.getPropertyValue(
						NIFDatatypeProperties.isString
								.getDatatypeProperty(inputModel)).asLiteral()
				.getString();
		String prefix = nifParameters.getPrefix();
		URIScheme urischeme = nifParameters.getUriScheme();
		confidence = nifParameters.getOptions().valueOf("confidence")
				.toString();

		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit");
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
		// query spotlight
		querySpotlight(contextString);

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

				if (h.get(String.valueOf(wordSpan.getStart())) != null) {

					List<String> l = h.get(String.valueOf(wordSpan.getStart()));
					Iterator<String> iterator = l.iterator();
					while (iterator.hasNext()) {
						String uri = iterator.next();
						wordIndividual.addProperty(outputModel.getProperty(
								"http://www.w3.org/2005/11/its/rdf#",
								"taIdentRef"), outputModel
								.createResource(dbpediaResourceLink + uri));
					}
				}
				outputModel.setNsPrefix("itsrdf",
						"http://www.w3.org/2005/11/its/rdf#");
			}
		}
	}

	// Method to query spotlight webservice
	public Hashtable<String, List<String>> querySpotlight(String context) {
		// String context =
		// "Hong Kong (CNN) -- A week into paralyzing pro-democracy protests in Hong Kong, authorities and demonstrators are still at loggerheads. Both sides say they are open to talks, but each wants concessions from the other. A student group said Sunday that it would restart dialogue with the government if police do a better job of handling clashes between pro-democracy protesters and people opposed to the demonstrations. The protesters, many of them students, have blocked major highways in several key districts for the past week, challenging a decision by Beijing about how elections will work in the semiautonomous Chinese territory.";


		log.info("Querying API.");
		log.info("This service uses the paper: Improving Efficiency and Accuracy in Multilingual Entity Extraction, from Joachim Daiber and Max Jakob and Chris Hokamp and Pablo N. Mendes. More information can be found here: https://github.com/dbpedia-spotlight/dbpedia-spotlight/wiki/Citation#statistical");

		HttpClient client = new HttpClient();

		try {
			GetMethod getMethod = new GetMethod(spotlightAPI
					+ "rest/candidates?" + "confidence=" + confidence
					+ "&support=" + support + "&policy=" + policy
					+ "&disambiguator=" + disambiguator + "&text="
					+ URLEncoder.encode(context, "utf-8"));
			getMethod
					.addRequestHeader(new Header("Accept", "application/json"));

			// Execute the method.
			int statusCode = client.executeMethod(getMethod);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = getMethod.getResponseBody();

			JSONObject j = new JSONObject(new String(responseBody));
			// System.out.println(j);
			JSONObject annotation = new JSONObject(j
					.getJSONObject("annotation").toString());
			// System.out.println(annotation);

			// check if is only one object
			boolean isArray = true;
			try {
				JSONObject surface = annotation.getJSONObject("surfaceForm");
				isArray = false;
				String key = surface.getString("@offset");
				String value = surface.getJSONObject("resource").get("@uri")
						.toString();
				List<String> value2 = new ArrayList<String>();
				value2.add(value);

				// log.info("Adding value "+value+" for offset "+key);
				h.put(key, value2);

			} catch (Exception e) {
				e.getMessage();
			}

			if (isArray) {
				JSONArray surface = annotation.getJSONArray("surfaceForm");
				try {
					JSONArray resultJSON = new JSONArray(surface.toString());
					// System.out.println(resultJSON);
					for (int i = 0; i < resultJSON.length(); i++) {
						try {
							JSONObject entity = resultJSON.getJSONObject(i);

							// check if is an array of entities
							isArray = false;
							try {
								JSONArray resources = entity
										.getJSONArray("resource");
								isArray = true;
								List<String> value2 = new ArrayList<String>();
								for (int k = 0; k < resources.length(); k++) {
									JSONObject entity2 = resources
											.getJSONObject(k);
									// System.out.println("----" + entity2);
									String key = entity.getString("@offset");
									String value = entity2.getString("@uri");
									// log.info("Adding value "+value+" for offset "+key);
									value2.add(value);
									h.put(key, value2);
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}

							// if there is only one entity
							if (!isArray) {
								String key = entity.getString("@offset");
								String value = entity.getJSONObject("resource")
										.get("@uri").toString();

								List<String> value2 = new ArrayList<String>();
								value2.add(value);
								h.put(key, value2);
							}

						} catch (JSONException e) {
							System.out.println("JSON exception " + e);
						}
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return h;
	}

}
