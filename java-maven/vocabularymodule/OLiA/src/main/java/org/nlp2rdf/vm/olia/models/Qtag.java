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

package org.nlp2rdf.vm.olia.models;

import org.apache.commons.collections.map.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Qtag { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/qtag.owl#closingParenthesis", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#RB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/qtag.owl#CS", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/qtag.owl#CS", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/qtag.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/qtag.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/qtag.owl#CD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/qtag.owl#CD", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/qtag.owl#CD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/qtag.owl#IN", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/qtag.owl#IN", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/qtag.owl#OD", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/qtag.owl#OD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/qtag.owl#OD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/qtag.owl#comma", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#SYM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/qtag.owl#SYM", "http://purl.org/olia/olia.owl#Symbol");
		links.put("http://purl.org/olia/qtag.owl#PPpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#PPpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#PPpossessive", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/qtag.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/qtag.owl#CC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/qtag.owl#PP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#PP", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/qtag.owl#PP", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/qtag.owl#PP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#sentenceCloser", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#JJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/qtag.owl#RP", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/qtag.owl#RP", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/qtag.owl#MD", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/qtag.owl#MD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#quotationMark", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#RBR", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/qtag.owl#VBN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/qtag.owl#VBN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#VBN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/qtag.owl#VBN", "http://purl.org/olia/olia.owl#PastParticiple");
		links.put("http://purl.org/olia/qtag.owl#RBS", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/qtag.owl#XNOT", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/qtag.owl#WRB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/qtag.owl#WRB", "http://purl.org/olia/olia.owl#WHTypeAdverbs");
		links.put("http://purl.org/olia/qtag.owl#colon", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#VBZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#WPpossessive", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/qtag.owl#WPpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#WPpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#openingParenthesis", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#NP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/qtag.owl#NP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/qtag.owl#PPX", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#PPX", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/qtag.owl#PPX", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/qtag.owl#PPX", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#NNS", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/qtag.owl#NNS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/qtag.owl#dash", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/qtag.owl#PDT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#PDT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/qtag.owl#VBG", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/qtag.owl#VBG", "http://purl.org/olia/olia.owl#PresentParticiple");
		links.put("http://purl.org/olia/qtag.owl#VBG", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#VBG", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/qtag.owl#NPS", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/qtag.owl#NPS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/qtag.owl#PN", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#PN", "http://purl.org/olia/olia.owl#IndefinitePronoun");
		links.put("http://purl.org/olia/qtag.owl#PN", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#JJR", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/qtag.owl#VB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#VBD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/qtag.owl#DT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#DT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/qtag.owl#UH", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/qtag.owl#FW", "http://purl.org/olia/olia.owl#Foreign");
		links.put("http://purl.org/olia/qtag.owl#FW", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/qtag.owl#EX", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/qtag.owl#WDT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/qtag.owl#WDT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#WDT", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/qtag.owl#WP", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/qtag.owl#WP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/qtag.owl#WP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/qtag.owl#POS", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/qtag.owl#JJS", "http://purl.org/olia/olia.owl#Adjective");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("VBN", "http://purl.org/olia/qtag.owl#VBN");
		hasTag.put("BER", "http://purl.org/olia/qtag.owl#BER");
		hasTag.put("SYM", "http://purl.org/olia/qtag.owl#SYM");
		hasTag.put(",", "http://purl.org/olia/qtag.owl#comma");
		hasTag.put("DOG", "http://purl.org/olia/qtag.owl#DOG");
		hasTag.put("DOZ", "http://purl.org/olia/qtag.owl#DOZ");
		hasTag.put("CD", "http://purl.org/olia/qtag.owl#CD");
		hasTag.put("NN", "http://purl.org/olia/qtag.owl#NN");
		hasTag.put("OD", "http://purl.org/olia/qtag.owl#OD");
		hasTag.put("RB", "http://purl.org/olia/qtag.owl#RB");
		hasTag.put("(", "http://purl.org/olia/qtag.owl#openingParenthesis");
		hasTag.put("UH", "http://purl.org/olia/qtag.owl#UH");
		hasTag.put("DO", "http://purl.org/olia/qtag.owl#DO");
		hasTag.put("VB", "http://purl.org/olia/qtag.owl#VB");
		hasTag.put("BEN", "http://purl.org/olia/qtag.owl#BEN");
		hasTag.put("JJS", "http://purl.org/olia/qtag.owl#JJS");
		hasTag.put("CS", "http://purl.org/olia/qtag.owl#CS");
		hasTag.put("HVD", "http://purl.org/olia/qtag.owl#HVD");
		hasTag.put("POS", "http://purl.org/olia/qtag.owl#POS");
		hasTag.put("", "http://purl.org/olia/qtag.owl#WP");
		hasTag.put("\"", "http://purl.org/olia/qtag.owl#quotationMark");
		hasTag.put("BEDZ", "http://purl.org/olia/qtag.owl#BEDZ");
		hasTag.put("RBR", "http://purl.org/olia/qtag.owl#RBR");
		hasTag.put("EX", "http://purl.org/olia/qtag.owl#EX");
		hasTag.put("-", "http://purl.org/olia/qtag.owl#dash");
		hasTag.put("DT", "http://purl.org/olia/qtag.owl#DT");
		hasTag.put("HV", "http://purl.org/olia/qtag.owl#HV");
		hasTag.put("MD", "http://purl.org/olia/qtag.owl#MD");
		hasTag.put("PN", "http://purl.org/olia/qtag.owl#PN");
		hasTag.put("HVG", "http://purl.org/olia/qtag.owl#HVG");
		hasTag.put("PP", "http://purl.org/olia/qtag.owl#PP");
		hasTag.put("VBG", "http://purl.org/olia/qtag.owl#VBG");
		hasTag.put("IN", "http://purl.org/olia/qtag.owl#IN");
		hasTag.put("NNS", "http://purl.org/olia/qtag.owl#NNS");
		hasTag.put("FW", "http://purl.org/olia/qtag.owl#FW");
		hasTag.put("NP", "http://purl.org/olia/qtag.owl#NP");
		hasTag.put(")", "http://purl.org/olia/qtag.owl#closingParenthesis");
		hasTag.put("RP", "http://purl.org/olia/qtag.owl#RP");
		hasTag.put("DOD", "http://purl.org/olia/qtag.owl#DOD");
		hasTag.put("NPS", "http://purl.org/olia/qtag.owl#NPS");
		hasTag.put("HVZ", "http://purl.org/olia/qtag.owl#HVZ");
		hasTag.put("BEG", "http://purl.org/olia/qtag.owl#BEG");
		hasTag.put("JJR", "http://purl.org/olia/qtag.owl#JJR");
		hasTag.put("BE", "http://purl.org/olia/qtag.owl#BE");
		hasTag.put(":", "http://purl.org/olia/qtag.owl#colon");
		hasTag.put("BEDR", "http://purl.org/olia/qtag.owl#BEDR");
		hasTag.put("WRB", "http://purl.org/olia/qtag.owl#WRB");
		hasTag.put("RBS", "http://purl.org/olia/qtag.owl#RBS");
		hasTag.put("BEM", "http://purl.org/olia/qtag.owl#BEM");
		hasTag.put(". ; ?\n      !", "http://purl.org/olia/qtag.owl#sentenceCloser");
		hasTag.put("XNOT", "http://purl.org/olia/qtag.owl#XNOT");
		hasTag.put("VBZ", "http://purl.org/olia/qtag.owl#VBZ");
		hasTag.put("PP$", "http://purl.org/olia/qtag.owl#PPpossessive");
		hasTag.put("BEZ", "http://purl.org/olia/qtag.owl#BEZ");
		hasTag.put("HVN", "http://purl.org/olia/qtag.owl#HVN");
		hasTag.put("VBD", "http://purl.org/olia/qtag.owl#VBD");
		hasTag.put("CC", "http://purl.org/olia/qtag.owl#CC");
		hasTag.put("WP$", "http://purl.org/olia/qtag.owl#WPpossessive");
		hasTag.put("JJ", "http://purl.org/olia/qtag.owl#JJ");
		hasTag.put("WDT", "http://purl.org/olia/qtag.owl#WDT");
		hasTag.put("PPX", "http://purl.org/olia/qtag.owl#PPX");
		hasTag.put("DON", "http://purl.org/olia/qtag.owl#DON");
		hasTag.put("TO", "http://purl.org/olia/qtag.owl#TO");
		hasTag.put("PDT", "http://purl.org/olia/qtag.owl#PDT");
	}
	

	}
	
