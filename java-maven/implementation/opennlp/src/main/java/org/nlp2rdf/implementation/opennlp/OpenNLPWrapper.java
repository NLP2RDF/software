package org.nlp2rdf.implementation.opennlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.h2.constant.SysProperties;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.vocab.NIFAnnotationProperties;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.NIFObjectProperties;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.vm.olia.models.OliaInterface;
import org.nlp2rdf.vm.olia.models.Penn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

import com.hp.hpl.jena.vocabulary.OWL;

public class OpenNLPWrapper {
	private static Logger log = LoggerFactory.getLogger(OpenNLPWrapper.class);
	
	private OntModel nifModel;
	private Individual context;
	private String documentText;
	private String prefix;
	private URIScheme uriScheme;
	private File modelFolder;
	private String lang;
	private OliaInterface tagset;
	private Set<String> unknownTags;
	
	Text2RDF text2RDF = new Text2RDF();
	
	public OpenNLPWrapper(Individual context, NIFParameters parameters, OntModel model) {
		nifModel = model;
		this.context = context;		
		this.documentText = context.getProperty(NIFDatatypeProperties.isString.getDatatypeProperty(nifModel)).getString();
		this.prefix = parameters.getPrefix();
		this.uriScheme = parameters.getUriScheme();
		if(!parameters.getOptions().has("modelFolder")) {
			log.error("No model specified, please specify via -modelFolder");
		} else {
			this.modelFolder = new File(parameters.getOptions().valueOf("modelFolder").toString());
		}
		if(!parameters.getOptions().has("language")) {
			log.warn("No language specified, defaulting to english");
			lang = "en";
		} else {
			lang = parameters.getOptions().valueOf("language").toString();
		}
		if(!parameters.getOptions().has("tagset")) {
			log.warn("No tagset specified, defaulting to Penn");
			ClassLoader classLoader = OpenNLPWrapper.class.getClassLoader();
			try {
				Class tagsetClass = classLoader.loadClass("org.nlp2rdf.vm.olia.models.Penn");
				tagset = (OliaInterface) tagsetClass.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ClassLoader classLoader = OpenNLPWrapper.class.getClassLoader();
			try {
				Class tagsetClass = classLoader.loadClass("org.nlp2rdf.vm.olia.models."+parameters.getOptions().valueOf("tagset").toString());
				tagset = (OliaInterface) tagsetClass.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		unknownTags = new HashSet<String>();
	}
	
	public OntModel getNifModel() {
		return nifModel;
	}
	
	public void processText(Individual context, NIFParameters nifParameters) {
		
		if(modelFolder==null)
			return;
		//sentence detection
		Span[] sentences = sentDetect(documentText);
		List<Individual> sentenceResources = addSentences(sentences);
		sentences = null;
		//tokenizing & pos tagging
		Tokenizer tokenizer = null;
		InputStream modelIn = null;

		try {
		   // Loading tokenizer model
			modelIn = new FileInputStream(modelFolder.getAbsolutePath()+"/"+lang+"-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(modelIn);
			modelIn.close();
			modelIn = new FileInputStream(modelFolder.getAbsolutePath()+"/"+lang+"-pos.bin");
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			modelIn.close();
			
			tokenizer = new TokenizerME(tokenModel);
			
			for(Individual sentence : sentenceResources) {
				
				String sentString = sentence.getProperty(NIFDatatypeProperties.anchorOf.getDatatypeProperty(nifModel)).getString();			
				
				Span[] tokenSpans = tokenizer.tokenizePos(sentString);
				String tags[] = tagger.tag(getStringsForSpans(tokenSpans, sentString));
				//offset relative to the sentence, but has to be relative to document: setting off by start offset of sentence
				int sentStart = sentence.getProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(nifModel)).getInt();
				List<Individual> sentenceWords = addWords(sentence, tokenSpans, sentStart);
				for(int i = 0; i < sentenceWords.size(); i++) {
					addPos(sentenceWords.get(i), tags[i]);
				}
				tokenSpans = null;
				tags = null;
				
			}
			
		} catch (FileNotFoundException fnf) {
			log.error("Model file not found "+ fnf.getMessage());
		} catch (final IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (modelIn != null) {
				try {
					modelIn.close();
				} catch (final IOException e) {}
			}
		}
		
	}
	
	private String[] getStringsForSpans(Span[] spans, String text) {
		String[] strings = new String[spans.length];
		for(int i = 0; i < spans.length; i++) {
			strings[i] = spans[i].getCoveredText(text).toString();
		}
		return strings;
	}
	
	public Span[] sentDetect(String text) {
		SentenceDetector sentenceDetector = null;
		InputStream modelIn = null;
		try {
		   // Loading sentence detection model

		   modelIn = new FileInputStream(modelFolder.getAbsolutePath()+"/"+lang+"-sent.bin");
		   final SentenceModel sentenceModel = new SentenceModel(modelIn);
		   modelIn.close();

		   sentenceDetector = new SentenceDetectorME(sentenceModel);  
		   Span[] spans = sentenceDetector.sentPosDetect(text);
		   return spans;
		   
		} catch (final IOException ioe) {
		   ioe.printStackTrace();
		} finally {
		   if (modelIn != null) {
		      try {
		         modelIn.close();
		      } catch (final IOException e) {} // oh well!
		   }
		}
		return null;
	}
	
	public List<Individual> addSentences(Span[] sentences) {
		return addSpans(sentences, documentText, NIFOntClasses.Sentence.getOntClass(nifModel),0);
	}
	
	public List<Individual> addWords(Individual sentence, Span[] words, int offset) {
		List<Individual> wordResources = addSpans(words, documentText, NIFOntClasses.Word.getOntClass(nifModel), offset);
		for(Individual wordRes : wordResources) {
			wordRes.addProperty(NIFObjectProperties.sentence.getObjectProperty(nifModel), sentence);
			sentence.addProperty(NIFObjectProperties.word.getObjectProperty(nifModel), wordRes);
		}	
		return wordResources;
	}
		
	//tagset based on training corpus, is Tiger for German models
	private void addPos(Individual wordResource, String posTag) {
		List<String> oliaIndividual = (List<String>) tagset.getTags().get(posTag);
        if (oliaIndividual != null) {
            for (String s : oliaIndividual) {
            	wordResource.addProperty(NIFObjectProperties.oliaLink.getObjectProperty(nifModel), nifModel.createIndividual(s, OWL.Thing));
                List<String> taglinks = (List<String>) tagset.getLinks().get(s);
                if (taglinks != null) {
                    for (String oc : taglinks) {
                    	wordResource.addProperty(NIFAnnotationProperties.oliaCategory.getAnnotationProperty(nifModel), nifModel.createClass(oc));
                    }
                } else {
                	if(!unknownTags.contains(posTag)) {
                		log.warn("missing oliaLinks for "+posTag);
                		unknownTags.add(posTag);
                	}              		               	
                }
            }
        } else {
        	if(!unknownTags.contains(posTag)) {
        		log.warn("missing tag in olia model: "+posTag);
        		unknownTags.add(posTag);
        	}
        }
	}
	
	public List<Individual> addSpans(Span[] spans, String text, OntClass spanClass) {
		return addSpans(spans, text, spanClass,0); 
	}

	public List<Individual> addSpans(Span[] spans, String text, OntClass spanClass, int offset) {
		
		List<Individual> resources = new ArrayList<Individual>();
		
		for(int i = 0; i < spans.length; i++) {
			
			Span span = spans[i];
			int start = span.getStart()+offset;
			int end = span.getEnd()+offset;
			org.nlp2rdf.core.Span nifSpan = new org.nlp2rdf.core.Span(start, end);
			Individual spanResource = text2RDF.createCStringIndividual(prefix, context, nifSpan, uriScheme, nifModel);
			spanResource.addOntClass(spanClass);
			spanResource.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(nifModel), context);
			spanResource.addLiteral(NIFDatatypeProperties.anchorOf.getDatatypeProperty(nifModel),nifModel.createLiteral(text.substring(start,end)));
			resources.add(spanResource);
		}
		return resources;
	}
	
}
