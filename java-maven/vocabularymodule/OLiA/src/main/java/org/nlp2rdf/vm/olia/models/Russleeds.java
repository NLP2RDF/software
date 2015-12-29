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

public class Russleeds { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/russleeds.owl#INIT", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/russleeds.owl#PUNCT", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/russleeds.owl#PUNCT", "http://purl.org/olia/olia.owl#SecondaryPunctuation");
		links.put("http://purl.org/olia/russleeds.owl#V", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/russleeds.owl#INTJ", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/russleeds.owl#CONJ", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/russleeds.owl#PR", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/russleeds.owl#PR", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/russleeds.owl#S", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/russleeds.owl#PART", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/russleeds.owl#A", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/russleeds.owl#ADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/russleeds.owl#A-NUM", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/russleeds.owl#A-NUM", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/russleeds.owl#A-NUM", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/russleeds.owl#NUM", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/russleeds.owl#NUM", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/russleeds.owl#A-PRO", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/russleeds.owl#A-PRO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/russleeds.owl#SENT", "http://purl.org/olia/olia.owl#SentenceFinalPunctuation");
		links.put("http://purl.org/olia/russleeds.owl#SENT", "http://purl.org/olia/olia.owl#MainPunctuation");
		links.put("http://purl.org/olia/russleeds.owl#SENT", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/russleeds.owl#S-PRO", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/russleeds.owl#S-PRO", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/russleeds.owl#S-PRO", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/russleeds.owl#S-PRO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/russleeds.owl#PRAEDIC-PRO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/russleeds.owl#PRAEDIC-PRO", "http://purl.org/olia/olia.owl#Pronoun");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("ADV", "http://purl.org/olia/russleeds.owl#ADV");
		hasTag.put("PART", "http://purl.org/olia/russleeds.owl#PART");
		hasTag.put("INIT", "http://purl.org/olia/russleeds.owl#INIT");
		hasTag.put("A-PRO", "http://purl.org/olia/russleeds.owl#A-PRO");
		hasTag.put("INTJ", "http://purl.org/olia/russleeds.owl#INTJ");
		hasTag.put("PARENTH", "http://purl.org/olia/russleeds.owl#PARENTH");
		hasTag.put("A", "http://purl.org/olia/russleeds.owl#A");
		hasTag.put("ADV-PRO", "http://purl.org/olia/russleeds.owl#ADV-PRO");
		hasTag.put("V", "http://purl.org/olia/russleeds.owl#V");
		hasTag.put("PRAEDIC-PRO", "http://purl.org/olia/russleeds.owl#PRAEDIC-PRO");
		hasTag.put("PRAEDIC", "http://purl.org/olia/russleeds.owl#PRAEDIC");
		hasTag.put("SENT", "http://purl.org/olia/russleeds.owl#SENT");
		hasTag.put("PRAEDIC-PRO", "http://purl.org/olia/russleeds.owl#PR");
		hasTag.put("CONJ", "http://purl.org/olia/russleeds.owl#CONJ");
		hasTag.put("PUNCT", "http://purl.org/olia/russleeds.owl#PUNCT");
		hasTag.put("S", "http://purl.org/olia/russleeds.owl#S");
		hasTag.put("NUM", "http://purl.org/olia/russleeds.owl#NUM");
		hasTag.put("S-PRO", "http://purl.org/olia/russleeds.owl#S-PRO");
		hasTag.put("A-NUM", "http://purl.org/olia/russleeds.owl#A-NUM");
	}
	

	}
	
