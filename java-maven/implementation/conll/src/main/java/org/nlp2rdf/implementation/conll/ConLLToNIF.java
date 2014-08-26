package org.nlp2rdf.implementation.conll;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.MultiValueMap;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.vocab.NIFAnnotationProperties;
import org.nlp2rdf.core.vocab.NIFDatatypeProperties;
import org.nlp2rdf.core.vocab.NIFObjectProperties;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.ResourceUtils;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;
import org.nlp2rdf.vm.olia.models.*;
/**
 * Converter to transform ConLL dependency format into NIF
 * 
 * @author Martin Bruemmer
 * This software is part of the nlp2rdf project. Find out more at nlp2rdf.org
 *
 */

public class ConLLToNIF {
	private static Logger log = LoggerFactory.getLogger(ConLLToNIF.class);
	
	private String tempSentence = "";
	private String prefix = "";
	private MultiValueMap tagsetLinks;
	private MultiValueMap tagsetTags;
	private Individual contextResource;
	boolean tagsetKnown = false;
	
	public ConLLToNIF() {
		
	}
	
	public void transform(OntModel inputModel, OntModel outputModel, NIFParameters nifParameters) {
		this.prefix = nifParameters.getPrefix();

		String uri = this.prefix +"char=0,";
		//only supporting RFC5147 string atm
		contextResource = outputModel.createIndividual(uri, outputModel.createClass(NIFOntClasses.RFC5147String.getUri()));
		contextResource.addOntClass(NIFOntClasses.Context.getOntClass(outputModel));
		contextResource.addOntClass(NIFOntClasses.String.getOntClass(outputModel));
		contextResource.addProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(outputModel), "0");
		
		if(!nifParameters.getOptions().has("informat")) {
			log.warn("informat parameter empty, please choose informat=file or informat=text");
		}
		
		if(!nifParameters.getOptions().has("tagset")) {
			log.warn("No tagset chosen, please choose an OLiA tagset from: https://github.com/NLP2RDF/software/blob/master/java-maven/vocabularymodule/OLiA/src/main/java/org/nlp2rdf/vm/olia/models");		
		} else {
			loadTagset(nifParameters.getOptions().valueOf("tagset").toString());
			
		}
		
		if(nifParameters.getOptions().valueOf("intype").equals("file")) {
			if(nifParameters.getOptions().valueOf("informat").equals("text")) {
				File input = new File(nifParameters.getOptions().valueOf("i").toString());
				FileReader reader = null;
				try {
					reader = new FileReader(input);
					this.transformConLL(reader, inputModel, outputModel, nifParameters);
					reader.close();
				}catch(FileNotFoundException fnf) {
					log.error("Could not open file "+nifParameters.getOptions().valueOf("i").toString());
				} catch (IOException e) {
					log.error("Could not read file "+nifParameters.getOptions().valueOf("i").toString());
				}
			}	
		} else if (nifParameters.getOptions().valueOf("intype").equals("url")) {
			log.error("URL input not yet supported");
		} else {
			if(nifParameters.getOptions().valueOf("informat").equals("text")) {
				StringReader reader = new StringReader(nifParameters.getOptions().valueOf("i").toString());
				this.transformConLL(reader, inputModel, outputModel, nifParameters);
				reader.close();
			}
		}

	}
	
	private void loadTagset(String tagset) {
		ClassLoader classLoader = ConLLToNIF.class.getClassLoader();
		
		try {
			Class tagsetClass = classLoader.loadClass("org.nlp2rdf.vm.olia.models."+tagset);
			Object tagsetObject = tagsetClass.newInstance();
			this.tagsetLinks = ((OliaInterface)tagsetObject).getLinks();
			this.tagsetTags = ((OliaInterface)tagsetObject).getTags();
			this.tagsetKnown = true;
		} catch(ClassNotFoundException cnf) {
			log.error("Tagset not found: "+tagset);
		} catch (InstantiationException e) {
			log.error("Tagset could not be instantiated: "+tagset);
		} catch (IllegalAccessException e) {
			log.error("Tagset could not be instantiated: "+tagset);
		}
	}
	
	/**
	 * Central method of the class, reading the corpus file and filling an OntModel for output
	 * 
	 * @param fileIn
	 * @param inputModel
	 * @param outputModel
	 */
	
	public void transformConLL(Reader input, OntModel inputModel, OntModel outputModel, NIFParameters nifParameters){
		BufferedReader reader = null; 	
		try {
			reader = new BufferedReader(input);
			String line;
			int offset = 0;
			List<String> sentence = new ArrayList<String>();
			String contextString = "";
			int count = 0;
			while((line = reader.readLine()) != null) {	
				count++;
				if(count>10000) break;
//					System.out.println(count);
				
				if(!line.isEmpty()) {
					sentence.add(line);
				}
				else {
					this.transformSentenceToConLL(sentence, contextString, offset, inputModel, outputModel, nifParameters);
					contextString += tempSentence;
					this.tempSentence = new String();
					offset = contextString.length();
					sentence = new ArrayList<String>();
				}
			}
			System.out.println(contextString);
			contextResource.addProperty(NIFDatatypeProperties.endIndex.getDatatypeProperty(outputModel), offset+"");
			contextResource.addLiteral(NIFDatatypeProperties.isString.getDatatypeProperty(outputModel), outputModel.createLiteral(contextString));
			ResourceUtils.renameResource(contextResource, contextResource.getURI()+offset);
			
			reader.close();
		} catch(IOException ioe) {
			log.error("Could not read input");
			return;
		}
		
		return;
	}
	
	public void transformSentenceToConLL(List<String> sentence, String contextString, int offset, OntModel inputModel, OntModel outputModel, NIFParameters nifParameters){
		
		List<ConLLWord> wordObjectsOfSentence = new ArrayList<ConLLWord>();
		if(!sentence.isEmpty()) {
			wordObjectsOfSentence = this.transformSentenceToObjects(sentence, offset);
			tempSentence += " "; 
			
			Individual sentenceResource = this.addSentenceResourceToModel(outputModel, tempSentence, offset, contextResource);
			offset = contextString.length();
			
			wordObjectsOfSentence = this.addWordResourcesToModel(outputModel, wordObjectsOfSentence, sentenceResource, contextResource);
			this.parseDependencyTree(wordObjectsOfSentence, outputModel, sentenceResource, contextResource);
			sentenceResource = null;
			wordObjectsOfSentence = null;
		}

		return;
	}
	
	private List<ConLLWord> addWordResourcesToModel(OntModel outputModel, List<ConLLWord> wordObjectsOfSentence, Individual sentenceResource, Individual contextResource) {
		for(ConLLWord word : wordObjectsOfSentence) {
			//generate URI differently
			String uri = contextResource.getURI().substring(0,contextResource.getURI().lastIndexOf("=")+1)+word.getStart()+","+word.getEnd();
			Individual wordResource = outputModel.createIndividual(uri, outputModel.createClass(NIFOntClasses.RFC5147String.getUri()));
			wordResource.addOntClass(NIFOntClasses.Word.getOntClass(outputModel));
			wordResource.addOntClass(NIFOntClasses.String.getOntClass(outputModel));
			wordResource.addProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(outputModel), word.getStart()+"");
			wordResource.addProperty(NIFDatatypeProperties.endIndex.getDatatypeProperty(outputModel), word.getEnd()+"");
			wordResource.addLiteral(NIFDatatypeProperties.anchorOf.getDatatypeProperty(outputModel), outputModel.createLiteral(word.getWordString()));
			wordResource.addProperty(NIFDatatypeProperties.posTag.getDatatypeProperty(outputModel), word.getPos());
			
			if(tagsetKnown)
				addOliaPos(wordResource,word.getPos(), outputModel);
			
			wordResource.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(outputModel), contextResource);
			//TODO: we may need something different here
			if(!word.getPosFine().equals("_"))
				wordResource.addProperty(NIFDatatypeProperties.posTag.getDatatypeProperty(outputModel), word.getPosFine());
			if(!word.getLemma().equals("_"))
				wordResource.addProperty(NIFDatatypeProperties.lemma.getDatatypeProperty(outputModel), word.getLemma());
			//TODO: add genus, numerus etc here
			wordResource.addProperty(NIFObjectProperties.sentence.getObjectProperty(outputModel), sentenceResource);
			word.setResource(wordResource);
			sentenceResource.addProperty(NIFObjectProperties.word.getObjectProperty(outputModel), wordResource);
			sentenceResource.addProperty(NIFObjectProperties.referenceContext.getObjectProperty(outputModel), contextResource);
		}
		return wordObjectsOfSentence;
	}
	
	private void addOliaPos(Individual wordResource, String posTag, OntModel outputModel) {
		List<String> oliaIndividual = (List<String>) this.tagsetTags.get(posTag);
        if (oliaIndividual != null) {

            for (String s : oliaIndividual) {
            	wordResource.addProperty(NIFObjectProperties.oliaLink.getObjectProperty(outputModel), outputModel.createIndividual(s, OWL.Thing));
                List<String> taglinks = (List<String>) this.tagsetLinks.get(s);
                if (taglinks != null) {
                    for (String oc : taglinks) {
                    	wordResource.addProperty(NIFAnnotationProperties.oliaCategory.getAnnotationProperty(outputModel), outputModel.createClass(oc));
                    }
                } else {
                    log.warn("missing oliaLinks for "+posTag);
                }
            }
        } else {
        	log.warn("missing oliaLinks for "+posTag);
        }
	}

	private Individual addSentenceResourceToModel(OntModel outputModel, String sentence, int startOffset, Individual context) {
		int endOffset = startOffset+sentence.length();
		String uri = context.getURI().substring(0,context.getURI().lastIndexOf("=")+1)+startOffset+","+endOffset;
		//only supporting RFC5147 string atm
		Individual sentenceResource = outputModel.createIndividual(uri, outputModel.createClass(NIFOntClasses.RFC5147String.getUri()));
		sentenceResource.addOntClass(NIFOntClasses.Sentence.getOntClass(outputModel));
		sentenceResource.addOntClass(NIFOntClasses.String.getOntClass(outputModel));
		sentenceResource.addProperty(NIFDatatypeProperties.beginIndex.getDatatypeProperty(outputModel), startOffset+"");
		sentenceResource.addProperty(NIFDatatypeProperties.endIndex.getDatatypeProperty(outputModel), endOffset+"");
		sentenceResource.addLiteral(NIFDatatypeProperties.anchorOf.getDatatypeProperty(outputModel), outputModel.createLiteral(sentence));
		return sentenceResource;
	}
	
	/**
	 * Parse a dependency tree from generated objects
	 * TODO: There are NIF properties missing to describe dependency relations from phrases to other phrases
	 * TODO: What exactly is the "root" node? I will make this the sentence resource but that seems to be wrong. should there be an artificial "root" phrase? what does it contain? what offsets does it have?
	 * @param sentenceObjects
	 */

	private void parseDependencyTree(List<ConLLWord> sentenceObjects, OntModel inputModel, Individual sentence, Individual context) {
		//create the tree
		//TODO: defining new property here, should be changed to work ootb
		ObjectProperty phraseHead = inputModel.createObjectProperty("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#phraseHead");
		phraseHead.addProperty(RDFS.comment, "The head of a Phrase.");
		phraseHead.addProperty(RDFS.domain, NIFOntClasses.Phrase.getUri());
		phraseHead.addProperty(RDFS.range, NIFOntClasses.Phrase.getUri());
		
		ObjectProperty depRelType = inputModel.createObjectProperty("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#dependencyRelationType");
		depRelType.addProperty(RDFS.comment, "Dependency relation to the HEAD. The set of dependency relations depends on the particular language. Note that depending on the original treebank annotation, the dependency relation may be meaningful or simply 'ROOT'. ");
		depRelType.addProperty(RDFS.domain, NIFOntClasses.Phrase.getUri());
		
		ObjectProperty govTODep = NIFObjectProperties.dependency.getObjectProperty(inputModel);
		
		
		for(ConLLWord word : sentenceObjects) {
			int phraseHeadId = word.getPhraseHeadId();
			Individual wordResource = word.getResource();
			//every word is a phrase
			wordResource.addOntClass(NIFOntClasses.Phrase.getOntClass(inputModel));
			
			if(phraseHeadId == 0) {
				//root node, making the sentence the head
				wordResource.addProperty(depRelType, word.getPhraseType());
				
			} else {
				//ids start with 1, List<> indexes starts with 0
				ConLLWord phraseHeadObject = sentenceObjects.get(phraseHeadId-1);
				phraseHeadObject.getResource().addProperty(govTODep, wordResource);
				
				wordResource.addProperty(phraseHead, phraseHeadObject.getResource());
				wordResource.addProperty(NIFDatatypeProperties.head.getDatatypeProperty(inputModel), String.valueOf(phraseHeadId));
				wordResource.addProperty(depRelType, word.getPhraseType());			
			}
		}
	}
	
	/**
	 * Parse a sentence into a list of ConLLWord objects
	 * @param sentence list of line strings from the ConLL file 
	 * @return
	 */

	private List<ConLLWord> transformSentenceToObjects(List<String> sentence, int startOffset) {
		
		String sentenceString = "";
		List<ConLLWord> sentenceObjects = new ArrayList<ConLLWord>();
		int offset = startOffset;
		
		boolean addSpaceToOffset = false;
		for(String line : sentence) {
		
			ConLLWord word = getWordFromCorpusLine(line);
			//TODO: check if word is punctuation class word instead of this static check
			if(word.getWordString().matches("([\\.!?,;:)]|'+)")) {
				//remove whitespace before punctuation mark and decrement offset
				if(sentenceString.endsWith(" ")) {
					sentenceString=sentenceString.substring(0,sentenceString.length()-1);
					offset = offset-1;
				}
				
				sentenceString+=word.getWordString();			
				//no space here
			} else if(word.getWordString().matches("([(]|`+)")) {			
				sentenceString+=word.getWordString();	
				//add a space after the word
			} else {			
				sentenceString+=word.getWordString()+" ";
				addSpaceToOffset = true;
			}
			
			word.setStart(offset);
			int wordEnd = offset+word.getWordString().length();
			word.setEnd(wordEnd);
			offset = wordEnd;
			if(addSpaceToOffset) {
				offset++;
				addSpaceToOffset = false;
			}
			
			sentenceObjects.add(word);
		}
		this.tempSentence = sentenceString.trim();
		
		//return the length of the sentence added to the offset
		return sentenceObjects;
	}
	
	private void writeModel(String filename, OntModel model,NIFParameters nifParameters) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			model.write(writer, Format.toJena(nifParameters.getOutputFormat()));
			writer.close();
		}catch(FileNotFoundException fnf) {
			log.error("Could not find file: "+filename);
		}catch(IOException ioe) {
			log.error("Could not write file: "+filename);
		}
	}
	
	private ConLLWord getWordFromCorpusLine(String line) {
		ConLLWord word = new ConLLWord();
		
		String[] conllFields = line.split("\t");
		word.setWordId(conllFields[0]);
		word.setWordString(conllFields[1]);
		word.setLemma(conllFields[2]);
		word.setPos(conllFields[4]);
		word.setPosFine(conllFields[5]);
		word.setMorphs(conllFields[6]);
		word.setPhraseHeadId(Integer.parseInt(conllFields[8]));
		word.setPhraseType(conllFields[10]);
		return word;
	}
	
}
