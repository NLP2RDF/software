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

public class Brown implements OliaInterface { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/brown.owl#AP", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/brown.owl#IN", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/brown.owl#IN", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/brown.owl#WDT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#WDT", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/brown.owl#WDT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/brown.owl#ABN", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/brown.owl#CS", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/brown.owl#CS", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/brown.owl#SecondPossessivePronoun", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#SecondPossessivePronoun", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#SecondPossessivePronoun", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/brown.owl#HVN", "http://purl.org/olia/olia.owl#PastParticiple");
		links.put("http://purl.org/olia/brown.owl#HVN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#HVN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#NP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/brown.owl#HVZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVZ", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#HVZ", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown-link.rdf#third", "http://purl.org/olia/olia.owl#Third");
		links.put("http://purl.org/olia/brown-link.rdf#first", "http://purl.org/olia/olia.owl#First");
		links.put("http://purl.org/olia/brown.owl#PPO", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/brown.owl#PPO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#PPO", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/brown.owl#PPO", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#RBR", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown-link.rdf#comparative", "http://purl.org/olia/olia.owl#Comparative");
		links.put("http://purl.org/olia/brown-link.rdf#present", "http://purl.org/olia/olia.owl#AbsoluteTense");
		links.put("http://purl.org/olia/brown-link.rdf#present", "http://purl.org/olia/olia.owl#Present");
		links.put("http://purl.org/olia/brown.owl#EX", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/brown.owl#VBN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#VBN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#VBN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#VBN", "http://purl.org/olia/olia.owl#PastParticiple");
		links.put("http://purl.org/olia/brown.owl#VB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#BEZ", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BEZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#BEZ", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#CD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/brown.owl#CD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/brown.owl#CD", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/brown.owl#VBZ", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#VBZ", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#VBZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#RN", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#BEM", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BEM", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#BEM", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown-link.rdf#superlative", "http://purl.org/olia/olia.owl#Superlative");
		links.put("http://purl.org/olia/brown.owl#NNSpossessive", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#NNSpossessive", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown-link.rdf#positive", "http://purl.org/olia/olia.owl#Positive");
		links.put("http://purl.org/olia/brown.owl#comma", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown.owl#BED", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BED", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#DOD", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#DOD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#WPpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#WPpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#WPpossessive", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/brown.owl#NPSpossessive", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NPSpossessive", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/brown.owl#JJR", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/brown.owl#NC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#WPO", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/brown.owl#WPO", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#WPO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#WPS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#WPS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#WPS", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/brown.owl#NPS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NPS", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/brown.owl#NNS", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#NNS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown-link.rdf#plural", "http://purl.org/olia/olia.owl#Plural");
		links.put("http://purl.org/olia/brown.owl#PPLS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#PPLS", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/brown.owl#PPLS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#PPLS", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/brown.owl#VBG", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#VBG", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#VBG", "http://purl.org/olia/olia.owl#PresentParticiple");
		links.put("http://purl.org/olia/brown.owl#VBG", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#QL", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#UH", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/brown-link.rdf#second", "http://purl.org/olia/olia.owl#Second");
		links.put("http://purl.org/olia/brown.owl#DO", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown-link.rdf#past", "http://purl.org/olia/olia.owl#AbsoluteTense");
		links.put("http://purl.org/olia/brown-link.rdf#past", "http://purl.org/olia/olia.owl#Past");
		links.put("http://purl.org/olia/brown.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/brown.owl#CC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/brown.owl#sentenceCloser", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown.owl#closingParenthesis", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown.owl#colon", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown.owl#BEG", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BEG", "http://purl.org/olia/olia.owl#PresentParticiple");
		links.put("http://purl.org/olia/brown.owl#BEG", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#BEG", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#AT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#AT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/brown.owl#AT", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/brown.owl#NRS", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NRS", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#NRS", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#JJS", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/brown.owl#PPS", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/brown.owl#PPS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#PPS", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/brown.owl#PPS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown-link.rdf#nominative", "http://purl.org/olia/olia.owl#Nominative");
		links.put("http://purl.org/olia/brown.owl#PPpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#PPpossessive", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/brown.owl#PPpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#DOZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#DOZ", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#DOZ", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#openingParenthesis", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown-link.rdf#singular", "http://purl.org/olia/olia.owl#Singular");
		links.put("http://purl.org/olia/brown.owl#OD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/brown.owl#OD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/brown.owl#OD", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/brown.owl#RBT", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#RB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#BEDZ", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BEDZ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVD", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#NR", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NR", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#NR", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#RP", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#PNpossessive", "http://purl.org/olia/olia.owl#IndefinitePronoun");
		links.put("http://purl.org/olia/brown.owl#PNpossessive", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#PNpossessive", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#ABL", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#PN", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#PN", "http://purl.org/olia/olia.owl#IndefinitePronoun");
		links.put("http://purl.org/olia/brown.owl#PN", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#JJT", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/brown.owl#VBD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#VBD", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#QLP", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#NNpossessive", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#NNpossessive", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/brown.owl#BE", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVG", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#HVG", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#HVG", "http://purl.org/olia/olia.owl#PresentParticiple");
		links.put("http://purl.org/olia/brown.owl#HVG", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#NPpossessive", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/brown.owl#NPpossessive", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/brown.owl#negator", "http://purl.org/olia/olia.owl#Negation");
		links.put("http://purl.org/olia/brown.owl#PPL", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/brown.owl#PPL", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/brown.owl#PPL", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#PPL", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#TO", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/brown.owl#TO", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/brown.owl#WRB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#WRB", "http://purl.org/olia/olia.owl#WHTypeAdverbs");
		links.put("http://purl.org/olia/brown.owl#HV", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#MD", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#MD", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/brown.owl#MD", "http://purl.org/olia/olia.owl#ModalVerb");
		links.put("http://purl.org/olia/brown.owl#BER", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/brown.owl#BER", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#BER", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/brown.owl#JJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/brown.owl#dash", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/brown.owl#WQL", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/brown.owl#BEN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/brown.owl#BEN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/brown.owl#BEN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/brown.owl#BEN", "http://purl.org/olia/olia.owl#PastParticiple");
		links.put("http://purl.org/olia/brown.owl#PPSS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/brown.owl#PPSS", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/brown.owl#PPSS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/brown.owl#PPSS", "http://purl.org/olia/olia.owl#PersReflPronoun");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {
		
		hasTag.put("AP", "http://purl.org/olia/brown.owl#AP");
		hasTag.put("RBR", "http://purl.org/olia/brown.owl#RBR");
		hasTag.put("VBG", "http://purl.org/olia/brown.owl#VBG");
		hasTag.put("WDT", "http://purl.org/olia/brown.owl#WDT");
		hasTag.put("VBZ", "http://purl.org/olia/brown.owl#VBZ");
		hasTag.put("VBN", "http://purl.org/olia/brown.owl#VBN");
		hasTag.put("JJR", "http://purl.org/olia/brown.owl#JJR");
		hasTag.put("BEN", "http://purl.org/olia/brown.owl#BEN");
		hasTag.put("WRB", "http://purl.org/olia/brown.owl#WRB");
		hasTag.put("QLP", "http://purl.org/olia/brown.owl#QLP");
		hasTag.put("HV", "http://purl.org/olia/brown.owl#HV");
		hasTag.put("WPS", "http://purl.org/olia/brown.owl#WPS");
		hasTag.put("HL", "http://purl.org/olia/brown.owl#HL");
		hasTag.put("NP$", "http://purl.org/olia/brown.owl#NPpossessive");
		hasTag.put("HVZ", "http://purl.org/olia/brown.owl#HVZ");
		hasTag.put("BEG", "http://purl.org/olia/brown.owl#BEG");
		hasTag.put("BER", "http://purl.org/olia/brown.owl#BER");
		hasTag.put("DO", "http://purl.org/olia/brown.owl#DO");
		hasTag.put(".", "http://purl.org/olia/brown.owl#sentenceCloser");
		hasTag.put("sentenceCloser", "http://purl.org/olia/brown.owl#sentenceCloser");
		hasTag.put("comma", "http://purl.org/olia/brown.owl#comma");
		hasTag.put("DTX", "http://purl.org/olia/brown.owl#DTX");
		hasTag.put("AT", "http://purl.org/olia/brown.owl#AT");
		hasTag.put("NPS$", "http://purl.org/olia/brown.owl#NPSpossessive");
		hasTag.put("HVN", "http://purl.org/olia/brown.owl#HVN");
		hasTag.put("NR", "http://purl.org/olia/brown.owl#NR");
		hasTag.put("DOD", "http://purl.org/olia/brown.owl#DOD");
		hasTag.put("PP$", "http://purl.org/olia/brown.owl#PPpossessive");
		hasTag.put("RB", "http://purl.org/olia/brown.owl#RB");
		hasTag.put("BE", "http://purl.org/olia/brown.owl#BE");
		hasTag.put("(", "http://purl.org/olia/brown.owl#openingParenthesis");
		hasTag.put("DTI", "http://purl.org/olia/brown.owl#DTI");
		hasTag.put("HVG", "http://purl.org/olia/brown.owl#HVG");
		hasTag.put("MD", "http://purl.org/olia/brown.owl#MD");
		hasTag.put("RP", "http://purl.org/olia/brown.owl#RP");
		hasTag.put("RBT", "http://purl.org/olia/brown.owl#RBT");
		hasTag.put("TO", "http://purl.org/olia/brown.owl#TO");
		hasTag.put("JJT", "http://purl.org/olia/brown.owl#JJT");
		hasTag.put(":", "http://purl.org/olia/brown.owl#colon");
		hasTag.put("colon", "http://purl.org/olia/brown.owl#colon");
		hasTag.put("JJ", "http://purl.org/olia/brown.owl#JJ");
		hasTag.put("BEZ", "http://purl.org/olia/brown.owl#BEZ");
		hasTag.put(")", "http://purl.org/olia/brown.owl#closingParenthesis");
		hasTag.put("DT", "http://purl.org/olia/brown.owl#DT");
		hasTag.put("PN$", "http://purl.org/olia/brown.owl#PNpossessive");
		hasTag.put("QL", "http://purl.org/olia/brown.owl#QL");
		hasTag.put("CD", "http://purl.org/olia/brown.owl#CD");
		hasTag.put("DOZ", "http://purl.org/olia/brown.owl#DOZ");
		hasTag.put("BEDZ", "http://purl.org/olia/brown.owl#BEDZ");
		hasTag.put("OD", "http://purl.org/olia/brown.owl#OD");
		hasTag.put("BEM", "http://purl.org/olia/brown.owl#BEM");
		hasTag.put("ABN", "http://purl.org/olia/brown.owl#ABN");
		hasTag.put("CS", "http://purl.org/olia/brown.owl#CS");
		hasTag.put("NN", "http://purl.org/olia/brown.owl#NN");
		hasTag.put("AP", "http://purl.org/olia/brown.owl#AP");
		hasTag.put("NNS", "http://purl.org/olia/brown.owl#NNS");
		hasTag.put("WP$", "http://purl.org/olia/brown.owl#WPpossessive");
		hasTag.put("WQL", "http://purl.org/olia/brown.owl#WQL");
		hasTag.put("RN", "http://purl.org/olia/brown.owl#RN");
		hasTag.put("*", "http://purl.org/olia/brown.owl#negator");
		hasTag.put("NN$", "http://purl.org/olia/brown.owl#NNpossessive");
		hasTag.put("CC", "http://purl.org/olia/brown.owl#CC");
		hasTag.put("PPL", "http://purl.org/olia/brown.owl#PPL");
		hasTag.put("JJS", "http://purl.org/olia/brown.owl#JJS");
		hasTag.put("FW", "http://purl.org/olia/brown.owl#FW");
		hasTag.put("PPO", "http://purl.org/olia/brown.owl#PPO");
		hasTag.put("PP$$", "http://purl.org/olia/brown.owl#SecondPossessivePronoun");
		hasTag.put("NC", "http://purl.org/olia/brown.owl#NC");
		hasTag.put("VB", "http://purl.org/olia/brown.owl#VB");
		hasTag.put("--", "http://purl.org/olia/brown.owl#dash");
		hasTag.put("VBD", "http://purl.org/olia/brown.owl#VBD");
		hasTag.put("NNS$", "http://purl.org/olia/brown.owl#NNSpossessive");
		hasTag.put("NPS", "http://purl.org/olia/brown.owl#NPS");
		hasTag.put("PPSS", "http://purl.org/olia/brown.owl#PPSS");
		hasTag.put("EX", "http://purl.org/olia/brown.owl#EX");
		hasTag.put("HVD", "http://purl.org/olia/brown.owl#HVD");
		hasTag.put("IN", "http://purl.org/olia/brown.owl#IN");
		hasTag.put("BED", "http://purl.org/olia/brown.owl#BED");
		hasTag.put("PPS", "http://purl.org/olia/brown.owl#PPS");
		hasTag.put("ABX", "http://purl.org/olia/brown.owl#ABX");
		hasTag.put("NRS", "http://purl.org/olia/brown.owl#NRS");
		hasTag.put("PN", "http://purl.org/olia/brown.owl#PN");
		hasTag.put(",", "http://purl.org/olia/brown.owl#comma");
		hasTag.put("WPO", "http://purl.org/olia/brown.owl#WPO");
		hasTag.put("PPLS", "http://purl.org/olia/brown.owl#PPLS");
		hasTag.put("NP", "http://purl.org/olia/brown.owl#NP");
		hasTag.put("DTS", "http://purl.org/olia/brown.owl#DTS");
		hasTag.put("TL", "http://purl.org/olia/brown.owl#TL");
		hasTag.put("ABL", "http://purl.org/olia/brown.owl#ABL");
		hasTag.put("UH", "http://purl.org/olia/brown.owl#UH");
	}
	
	@Override 
	public MultiValueMap getLinks()
	{
		return links;
	}
	
	@Override 
	public MultiValueMap getTags()
	{
		return hasTag;
	}
	

	}
	
