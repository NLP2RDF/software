package org.nlp2rdf.vm.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Ancorra { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/ancorra.owl#DEM", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/ancorra.owl#DEM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/ancorra.owl#DEM", "http://purl.org/olia/olia.owl#DemonstrativeDeterminer");
		links.put("http://purl.org/olia/ancorra.owl#DEM", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/ancorra.owl#DEM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/ancorra.owl#RB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/ancorra.owl#VAUX", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/ancorra.owl#VAUX", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/ancorra.owl#JJC", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/ancorra.owl#JJC", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/ancorra.owl#NEG", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/ancorra.owl#NEG", "http://purl.org/olia/olia.owl#NegativeParticle");
		links.put("http://purl.org/olia/ancorra.owl#NEG", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/ancorra.owl#PSP", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/ancorra.owl#PSP", "http://purl.org/olia/olia.owl#Postposition");
		links.put("http://purl.org/olia/ancorra.owl#_C", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/ancorra.owl#QO", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/ancorra.owl#QO", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/ancorra.owl#QO", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/ancorra.owl#QC", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/ancorra.owl#QC", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/ancorra.owl#QC", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/ancorra.owl#NNPC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/ancorra.owl#NNPC", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/ancorra.owl#NNPC", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/ancorra.owl#UT", "http://purl.org/olia/olia.owl#QuotativeVerb");
		links.put("http://purl.org/olia/ancorra.owl#SYM", "http://purl.org/olia/olia.owl#Symbol");
		links.put("http://purl.org/olia/ancorra.owl#SYM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/ancorra.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/ancorra.owl#JJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/ancorra.owl#RDP", "http://purl.org/olia/olia.owl#Reduplication");
		links.put("http://purl.org/olia/ancorra.owl#RP", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/ancorra.owl#RP", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/ancorra.owl#INJ", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/ancorra.owl#VM", "http://purl.org/olia/olia.owl#MainVerb");
		links.put("http://purl.org/olia/ancorra.owl#VM", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/ancorra.owl#NST", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/ancorra.owl#NST", "http://purl.org/olia/olia.owl#SpatiotemporalNoun");
		links.put("http://purl.org/olia/ancorra.owl#NST", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/ancorra.owl#QF", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/ancorra.owl#ECH", "http://purl.org/olia/olia.owl#Reduplication");
		links.put("http://purl.org/olia/ancorra.owl#ECH", "http://purl.org/olia/olia.owl#EchoWord");
		links.put("http://purl.org/olia/ancorra.owl#NNC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/ancorra.owl#NNC", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/ancorra.owl#NNC", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/ancorra.owl#NNP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/ancorra.owl#NNP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/ancorra.owl#PRP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/ancorra.owl#PRP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/ancorra.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/ancorra.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/ancorra.owl#WQ", "http://purl.org/olia/olia.owl#InterrogativeParticle");
		links.put("http://purl.org/olia/ancorra.owl#WQ", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/ancorra.owl#WQ", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/ancorra.owl#INTF", "http://purl.org/olia/olia.owl#Intensifier");
		links.put("http://purl.org/olia/ancorra.owl#INTF", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/ancorra.owl#CL", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/ancorra.owl#CL", "http://purl.org/olia/olia.owl#Classifier");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("CC", "http://purl.org/olia/ancorra.owl#CC");
		hasTag.put("CL", "http://purl.org/olia/ancorra.owl#CL");
		hasTag.put("DEM", "http://purl.org/olia/ancorra.owl#DEM");
		hasTag.put("ECH", "http://purl.org/olia/ancorra.owl#ECH");
		hasTag.put("INJ", "http://purl.org/olia/ancorra.owl#INJ");
		hasTag.put("INTF", "http://purl.org/olia/ancorra.owl#INTF");
		hasTag.put("JJ", "http://purl.org/olia/ancorra.owl#JJ");
		hasTag.put("JJC", "http://purl.org/olia/ancorra.owl#JJC");
		hasTag.put("NEG", "http://purl.org/olia/ancorra.owl#NEG");
		hasTag.put("NN", "http://purl.org/olia/ancorra.owl#NN");
		hasTag.put("NNC", "http://purl.org/olia/ancorra.owl#NNC");
		hasTag.put("NNP", "http://purl.org/olia/ancorra.owl#NNP");
		hasTag.put("NNPC", "http://purl.org/olia/ancorra.owl#NNPC");
		hasTag.put("NST", "http://purl.org/olia/ancorra.owl#NST");
		hasTag.put("PRP", "http://purl.org/olia/ancorra.owl#PRP");
		hasTag.put("PSP", "http://purl.org/olia/ancorra.owl#PSP");
		hasTag.put("QC", "http://purl.org/olia/ancorra.owl#QC");
		hasTag.put("QF", "http://purl.org/olia/ancorra.owl#QF");
		hasTag.put("QO", "http://purl.org/olia/ancorra.owl#QO");
		hasTag.put("RB", "http://purl.org/olia/ancorra.owl#RB");
		hasTag.put("RDP", "http://purl.org/olia/ancorra.owl#RDP");
		hasTag.put("RP", "http://purl.org/olia/ancorra.owl#RP");
		hasTag.put("SYM", "http://purl.org/olia/ancorra.owl#SYM");
		hasTag.put("UNK", "http://purl.org/olia/ancorra.owl#UNK");
		hasTag.put("UT", "http://purl.org/olia/ancorra.owl#UT");
		hasTag.put("VAUX", "http://purl.org/olia/ancorra.owl#VAUX");
		hasTag.put("VM", "http://purl.org/olia/ancorra.owl#VM");
		hasTag.put("WQ", "http://purl.org/olia/ancorra.owl#WQ");
		hasTag.put("*C", "http://purl.org/olia/ancorra.owl#_C");
	}
	

	}
	
