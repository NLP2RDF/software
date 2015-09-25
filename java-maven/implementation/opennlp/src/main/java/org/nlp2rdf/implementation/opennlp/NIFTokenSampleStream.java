package org.nlp2rdf.implementation.opennlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.NIFObjectProperties;
import org.nlp2rdf.core.vocab.NIFOntClasses;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.util.FilterObjectStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.Span;

public class NIFTokenSampleStream extends FilterObjectStream<Resource, TokenSample>{

	public NIFTokenSampleStream(ObjectStream<Resource> resources) {
		super(resources);
	}
	
	// extract start and end indexes from uri fragment of the word resource
	private int[] extractWordOffsets(String uri) {
		int[] offsets = new int[2];
			//nif 2.0
		String[] indexes = new String[2];
		if(uri.contains("#char=")) {
			String charFragment = uri.substring(uri.lastIndexOf("#char="));
			if (!charFragment.isEmpty()) {
				charFragment = charFragment.substring(6);
			} else {
				return null;
			}
			indexes = charFragment.split(",");
		//nif 2.1
		} else if(uri.contains("#offset_")) {
			String charFragment = uri.substring(uri.lastIndexOf("#offset_"));
			if (!charFragment.isEmpty()) {
				charFragment = charFragment.substring(8);
			} else {
				return null;
			}
			indexes = charFragment.split("_");
		}
		
		if (indexes.length >= 2) {
			offsets[0] = Integer.valueOf(indexes[0]);
			offsets[1] = Integer.valueOf(indexes[1]);
		} else {
			return null;
		}
		return offsets;
	}
	
	public TokenSample read() throws IOException {

		Resource documentRes = samples.read();
		if(documentRes==null) {
			return null;
		}
		Model rdfModel = documentRes.getModel();
		
		Property docRefProp = rdfModel
		    .createProperty(NIFObjectProperties.referenceContext.getUri());
		Property stringProp = rdfModel
		    .createProperty(NIFDatatypeProperties.isString.getUri());
		Property wordProp = rdfModel.createProperty(NIFOntClasses.Word.getUri());

		List<Resource> wordResources = rdfModel.listSubjectsWithProperty(
		    docRefProp, documentRes).toList();
		//fall back to getting single sentences
		//no order because no context
		if(wordResources.size()==0) {
			wordResources = rdfModel.listSubjectsWithProperty(RDF.type,wordProp).toList();
//			return makeNoContextSample(wordResources, rdfModel);
		}
		List<Span> wordSpans = new ArrayList<Span>();
		String documentContent = documentRes.getProperty(stringProp).getString();

		Map<Integer, Resource> wordMap = new HashMap<Integer, Resource>();
		
		for (Resource wordRes : wordResources) {
			if(wordRes.hasProperty(RDF.type, wordProp)) {
				Integer startIndex = this.extractWordOffsets(wordRes.getURI())[0];
				wordMap.put(startIndex, wordRes);
			}
		}

		List<Integer> sortedIndexes = new ArrayList<Integer>();
		sortedIndexes.addAll(wordMap.keySet());
		Collections.sort(sortedIndexes);

		for (Integer startIndex : sortedIndexes) {
			int[] borders = this.extractWordOffsets(wordMap.get(startIndex).getURI());
			Span wordBorder = new Span(borders[0], borders[1]);
			wordSpans.add(wordBorder);
		}	

		return new TokenSample(documentContent,
		    wordSpans.toArray(new Span[wordSpans.size()]));
	}
}
