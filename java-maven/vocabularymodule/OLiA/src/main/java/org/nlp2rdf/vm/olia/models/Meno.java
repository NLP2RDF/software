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

public class Meno { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/meno.owl#PInd", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#PInd", "http://purl.org/olia/olia.owl#IndefinitePronoun");
		links.put("http://purl.org/olia/meno.owl#PInd", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#Adjective", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/meno.owl#Adverb", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/meno.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#CC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/meno.owl#DDet", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/meno.owl#DDet", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/meno.owl#DDet", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#DDet", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#DDet", "http://purl.org/olia/olia.owl#DemonstrativeDeterminer");
		links.put("http://purl.org/olia/meno.owl#Imperative", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/meno.owl#Imperative", "http://purl.org/olia/olia.owl#ImperativeVerb");
		links.put("http://purl.org/olia/meno.owl#Imperative", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#u", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/meno.owl#PronounOrDeterminer", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#Unassigned", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/meno.owl#PInt", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#PInt", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/meno.owl#PInt", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#PInt", "http://purl.org/olia/olia.owl#InterrogativePronoun");
		links.put("http://purl.org/olia/meno.owl#AT", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/meno.owl#AT", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#AT", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/meno.owl#VPresPart", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#VPresPart", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/meno.owl#VPresPart", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/meno.owl#PPer3", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#PPer3", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer3", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer3", "http://purl.org/olia/olia.owl#ThirdPersonPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer3", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#PPer1", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#PPer1", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#PPer1", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer1", "http://purl.org/olia/olia.owl#FirstPersonPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer1", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/meno.owl#Interjection", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/meno.owl#SubjunctiveVerb", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/meno.owl#SubjunctiveVerb", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#SubjunctiveVerb", "http://purl.org/olia/olia.owl#SubjunctiveVerb");
		links.put("http://purl.org/olia/meno.owl#xCS", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/meno.owl#xCS", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#CS", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/meno.owl#CS", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#InfinitiveMarker", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/meno.owl#NP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/meno.owl#NP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#RelativePronoun");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#RelativeParticle", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/meno.owl#xCU", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#Infinitive", "http://purl.org/olia/olia.owl#Infinitive");
		links.put("http://purl.org/olia/meno.owl#Infinitive", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/meno.owl#Infinitive", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#IndicativeVerb", "http://purl.org/olia/olia.owl#IndicativeVerb");
		links.put("http://purl.org/olia/meno.owl#IndicativeVerb", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/meno.owl#IndicativeVerb", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#xCC", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/meno.owl#xCC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/meno.owl#NC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/meno.owl#NC", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/meno.owl#Adposition", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/meno.owl#Adposition", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/meno.owl#PPer2", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#PPer2", "http://purl.org/olia/olia.owl#SecondPersonPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer2", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer2", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/meno.owl#PPer2", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#PossessiveDeterminer");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/meno.owl#DPos", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/meno.owl#_preterite_participle", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/meno.owl#_preterite_participle", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/meno.owl#_preterite_participle", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/meno.owl#NUO", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/meno.owl#NUO", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/meno.owl#NUO", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/meno.owl#NUC", "http://purl.org/olia/olia.owl#CardinalNumber");
		links.put("http://purl.org/olia/meno.owl#NUC", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/meno.owl#NUC", "http://purl.org/olia/olia.owl#Quantifier");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("CC", "http://purl.org/olia/meno.owl#CC");
		hasTag.put("NP", "http://purl.org/olia/meno.owl#NP");
		hasTag.put("DPos", "http://purl.org/olia/meno.owl#DPos");
		hasTag.put("PInt", "http://purl.org/olia/meno.owl#PInt");
		hasTag.put("NUC", "http://purl.org/olia/meno.owl#NUC");
		hasTag.put("xCU", "http://purl.org/olia/meno.owl#xCU");
		hasTag.put("NUO", "http://purl.org/olia/meno.owl#NUO");
		hasTag.put("PInd", "http://purl.org/olia/meno.owl#PInd");
		hasTag.put("VPresPart", "http://purl.org/olia/meno.owl#VPresPart");
		hasTag.put("AT", "http://purl.org/olia/meno.owl#AT");
		hasTag.put("CS", "http://purl.org/olia/meno.owl#CS");
		hasTag.put("NC", "http://purl.org/olia/meno.owl#NC");
		hasTag.put("xCS", "http://purl.org/olia/meno.owl#xCS");
		hasTag.put("PPer3", "http://purl.org/olia/meno.owl#PPer3");
		hasTag.put("PPer1", "http://purl.org/olia/meno.owl#PPer1");
		hasTag.put("xVB_fF", "http://purl.org/olia/meno.owl#xVB_fF");
		hasTag.put("xCC", "http://purl.org/olia/meno.owl#xCC");
		hasTag.put("PPer2", "http://purl.org/olia/meno.owl#PPer2");
		hasTag.put("DDet", "http://purl.org/olia/meno.owl#DDet");
		hasTag.put("u", "http://purl.org/olia/meno.owl#u");
	}
	

	}
	
