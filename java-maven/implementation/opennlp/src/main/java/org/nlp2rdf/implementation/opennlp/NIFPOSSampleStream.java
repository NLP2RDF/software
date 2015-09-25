package org.nlp2rdf.implementation.opennlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.NIFObjectProperties;
import org.nlp2rdf.core.vocab.NIFOntClasses;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import opennlp.tools.postag.POSSample;
import opennlp.tools.util.FilterObjectStream;
import opennlp.tools.util.ObjectStream;

public class NIFPOSSampleStream extends FilterObjectStream<Resource, POSSample> {

	public NIFPOSSampleStream(ObjectStream<Resource> resources) {
		super(resources);
	}

	public POSSample read() throws IOException {

		Resource sentRes = samples.read();
		
		if (sentRes == null) {
			return null;
		}
		
		Model rdfModel = sentRes.getModel();
		POSSample sample = null;
		Property firstWordProp = rdfModel.createProperty(NIFObjectProperties.firstWord.getUri());
		Property nextWordProp = rdfModel.createProperty(NIFObjectProperties.nextWord.getUri());
		Property hasWordProp = rdfModel.createProperty(NIFObjectProperties.word.getUri());
		Property posProp = rdfModel.createProperty(NIFObjectProperties.oliaLink.getUri());
		Property anchorProp = rdfModel.createProperty(NIFDatatypeProperties.anchorOf.getUri());

		List<String> tokens = new ArrayList<String>(100);
		List<String> tags = new ArrayList<String>(100);

		Resource wordResource = sentRes.getPropertyResourceValue(firstWordProp);
		if (wordResource != null) {
			tokens.add(wordResource.getProperty(anchorProp).getString());
			tags.add(wordResource.getProperty(posProp).getResource().getLocalName());

			// traversing all words of the sentence
			while (true) {
				wordResource = wordResource.getPropertyResourceValue(nextWordProp);
				if (wordResource != null) {
					tokens.add(wordResource.getProperty(anchorProp).getString());
					tags.add(wordResource.getProperty(posProp).getResource()
					    .getLocalName());
				} else {
					break;
				}
			}
			sample = new POSSample(tokens, tags);
			return sample;

		} else {
			// firstWord not given, trying if single words are annotated
			List<Statement> wordStatements = sentRes.listProperties(hasWordProp)
			    .toList();
			if (wordStatements.size() != 0) {
				sample = getPOSSampleFromWordStatement(wordStatements,rdfModel);
				return sample;
			} else {
				return null;
			}
		}
	}

	// resources are not in order, so they are ordered here
	// TODO: proof if tags exist
	private POSSample getPOSSampleFromWordStatement(List<Statement> words, Model rdfModel) {

		Property posLinkProp = rdfModel.createProperty(NIFObjectProperties.oliaLink.getUri());
		Property posTagProp = rdfModel.createProperty(NIFDatatypeProperties.posTag.getUri());
		Property anchorProp = rdfModel
		    .createProperty(NIFDatatypeProperties.anchorOf.getUri());
		Property beginProp = rdfModel.createProperty(NIFDatatypeProperties.beginIndex.getUri());
		Map<Integer, Resource> wordMap = new HashMap<Integer, Resource>();

		for (Statement wordStmt : words) {
			Resource wordRes = wordStmt.getObject().asResource();
			Integer startIndex = Integer.valueOf(wordRes.getProperty(beginProp)
			    .getString());
			wordMap.put(startIndex, wordRes);
		}
		List<Integer> sortedIndexes = new ArrayList<Integer>();
		sortedIndexes.addAll(wordMap.keySet());
		Collections.sort(sortedIndexes);

		List<String> tokens = new ArrayList<String>(100);
		List<String> tags = new ArrayList<String>(100);

		for (Integer startIndex : sortedIndexes) {
			Resource wordRes = wordMap.get(startIndex);
			tokens.add(wordRes.getProperty(anchorProp).getString());
			if(wordRes.getProperty(posLinkProp)!=null) {
				tags.add(wordRes.getProperty(posLinkProp).getResource().getLocalName());
			} 
			if(wordRes.getProperty(posTagProp)!=null) {
				tags.add(wordRes.getProperty(posTagProp).getLiteral().getString());
			}
		}

		return new POSSample(tokens, tags);
	}

}
