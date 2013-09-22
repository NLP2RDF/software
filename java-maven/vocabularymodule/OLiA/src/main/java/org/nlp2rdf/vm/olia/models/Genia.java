package org.nlp2rdf.vm.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Genia { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/genia.owl#LS", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/genia.owl#NNPS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/genia.owl#NNPS", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/genia.owl#EX", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/genia.owl#MD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/genia.owl#MD", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/genia.owl#MD", "http://purl.org/olia/olia.owl#ModalVerb");
		links.put("http://purl.org/olia/genia.owl#RBR", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/genia.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/genia.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/genia.owl#WDT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/genia.owl#WDT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/genia.owl#WDT", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/genia.owl#NNS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/genia.owl#NNS", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/genia.owl#CC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/genia.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/genia.owl#SYM", "http://purl.org/olia/olia.owl#Symbol");
		links.put("http://purl.org/olia/genia.owl#SYM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/genia.owl#JJR", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/genia.owl#VB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/genia.owl#VBG", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/genia.owl#VBG", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/genia.owl#VBG", "http://purl.org/olia/olia.owl#ing");
		links.put("http://purl.org/olia/genia.owl#VBG", "http://purl.org/olia/olia.owl#Gerund");
		links.put("http://purl.org/olia/genia.owl#VBG", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/genia.owl#JJS", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/genia.owl#FW", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/genia.owl#FW", "http://purl.org/olia/olia.owl#Foreign");
		links.put("http://purl.org/olia/genia.owl#RBS", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/genia.owl#PRPpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/genia.owl#PRPpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/genia.owl#PRPpossessive", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/genia.owl#WP", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/genia.owl#WP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/genia.owl#WP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/genia.owl#CD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/genia.owl#CD", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/genia.owl#CD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/genia.owl#NNP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/genia.owl#NNP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/genia.owl#VBN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/genia.owl#VBN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/genia.owl#VBN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/genia.owl#JJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/genia.owl#WRB", "http://purl.org/olia/olia.owl#WHTypeAdverbs");
		links.put("http://purl.org/olia/genia.owl#WRB", "http://purl.org/olia/olia.owl#Adverb");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("WP", "http://purl.org/olia/genia.owl#WP");
		hasTag.put("RP", "http://purl.org/olia/genia.owl#RP");
		hasTag.put("NNS", "http://purl.org/olia/genia.owl#NNS");
		hasTag.put("VBN", "http://purl.org/olia/genia.owl#VBN");
		hasTag.put("RBS", "http://purl.org/olia/genia.owl#RBS");
		hasTag.put("VB", "http://purl.org/olia/genia.owl#VB");
		hasTag.put("PDT", "http://purl.org/olia/genia.owl#PDT");
		hasTag.put("FW", "http://purl.org/olia/genia.owl#FW");
		hasTag.put("COLON", "http://purl.org/olia/genia.owl#COLON");
		hasTag.put("NNPS", "http://purl.org/olia/genia.owl#NNPS");
		hasTag.put("LQT", "http://purl.org/olia/genia.owl#LQT");
		hasTag.put("TO", "http://purl.org/olia/genia.owl#TO");
		hasTag.put("PRP", "http://purl.org/olia/genia.owl#PRP");
		hasTag.put("DT", "http://purl.org/olia/genia.owl#DT");
		hasTag.put("VBP", "http://purl.org/olia/genia.owl#VBP");
		hasTag.put("NN", "http://purl.org/olia/genia.owl#NN");
		hasTag.put("RB", "http://purl.org/olia/genia.owl#RB");
		hasTag.put("WP$", "http://purl.org/olia/genia.owl#WPpossessive");
		hasTag.put("LRB", "http://purl.org/olia/genia.owl#LRB");
		hasTag.put("NNP", "http://purl.org/olia/genia.owl#NNP");
		hasTag.put("WRB", "http://purl.org/olia/genia.owl#WRB");
		hasTag.put("RRB", "http://purl.org/olia/genia.owl#RRB");
		hasTag.put("CC", "http://purl.org/olia/genia.owl#CC");
		hasTag.put("IN", "http://purl.org/olia/genia.owl#IN");
		hasTag.put("JJ", "http://purl.org/olia/genia.owl#JJ");
		hasTag.put("VBZ", "http://purl.org/olia/genia.owl#VBZ");
		hasTag.put("JJR", "http://purl.org/olia/genia.owl#JJR");
		hasTag.put("RQT", "http://purl.org/olia/genia.owl#RQT");
		hasTag.put("CD", "http://purl.org/olia/genia.owl#CD");
		hasTag.put("VBD", "http://purl.org/olia/genia.owl#VBD");
		hasTag.put("RBR", "http://purl.org/olia/genia.owl#RBR");
		hasTag.put("PERIOD", "http://purl.org/olia/genia.owl#PERIOD");
		hasTag.put("EX", "http://purl.org/olia/genia.owl#EX");
		hasTag.put("PRP$", "http://purl.org/olia/genia.owl#PRPpossessive");
		hasTag.put("WDT", "http://purl.org/olia/genia.owl#WDT");
		hasTag.put("JJS", "http://purl.org/olia/genia.owl#JJS");
		hasTag.put("MD", "http://purl.org/olia/genia.owl#MD");
		hasTag.put("SYM", "http://purl.org/olia/genia.owl#SYM");
		hasTag.put("POS", "http://purl.org/olia/genia.owl#POS");
		hasTag.put("LS", "http://purl.org/olia/genia.owl#LS");
		hasTag.put("VBG", "http://purl.org/olia/genia.owl#VBG");
		hasTag.put("COMMA", "http://purl.org/olia/genia.owl#COMMA");
	}
	

	}
	
