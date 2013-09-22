package org.nlp2rdf.vm.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Connexor_phrase { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/connexor.owl#MAIN", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/connexor.owl#ADVL", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#ADVL", "http://purl.org/olia/olia.owl#AdverbPhrase");
		links.put("http://purl.org/olia/connexor.owl#PREMOD", "http://purl.org/olia/olia.owl#AdjectivePhrase");
		links.put("http://purl.org/olia/connexor.owl#PREMOD", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#NH", "http://purl.org/olia/olia.owl#NounHeadedPhrase");
		links.put("http://purl.org/olia/connexor.owl#NH", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#POSTMOD", "http://purl.org/olia/olia.owl#AdjectivePhrase");
		links.put("http://purl.org/olia/connexor.owl#POSTMOD", "http://purl.org/olia/olia.owl#Phrase");
	}
	

	}
	
