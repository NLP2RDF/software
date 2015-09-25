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

import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.util.FilterObjectStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.Span;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.vocabulary.RDF;

public class NIFSentenceSampleStream extends
    FilterObjectStream<Resource, SentenceSample> {

	public NIFSentenceSampleStream(ObjectStream<Resource> resources) {		
		super(resources);
	}

	// extract start and end indexes from uri fragment of the sentence resource
	private int[] extractSentenceOffsets(String uri) {
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

	// get properties to central final class
	public SentenceSample read() throws IOException {

		Resource documentRes = samples.read();
		if(documentRes==null) {
			return null;
		}
		Model rdfModel = documentRes.getModel();
		
		Property docRefProp = rdfModel
		    .createProperty(NIFObjectProperties.referenceContext.getUri());
		Property stringProp = rdfModel
		    .createProperty(NIFDatatypeProperties.isString.getUri());
		Property sentenceProp = rdfModel.createProperty(NIFOntClasses.Sentence.getUri());

		List<Resource> sentenceResources = rdfModel.listSubjectsWithProperty(
		    docRefProp, documentRes).toList();
		//fall back to getting single sentences
		//no order because no context
		if(sentenceResources.size()==0) {
			sentenceResources = rdfModel.listSubjectsWithProperty(RDF.type,sentenceProp).toList();
			return makeNoContextSample(sentenceResources, rdfModel);
		}
		List<Span> sentenceSpans = new ArrayList<Span>();
		String documentContent = documentRes.getProperty(stringProp).getString();
		
		Map<Integer, Resource> sentenceMap = new HashMap<Integer, Resource>();
		
		for (Resource sentenceRes : sentenceResources) {
			if(sentenceRes.hasProperty(RDF.type, sentenceProp)) {
				Integer startIndex = this.extractSentenceOffsets(sentenceRes.getURI())[0];
				sentenceMap.put(startIndex, sentenceRes);
			}
		}

		List<Integer> sortedIndexes = new ArrayList<Integer>();
		sortedIndexes.addAll(sentenceMap.keySet());
		Collections.sort(sortedIndexes);

		for (Integer startIndex : sortedIndexes) {
			int[] borders = this.extractSentenceOffsets(sentenceMap.get(startIndex).getURI());
			Span sentenceBorder = new Span(borders[0], borders[1]);
			sentenceSpans.add(sentenceBorder);

		}
		
		
		return new SentenceSample(documentContent,
		    sentenceSpans.toArray(new Span[sentenceSpans.size()]));
	}

	private SentenceSample makeNoContextSample(List<Resource> sentenceResources, Model model) {
		Property anchorProp = model.createProperty(NIFDatatypeProperties.anchorOf.getUri());
		Property stringProp = model.createProperty(NIFDatatypeProperties.isString.getUri());
		List<Span> sentenceSpans = new ArrayList<Span>();
		
		int pos = 0;
		StringBuilder builder = new StringBuilder();
		for(Resource sentenceResource : sentenceResources) {
			String sentence = null;
			if(sentenceResource.hasProperty(anchorProp)) {
				sentence = sentenceResource.getProperty(anchorProp).getObject().toString().trim();
			} else if(sentenceResource.hasProperty(stringProp)) {
				sentence = sentenceResource.getProperty(stringProp).getObject().toString().trim();
			}
			if(sentence != null) {
				builder.append(sentence);
				builder.append("\n");
				sentenceSpans.add(new Span(pos,pos+sentence.length()));
				pos = pos+sentence.length()+1;
			}
		}
		return new SentenceSample(builder.toString(),
			    sentenceSpans.toArray(new Span[sentenceSpans.size()]));
	}
}

