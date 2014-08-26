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

import opennlp.tools.namefind.NameSample;
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

public class NIFNameSampleStream extends
    FilterObjectStream<Resource, NameSample> {
	
	public static final String NER_ANNO = "http://www.w3.org/2005/11/its/rdf#taIdentRef";

	public NIFNameSampleStream(ObjectStream<Resource> resources) {
		super(resources);
	}

	public NameSample read() throws IOException {

		Resource sentRes = samples.read();
		if (sentRes == null) {
			return null;
		}
		Model rdfModel = sentRes.getModel();
		
		NameSample sample = null;
		Property firstWordProp = rdfModel.createProperty(NIFObjectProperties.firstWord.getUri());
		Property nextWordProp = rdfModel.createProperty(NIFObjectProperties.nextWord.getUri());
		Property hasWordProp = rdfModel.createProperty(NIFObjectProperties.word.getUri());
		Property anchorProp = rdfModel.createProperty(NIFDatatypeProperties.anchorOf.getUri());
		Property nerProp = rdfModel.createProperty(NER_ANNO);
		

		int sentenceStartIndex = extractOffsets(sentRes,rdfModel)[0];

		List<String> tokens = new ArrayList<String>(100);
		List<Span> tags = new ArrayList<Span>(100);

		Resource wordResource = sentRes.getPropertyResourceValue(firstWordProp);

		if (wordResource != null) {
			
			tokens.add(wordResource.getProperty(anchorProp).getString());

			if (wordResource.getProperty(nerProp) != null) {
				int[] offsets = extractOffsets(wordResource, rdfModel);
				String type = wordResource.getProperty(nerProp).getResource()
				    .getLocalName();
				// indexes are relative to the documents, not the sentences, so we have
				// to correct for the start offset of the sentence
				tags.add(new Span(offsets[0] - sentenceStartIndex, offsets[1]
				    - sentenceStartIndex, type));

			}

			// traversing all words of the sentence
			while (true) {
				wordResource = wordResource.getPropertyResourceValue(nextWordProp);
				if (wordResource != null) {
					tokens.add(wordResource.getProperty(anchorProp).getString());
					if (wordResource.getProperty(nerProp) != null) {
						int[] offsets = extractOffsets(wordResource, rdfModel);
						String type = wordResource.getProperty(nerProp).getResource()
						    .getLocalName();
						tags.add(new Span(offsets[0] - sentenceStartIndex, offsets[1]
						    - sentenceStartIndex, type));
					}
				} else {
					break;
				}
			}
			return sample;

		} else {
			// firstWord not given, trying if single words are annotated
			List<Statement> wordStatements = sentRes.listProperties(hasWordProp)
			    .toList();
			if (wordStatements.size() != 0) {
				sample = getNameSampleFromWordStatement(wordStatements, rdfModel, 
				    sentenceStartIndex);
				return sample;
			} else {
				return null;
			}
		}
	}

	// TODO: error if format not well formed
	private int[] extractOffsets(Resource res, Model rdfModel) {
		int[] offsets = new int[2];
		Property beginProp = rdfModel.createProperty(NIFDatatypeProperties.beginIndex.getUri());
		Property endProp = rdfModel.createProperty(NIFDatatypeProperties.endIndex.getUri());
		if (res.getProperty(beginProp) != null && res.getProperty(endProp) != null) {
			offsets[0] = Integer.valueOf(res.getProperty(beginProp).getString());
			offsets[1] = Integer.valueOf(res.getProperty(endProp).getString());
			return offsets;
		} else {
			String uri = res.getURI();
			String charFragment = uri.substring(uri.lastIndexOf("#char="));
			if (!charFragment.isEmpty()) {
				charFragment = charFragment.substring(6);
			} else {
				// fragment not found, format is not well formed
				throw new RuntimeException(
				    "Resource URI does not adhere to NIF standard. #char fragment not found.");
			}
			String[] indexes = charFragment.split(",");
			if (indexes.length >= 2) {
				offsets[0] = Integer.valueOf(indexes[0]);
				offsets[1] = Integer.valueOf(indexes[1]);
			} else {
				throw new RuntimeException(
				    "Resource URI does not adhere to NIF standard. #char fragment not well formed.");
			}
			return offsets;
		}
	}

	// resources are not in order, so they are ordered here
	private NameSample getNameSampleFromWordStatement(List<Statement> words, 
			Model rdfModel, int correctOffset) {

		Property nameProp = rdfModel.createProperty(NER_ANNO);
		Property anchorProp = rdfModel
		    .createProperty(NIFDatatypeProperties.anchorOf.getUri());

		Map<Integer, Resource> wordMap = new HashMap<Integer, Resource>();

		for (Statement wordStmt : words) {
			Resource wordRes = wordStmt.getObject().asResource();
			Integer startIndex = extractOffsets(wordRes, rdfModel)[0];
			wordMap.put(startIndex, wordRes);
		}

		List<Integer> sortedIndexes = new ArrayList<Integer>();
		sortedIndexes.addAll(wordMap.keySet());
		Collections.sort(sortedIndexes);

		List<String> tokens = new ArrayList<String>(100);
		List<Span> tags = new ArrayList<Span>(100);

		for (Integer startIndex : sortedIndexes) {
			Resource wordRes = wordMap.get(startIndex);
			tokens.add(wordRes.getProperty(anchorProp).getString());
			if (wordRes.getProperty(nameProp) != null) {
				int endIndex = extractOffsets(wordRes, rdfModel)[1] - correctOffset;
				String type = wordRes.getProperty(nameProp).getResource()
				    .getLocalName();
				tags.add(new Span(startIndex - correctOffset, endIndex, type));
			}
		}

		return new NameSample(tokens.toArray(new String[tokens.size()]),
		    tags.toArray(new Span[tags.size()]), true);
	}

	public static void main(String[] args) {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
		TypedRDFResourceStream res = null;
		NIFNameSampleStream nifnamestream = null;
		try {
			model.read(new BufferedReader(new FileReader(
			    "/home/martin/workspace/OpenNLPNIFParser/resources/nifnertest.ttl")),
			    "http://example.org", "TURTLE");

			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			res = new TypedRDFResourceStream(model, NIFOntClasses.Sentence.getOntClass(model));
			nifnamestream = new NIFNameSampleStream(res);
			List<NameSample> samples = new ArrayList<NameSample>();
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());
			samples.add(nifnamestream.read());

			for (NameSample sample : samples) {
				String[] sentence = sample.getSentence();
				Span[] tags = sample.getNames();
				String sentenceString = sentence[0];
				for (int i = 1; i < sentence.length; i++) {
					sentenceString += " " + sentence[i];
				}
//				System.out.println(sentenceString);
				for (int j = 0; j < tags.length; j++) {
					Span tag = tags[j];
					System.out.println(sentenceString.substring(tag.getStart(),
					    tag.getEnd())
					    + " " + tag.getType());
				}
			}
		} catch (Exception e) {
			// this shouldn't happen
			e.printStackTrace();
		}
	}

}
