package org.nlp2rdf.implementation.conll;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.junit.Test;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.urischemes.RFC5147String;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;

public class ConLLTest {
	
	private static Logger logger = LoggerFactory.getLogger(ConLLToNIF.class);

    @Test
    public void testConLL() {
    	try {

            String[] args = new String[]{"-i='tigertest.conll'", "-intype='file'", "-f='text'", "-tagset='Stts'"};
            OptionParser parser = ParameterParser.getParser(args, "http://example.org/conll#");
    		parser.acceptsAll(asList("tagset"), "").withRequiredArg().ofType(String.class).describedAs("tagset name supported by olia");	  
    	  	OptionSet options = ParameterParser.getOption(parser, args);
    	  	ParameterParser.handleHelpAndWS(options, "");
    	  	NIFParameters nifParameters = ParameterParser.parseOptions(options, false);
			OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());  
			
			ConLLToNIF converter = new ConLLToNIF();
			converter.transform(model, model, nifParameters);
			
    	} catch(FileNotFoundException fnf) {
    		
    	} catch(IOException io) {
    		
    	} catch(ParameterException param) {
    		
    	}
    }
}
