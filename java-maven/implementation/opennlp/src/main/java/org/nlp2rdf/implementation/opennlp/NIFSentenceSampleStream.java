package org.nlp2rdf.implementation.opennlp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
import com.hp.hpl.jena.vocabulary.RDF;

public class NIFSentenceSampleStream extends
    FilterObjectStream<Resource, SentenceSample> {

	public NIFSentenceSampleStream(ObjectStream<Resource> resources) {
		super(resources);
	}

	// extract start and end indexes from uri fragment of the sentence resource
	private int[] extractSentenceOffsets(String uri) {
		int[] offsets = new int[2];
		String charFragment = uri.substring(uri.lastIndexOf("#char="));
		if (!charFragment.isEmpty()) {
			charFragment = charFragment.substring(6);
		} else {
			// fragment not found, format is not well formed
			return null;
		}
		String[] indexes = charFragment.split(",");
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
		List<Span> sentenceSpans = new ArrayList<Span>();
		String documentContent = documentRes.getProperty(stringProp).getString();

		for (Resource sentenceRes : sentenceResources) {
			if(sentenceRes.hasProperty(RDF.type, sentenceProp)) {
				int[] borders = this.extractSentenceOffsets(sentenceRes.getURI());
				Span sentenceBorder = new Span(borders[0], borders[1]);
				sentenceSpans.add(sentenceBorder);
			}
		}

		return new SentenceSample(documentContent,
		    sentenceSpans.toArray(new Span[sentenceSpans.size()]));
	}
	
	public static void main(String[] args) {
	 OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
	 TypedRDFResourceStream res = null;
//	 System.out.println(NIFSentenceSampleTest.class.getResourceAsStream("/nif/nifexample.ttl"));
	 try {
		 model.read(new BufferedReader(new
		 FileReader("/home/martin/workspace/OpenNLPNIFParser/resources/nifnertest.ttl")),"http://example.org","TURTLE");
		 System.setOut(new PrintStream(System.out, true, "UTF-8"));	
		 res = new TypedRDFResourceStream(model, NIFOntClasses.Context.getOntClass(model));
		 
		 NIFSentenceSampleStream sampleStream = new NIFSentenceSampleStream(res);
		 List<SentenceSample> samples = new ArrayList<SentenceSample>();
		 SentenceSample sample = sampleStream.read();
	
	   while (sample != null) {
	     System.out.println(sample.toString());
	     samples.add(sample);
	     sample = sampleStream.read();
	   }
//	   System.out.println(samples.size());
	 
	 } catch (Exception e) {
	 // this shouldn't happen
	 e.printStackTrace();
	 }
	}
}

