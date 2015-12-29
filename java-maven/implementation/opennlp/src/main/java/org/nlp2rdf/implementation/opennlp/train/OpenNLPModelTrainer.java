/******************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                               */
/*                                                                            */
/*  Licensed under the Apache License, Version 2.0 (the "License");           */
/*  you may not use this file except in compliance with the License.          */
/*  You may obtain a copy of the License at                                   */
/*                                                                            */
/*      http://www.apache.org/licenses/LICENSE-2.0                            */
/*                                                                            */
/*  Unless required by applicable law or agreed to in writing, software       */
/*  distributed under the License is distributed on an "AS IS" BASIS,         */
/*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  */
/*  See the License for the specific language governing permissions and       */
/*  limitations under the License.                                            */
/******************************************************************************/

package org.nlp2rdf.implementation.opennlp.train;


import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import opennlp.tools.cmdline.sentdetect.SentenceDetectorEvaluatorTool;
import opennlp.tools.postag.POSDictionary;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerFactory;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorFactory;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerFactory;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.BaseModel;

import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.*;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.nlp2rdf.core.urischemes.URISchemeHelper;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.implementation.opennlp.NIFPOSSampleStream;
import org.nlp2rdf.implementation.opennlp.NIFSentenceSampleStream;
import org.nlp2rdf.implementation.opennlp.NIFTokenSampleStream;
import org.nlp2rdf.implementation.opennlp.TypedRDFResourceStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;

public class OpenNLPModelTrainer {
	private static Logger log = LoggerFactory.getLogger(OpenNLPModelTrainer.class);
	
	public OpenNLPModelTrainer() {
		
	}
	
	public SentenceModel trainSentenceModel(List<OntModel> models, String language) {
		TypedRDFResourceStream res = new TypedRDFResourceStream(models, NIFOntClasses.Context.getOntClass(models.get(0)));
		ObjectStream<SentenceSample> sampleStream = new NIFSentenceSampleStream(res);

		SentenceModel model = null;
		try {	
			char[] eos = ".?!".toCharArray();
			SentenceDetectorFactory sdFactory = SentenceDetectorFactory.create(null, language, true, null, eos);
			model = SentenceDetectorME.train(language,sampleStream, sdFactory, TrainingParameters.defaultParams());
			
		} catch ( IOException ioe) {
			log.error("Could not read from sentence sample stream.");
		} catch ( Exception e) {
			log.error("Something went wrong with training. If your dataset is very small, try a larger one.");
			return null;
		} 	
		return model;
	}
	
	public POSModel trainPOSModel(List<OntModel> models, String language) {
		TypedRDFResourceStream res = new TypedRDFResourceStream(models, NIFOntClasses.Sentence.getOntClass(models.get(0)));
		ObjectStream<POSSample> sampleStream = new NIFPOSSampleStream(res);

		POSModel model = null;

		try {	
			model = POSTaggerME.train(language, sampleStream, TrainingParameters.defaultParams(), null, null);
			
		} catch ( IOException ioe) {
			log.error("Could not read from pos sample stream.");
		} catch ( Exception e) {
			log.error("Something went wrong with training. If your dataset is very small, try a larger one.");
			return null;
		} 
		return model;
	}
	
	public TokenizerModel trainTokenModel(List<OntModel> models, String language) {
		TypedRDFResourceStream res = new TypedRDFResourceStream(models, NIFOntClasses.Context.getOntClass(models.get(0)));
		ObjectStream<TokenSample> sampleStream = new NIFTokenSampleStream(res);

		TokenizerModel model = null;

		try {		
			TokenizerFactory tokenFactory = TokenizerFactory.create(null, language, null, true, null);
			model = TokenizerME.train(sampleStream, tokenFactory, TrainingParameters.defaultParams());
//			model = TokenizerME.train(language, sampleStream, true, TrainingParameters.defaultParams());
			
		} catch (IOException ioe) {
			log.error("Could not read from token sample stream.");
		} catch ( Exception e) {
			log.error("Something went wrong with training. If your dataset is very small, try a larger one.");
			return null;
		} 
		return model;
	}
	
	public List<OntModel> readDataIDModels(NIFParameters nifParameters, String format) {
		List<OntModel> modelList = new ArrayList<OntModel>();
		List<String> corpusUrls = getCorpusUrlsFromDataID(nifParameters);
    	for(String corpusurl : corpusUrls) {
    		log.info("Retrieving "+corpusurl);
    		OntModel datamodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
    		InputStream is = null;
    		try {
    			is = new URI(corpusurl).toURL().openStream();
    		} catch(URISyntaxException e) {
    			log.error(corpusurl+" is not a valid uri");
    		} catch(IOException e) {
    			log.error("Can not read "+corpusurl);
    		}
    		datamodel.read(is,null,format);
    		modelList.add(datamodel);
    	}
    	return modelList;
	}
	
	public void writeTrainedModel(BaseModel model, String outputFile, String task) {
		OutputStream modelOut = null;
		try {
		  modelOut = new BufferedOutputStream(new FileOutputStream(outputFile));
		  if(model!=null)
			  model.serialize(modelOut);
		  else
			  log.error("No model created for "+task);
		} catch(IOException ioe) {
			log.error("Could not write "+outputFile);
		}
	}
	
	private List<String> getCorpusUrlsFromDataID(NIFParameters nifParameters) {
		List<String> urls = new ArrayList<String>();
		if(nifParameters.getOptions().has("dataid")) {
            
        	OntModel dataIdModel = nifParameters.getInputModel();
        	Resource distributionClass = dataIdModel.createResource("http://dataid.dbpedia.org/ns/core#Distribution");
        	Property acessUrl = dataIdModel.createProperty("http://www.w3.org/ns/dcat#accessURL");
        	Property downloadUrl = dataIdModel.createProperty("http://www.w3.org/ns/dcat#downloadURL");
        	ResIterator distributionIt = dataIdModel.listResourcesWithProperty(RDF.type,distributionClass);
        	while(distributionIt.hasNext()) {
        		Resource distribution = distributionIt.next();
        		NodeIterator corpusFiles = null;
        		
        		if(dataIdModel.contains(distribution, acessUrl)) {
        			corpusFiles = dataIdModel.listObjectsOfProperty(distribution, acessUrl);
        		} else if (dataIdModel.contains(distribution, downloadUrl)) {
        			corpusFiles = dataIdModel.listObjectsOfProperty(distribution, downloadUrl);
        		} else {
        			log.error("No distributions found in DataID");
        			return urls;
        		}
        		while(corpusFiles.hasNext()) {
        			urls.add(corpusFiles.next().toString());
        		}
        		
        	}
        } 
		return urls;
	}
	
	public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/opennlp#");
        OpenNLPModelTrainer trainer = new OpenNLPModelTrainer();
        ParameterParser.addCLIParameter(parser);
        parser.acceptsAll(asList("dataid"), "Use DataID description of the corpus instead direct input (true/false)")
        	.withRequiredArg().ofType(Boolean.class).describedAs("dataid");
        parser.acceptsAll(asList("language"), "Two char language identifier (en,de,es)")
        	.withRequiredArg().ofType(String.class).describedAs("lang");
        parser.acceptsAll(asList("tasks"), "Comma-seperated list of tasks to run (sentences,tokens,pos)")
    	.withRequiredArg().ofType(String.class).describedAs("tasks");
        
        try {
            OptionSet options = ParameterParser.getOption(parser, args);
            ParameterParser.handleHelpAndWS(options, "");
            NIFParameters nifParameters = ParameterParser.parseOptions(options, false);    
            List<OntModel> datamodels = new ArrayList<OntModel>();
            boolean doSentences = false;
            boolean doTokens = false;
            boolean doPos = false;
            
         //parse task description   
            if(nifParameters.getOptions().has("tasks")) {
            	
            	String[] tasks = options.valueOf("tasks").toString().split(",");            		
            	for(String task : tasks) {
            		if(task.toLowerCase().contains("sentence")) {
            			doSentences = true;
            		} else if(task.toLowerCase().contains("token")) {
            			doTokens = true;
            		} else if(task.toLowerCase().contains("pos")) {
            			doPos = true;
            		} else {
            			log.error("No valid tasks found, please choose one or multiple of sentences,tokens,pos");
            			return;
            		}
            	}
            } else {
            	log.warn("Task parameter not given, defaulting to (sentences,tokens,pos)");
            	doSentences = true;
            	doTokens = true;
            	doPos = true;
            }
            
            //load the data. use dataid if set as parameter
            if(nifParameters.getOptions().has("dataid") && 
              		options.valueOf("dataid").toString().equals("true")) {
            	log.info("Using DataID to retrieve corpus files");
            	datamodels = trainer.readDataIDModels(nifParameters, options.valueOf("f").toString().toUpperCase());
            } else {
              	OntModel datamodel = nifParameters.getInputModel();
              	datamodels.add(datamodel); 
            }
            
            String lang = options.valueOf("language") != null ? 
             		options.valueOf("language").toString() : "en";
         
          //train set tasks
            if(doSentences) {
            	log.info("Training sentence detector");
            	//this is a bit ugly: the stream classes work by removing the first element of the model list
            	//, thus it would be empty after first training
            	List<OntModel> tempModels = new ArrayList<OntModel>();
            	tempModels.addAll(datamodels);
             	SentenceModel sentModel = trainer.trainSentenceModel(tempModels, lang);
             	String outFile = options.valueOf("outfile") != null ? 
             			options.valueOf("outfile").toString()+"-sent.bin" : lang+"-sent.bin";
             	trainer.writeTrainedModel(sentModel, outFile, "sentence");
            }
           
            if(doPos) {
            	log.info("Training POS tagger");
            	//this is a bit ugly: the stream classes work by removing the first element of the model list
            	//, thus it would be empty after first training
            	List<OntModel> tempModels = new ArrayList<OntModel>();
            	tempModels.addAll(datamodels);
             	POSModel posModel = trainer.trainPOSModel(tempModels, lang);
             	String outFile = options.valueOf("outfile") != null ? 
             			options.valueOf("outfile").toString()+"-pos.bin" : lang+"-pos.bin";
             	trainer.writeTrainedModel(posModel, outFile, "pos"); 
            }

            if(doTokens) {
            	log.info("Training tokenizer");
            	//this is a bit ugly: the stream classes work by removing the first element of the model list
            	//, thus it would be empty after first training
            	List<OntModel> tempModels = new ArrayList<OntModel>();
            	tempModels.addAll(datamodels);
            	TokenizerModel tokenModel = trainer.trainTokenModel(tempModels, lang);
             	String outFile = options.valueOf("outfile") != null ? 
             			options.valueOf("outfile").toString()+"-token.bin" : lang+"-token.bin";
             	trainer.writeTrainedModel(tokenModel, outFile, "tokens"); 
            }
            
        } catch (ParameterException e) {
            ParameterParser.die(parser, e.getMessage());
          
        }
      
//        SentenceDetectorEvaluatorTool eval = new SentenceDetectorEvaluatorTool();
//        String[] evalargs = {"-model", "models/en-sent.bin","-data", "test_roundtrip", "-encoding","UTF-8"};
//        eval.run(null, evalargs);
 
    }
}
