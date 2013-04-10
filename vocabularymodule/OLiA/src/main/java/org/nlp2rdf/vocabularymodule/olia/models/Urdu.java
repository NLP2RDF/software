package org.nlp2rdf.vocabularymodule.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Urdu { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/urdu.owl#PD", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/urdu.owl#PD", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#KP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#KP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#KP", "http://purl.org/olia/olia.owl#InterrogativePronoun");
		links.put("http://purl.org/olia/urdu.owl#KP", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/urdu.owl#REP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#REP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#REP", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/urdu.owl#REP", "http://purl.org/olia/olia.owl#RelativePronoun");
		links.put("http://purl.org/olia/urdu.owl#CA", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/urdu.owl#CA", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/urdu.owl#CA", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/urdu.owl#AKP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#AKP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#RP", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/urdu.owl#RP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#RP", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/urdu.owl#RP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#TA", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/urdu.owl#TA", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/urdu.owl#SM", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/urdu.owl#SM", "http://purl.org/olia/olia.owl#SentenceFinalPunctuation");
		links.put("http://purl.org/olia/urdu.owl#SM", "http://purl.org/olia/olia.owl#MainPunctuation");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#SyntacticAdjunct");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#PreNominalModifier");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#AdnominalConstituent");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#TitleNoun");
		links.put("http://purl.org/olia/urdu.owl#PRT", "http://purl.org/olia/olia.owl#Modifier");
		links.put("http://purl.org/olia/urdu.owl#QW", "http://purl.org/olia/olia.owl#InterrogativeParticle");
		links.put("http://purl.org/olia/urdu.owl#QW", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/urdu.owl#QW", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/urdu.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/urdu.owl#CC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/urdu.owl#Exp", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/urdu.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/urdu.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/urdu.owl#NEG", "http://purl.org/olia/olia.owl#Negation");
		links.put("http://purl.org/olia/urdu.owl#KER", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/urdu.owl#KER", "http://purl.org/olia/olia.owl#VerbalParticle");
		links.put("http://purl.org/olia/urdu.owl#KER", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/urdu.owl#INT", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/urdu.owl#I", "http://purl.org/olia/olia.owl#Intensifier");
		links.put("http://purl.org/olia/urdu.owl#I", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/urdu.owl#DATE", "http://purl.org/olia/olia.owl#Date");
		links.put("http://purl.org/olia/urdu.owl#DATE", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/urdu.owl#PN", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/urdu.owl#PN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/urdu.owl#G", "http://purl.org/olia/olia.owl#Izafat");
		links.put("http://purl.org/olia/urdu.owl#G", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/urdu.owl#PP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#PP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#PP", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/urdu.owl#PP", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/urdu.owl#VB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#SyntacticAdjunct");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#Modifier");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#PostNominalModifier");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#AdnominalConstituent");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/urdu.owl#POT", "http://purl.org/olia/olia.owl#TitleNoun");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#AP", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/urdu.owl#OR", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/urdu.owl#OR", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/urdu.owl#OR", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/urdu.owl#MUL", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/urdu.owl#MUL", "http://purl.org/olia/olia.owl#MultipleNumeral");
		links.put("http://purl.org/olia/urdu.owl#MUL", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/urdu.owl#A", "http://purl.org/olia/olia.owl#ParticipleAdjective");
		links.put("http://purl.org/olia/urdu.owl#A", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/urdu.owl#A", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/urdu.owl#A", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/urdu.owl#A", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/urdu.owl#RD", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#RD", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/urdu.owl#RD", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/urdu.owl#RD", "http://purl.org/olia/olia.owl#RelativeDeterminer");
		links.put("http://purl.org/olia/urdu.owl#FR", "http://purl.org/olia/olia.owl#Fraction");
		links.put("http://purl.org/olia/urdu.owl#FR", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/urdu.owl#FR", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#DemonstrativeAdverb");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#DemonstrativePronoun");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/urdu.owl#AD", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#Q", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/urdu.owl#ADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/urdu.owl#WALA", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/urdu.owl#WALA", "http://purl.org/olia/olia.owl#PossessionMarker");
		links.put("http://purl.org/olia/urdu.owl#PM", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/urdu.owl#PM", "http://purl.org/olia/olia.owl#SecondaryPunctuation");
		links.put("http://purl.org/olia/urdu.owl#PM", "http://purl.org/olia/olia.owl#SentenceMedialPunctuation");
		links.put("http://purl.org/olia/urdu.owl#AA", "http://purl.org/olia/olia.owl#StrictAuxiliaryVerb");
		links.put("http://purl.org/olia/urdu.owl#AA", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/urdu.owl#AA", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/urdu.owl#AA", "http://purl.org/olia/olia.owl#AspectMarkingAuxiliary");
		links.put("http://purl.org/olia/urdu.owl#KD", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/urdu.owl#KD", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/urdu.owl#KD", "http://purl.org/olia/olia.owl#InterrogativeDeterminer");
		links.put("http://purl.org/olia/urdu.owl#KD", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/urdu.owl#ADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/urdu.owl#SC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/urdu.owl#SC", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/urdu.owl#U", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/urdu.owl#U", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/urdu.owl#U", "http://purl.org/olia/olia.owl#UnitNoun");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("A", "http://purl.org/olia/urdu.owl#A");
		hasTag.put("AA", "http://purl.org/olia/urdu.owl#AA");
		hasTag.put("KD", "http://purl.org/olia/urdu.owl#AD");
		hasTag.put("ADJ", "http://purl.org/olia/urdu.owl#ADJ");
		hasTag.put("ADV", "http://purl.org/olia/urdu.owl#ADV");
		hasTag.put("AKP", "http://purl.org/olia/urdu.owl#AKP");
		hasTag.put("AP", "http://purl.org/olia/urdu.owl#AP");
		hasTag.put("CA", "http://purl.org/olia/urdu.owl#CA");
		hasTag.put("CC", "http://purl.org/olia/urdu.owl#CC");
		hasTag.put("DATE", "http://purl.org/olia/urdu.owl#DATE");
		hasTag.put("Exp", "http://purl.org/olia/urdu.owl#Exp");
		hasTag.put("FR", "http://purl.org/olia/urdu.owl#FR");
		hasTag.put("G", "http://purl.org/olia/urdu.owl#G");
		hasTag.put("GR", "http://purl.org/olia/urdu.owl#GR");
		hasTag.put("I", "http://purl.org/olia/urdu.owl#I");
		hasTag.put("INT", "http://purl.org/olia/urdu.owl#INT");
		hasTag.put("AD", "http://purl.org/olia/urdu.owl#KD");
		hasTag.put("KER", "http://purl.org/olia/urdu.owl#KER");
		hasTag.put("KP", "http://purl.org/olia/urdu.owl#KP");
		hasTag.put("MUL", "http://purl.org/olia/urdu.owl#MUL");
		hasTag.put("NEG", "http://purl.org/olia/urdu.owl#NEG");
		hasTag.put("NN", "http://purl.org/olia/urdu.owl#NN");
		hasTag.put("OR", "http://purl.org/olia/urdu.owl#OR");
		hasTag.put("P", "http://purl.org/olia/urdu.owl#P");
		hasTag.put("PD", "http://purl.org/olia/urdu.owl#PD");
		hasTag.put("PM", "http://purl.org/olia/urdu.owl#PM");
		hasTag.put("PN", "http://purl.org/olia/urdu.owl#PN");
		hasTag.put("POT", "http://purl.org/olia/urdu.owl#POT");
		hasTag.put("PP", "http://purl.org/olia/urdu.owl#PP");
		hasTag.put("PRT", "http://purl.org/olia/urdu.owl#PRT");
		hasTag.put("Q", "http://purl.org/olia/urdu.owl#Q");
		hasTag.put("QW", "http://purl.org/olia/urdu.owl#QW");
		hasTag.put("RD", "http://purl.org/olia/urdu.owl#RD");
		hasTag.put("REP", "http://purl.org/olia/urdu.owl#REP");
		hasTag.put("RP", "http://purl.org/olia/urdu.owl#RP");
		hasTag.put("SC", "http://purl.org/olia/urdu.owl#SC");
		hasTag.put("SE", "http://purl.org/olia/urdu.owl#SE");
		hasTag.put("SM", "http://purl.org/olia/urdu.owl#SM");
		hasTag.put("TA", "http://purl.org/olia/urdu.owl#TA");
		hasTag.put("U", "http://purl.org/olia/urdu.owl#U");
		hasTag.put("VB", "http://purl.org/olia/urdu.owl#VB");
		hasTag.put("WALA", "http://purl.org/olia/urdu.owl#WALA");
	}
	

	}
	
