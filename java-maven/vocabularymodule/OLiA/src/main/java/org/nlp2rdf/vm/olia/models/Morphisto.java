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

public class Morphisto { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/morphisto.owl#IPNorm", "http://purl.org/olia/olia.owl#SentenceFinalPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPNorm", "http://purl.org/olia/olia.owl#MainPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPNorm", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/morphisto.owl#INTJ", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/morphisto.owl#VPPres", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/morphisto.owl#VPPres", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#VPPres", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Dat", "http://purl.org/olia/olia.owl#DativeCase");
		links.put("http://purl.org/olia/morphisto.owl#VPPast", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#VPPast", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#VPPast", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/morphisto.owl#masc", "http://purl.org/olia/olia.owl#Masculine");
		links.put("http://purl.org/olia/morphisto.owl#VInfZu", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#VInfZu", "http://purl.org/olia/olia.owl#Infinitive");
		links.put("http://purl.org/olia/morphisto.owl#VInfZu", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#Indef", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#Indef", "http://purl.org/olia/olia.owl#IndefiniteArticle");
		links.put("http://purl.org/olia/morphisto.owl#Indef", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/morphisto.owl#Indef", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#NE", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/morphisto.owl#NE", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/morphisto.owl#Gen", "http://purl.org/olia/olia.owl#GenitiveCase");
		links.put("http://purl.org/olia/morphisto.owl#IPKomma", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPKomma", "http://purl.org/olia/olia.owl#SecondaryPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPKomma", "http://purl.org/olia/olia.owl#SentenceMedialPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#PTKLAdj", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#Subj", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Subj", "http://purl.org/olia/olia.owl#SubjunctiveMood");
		links.put("http://purl.org/olia/morphisto.owl#strong_mixed", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#PTKLzu", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#Old", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#DemonstrativePronoun");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/morphisto.owl#DEM", "http://purl.org/olia/olia.owl#DemonstrativeDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#PTKLAnt", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#POSTP", "http://purl.org/olia/olia.owl#Postposition");
		links.put("http://purl.org/olia/morphisto.owl#POSTP", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/morphisto.owl#POSS", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#POSS", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#POSS", "http://purl.org/olia/olia.owl#PossessivePronoun");
		links.put("http://purl.org/olia/morphisto.owl#IP", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#DefiniteArticle");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#FusedPrepArt");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/morphisto.owl#PREP_ART", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#V1", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#V1", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#IPrechts", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPrechts", "http://purl.org/olia/olia.owl#ParentheticalPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPrechts", "http://purl.org/olia/olia.owl#RightParentheticalPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPrechts", "http://purl.org/olia/olia.owl#SecondaryPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#PTKL", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#Pl", "http://purl.org/olia/olia.owl#Plural");
		links.put("http://purl.org/olia/morphisto.owl#Adv", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#Adv", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#ART", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#ART", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/morphisto.owl#ART", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#ADJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#Comp", "http://purl.org/olia/olia.owl#Comparative");
		links.put("http://purl.org/olia/morphisto.owl#Sg", "http://purl.org/olia/olia.owl#Singular");
		links.put("http://purl.org/olia/morphisto.owl#KONJKon", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJKon", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJ", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/morphisto.owl#first", "http://purl.org/olia/olia.owl#First");
		links.put("http://purl.org/olia/morphisto.owl#VImp", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#VImp", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#VImp", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#VImp", "http://purl.org/olia/olia.owl#ImperativeVerb");
		links.put("http://purl.org/olia/morphisto.owl#WADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#WADV", "http://purl.org/olia/olia.owl#WHTypeAdverbs");
		links.put("http://purl.org/olia/morphisto.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/morphisto.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/morphisto.owl#Past", "http://purl.org/olia/olia.owl#Past");
		links.put("http://purl.org/olia/morphisto.owl#Past", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Past", "http://purl.org/olia/olia.owl#AbsoluteTense");
		links.put("http://purl.org/olia/morphisto.owl#Def", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#Def", "http://purl.org/olia/olia.owl#DefiniteArticle");
		links.put("http://purl.org/olia/morphisto.owl#Def", "http://purl.org/olia/olia.owl#Article");
		links.put("http://purl.org/olia/morphisto.owl#Def", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#Pred", "http://purl.org/olia/olia.owl#PredicativeAdjective");
		links.put("http://purl.org/olia/morphisto.owl#Pred", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#WPRO", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/morphisto.owl#WPRO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#WPRO", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#V2", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#V2", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#PREP", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/morphisto.owl#PREP", "http://purl.org/olia/olia.owl#Preposition");
		links.put("http://purl.org/olia/morphisto.owl#VPREProAdv", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#VPREProAdv", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#V", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#second", "http://purl.org/olia/olia.owl#Second");
		links.put("http://purl.org/olia/morphisto.owl#refl", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#refl", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/morphisto.owl#refl", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/morphisto.owl#refl", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#Pres", "http://purl.org/olia/olia.owl#AbsoluteTense");
		links.put("http://purl.org/olia/morphisto.owl#Pres", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Pres", "http://purl.org/olia/olia.owl#Present");
		links.put("http://purl.org/olia/morphisto.owl#Nom", "http://purl.org/olia/olia.owl#Nominative");
		links.put("http://purl.org/olia/morphisto.owl#NPROP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/morphisto.owl#NPROP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/morphisto.owl#prfl", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/morphisto.owl#prfl", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/morphisto.owl#prfl", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#prfl", "http://purl.org/olia/olia.owl#ReflexivePronoun");
		links.put("http://purl.org/olia/morphisto.owl#prfl", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#WHPronoun");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#WHDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#RelativePronoun");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#RelativeDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#AttributivePronoun");
		links.put("http://purl.org/olia/morphisto.owl#REL", "http://purl.org/olia/olia.owl#Determiner");
		links.put("http://purl.org/olia/morphisto.owl#weak_mixed", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#PROADV", "http://purl.org/olia/olia.owl#PronominalAdverb");
		links.put("http://purl.org/olia/morphisto.owl#PROADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#Konj", "http://purl.org/olia/olia.owl#SubjunctiveMood");
		links.put("http://purl.org/olia/morphisto.owl#Konj", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#strong", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#VPREAdv", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#VPREAdv", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#third", "http://purl.org/olia/olia.owl#Third");
		links.put("http://purl.org/olia/morphisto.owl#V3", "http://purl.org/olia/olia.owl#FiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#V3", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Sup", "http://purl.org/olia/olia.owl#Superlative");
		links.put("http://purl.org/olia/morphisto.owl#IPlinks", "http://purl.org/olia/olia.owl#ParentheticalPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPlinks", "http://purl.org/olia/olia.owl#SecondaryPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPlinks", "http://purl.org/olia/olia.owl#LeftParentheticalPunctuation");
		links.put("http://purl.org/olia/morphisto.owl#IPlinks", "http://purl.org/olia/olia.owl#Punctuation");
		links.put("http://purl.org/olia/morphisto.owl#fem", "http://purl.org/olia/olia.owl#Feminine");
		links.put("http://purl.org/olia/morphisto.owl#VInf", "http://purl.org/olia/olia.owl#Infinitive");
		links.put("http://purl.org/olia/morphisto.owl#VInf", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/morphisto.owl#VInf", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#ADV", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/morphisto.owl#INDEF", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#KONJInf", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJInf", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJVgl", "http://purl.org/olia/olia.owl#CoordinatingConjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJVgl", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/morphisto.owl#Pos", "http://purl.org/olia/olia.owl#Positive");
		links.put("http://purl.org/olia/morphisto.owl#neut", "http://purl.org/olia/olia.owl#Neuter");
		links.put("http://purl.org/olia/morphisto.owl#pers", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#pers", "http://purl.org/olia/olia.owl#PersonalPronoun");
		links.put("http://purl.org/olia/morphisto.owl#pers", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#pers", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/morphisto.owl#KONJSub", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/morphisto.owl#KONJSub", "http://purl.org/olia/olia.owl#SubordinatingConjunction");
		links.put("http://purl.org/olia/morphisto.owl#Acc", "http://purl.org/olia/olia.owl#Accusative");
		links.put("http://purl.org/olia/morphisto.owl#PTKLNeg", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#PPRO", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/morphisto.owl#PPRO", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/morphisto.owl#PPRO", "http://purl.org/olia/olia.owl#PersReflPronoun");
		links.put("http://purl.org/olia/morphisto.owl#ORD", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/morphisto.owl#ORD", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/morphisto.owl#ORD", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#ORD", "http://purl.org/olia/olia.owl#OrdinalNumber");
		links.put("http://purl.org/olia/morphisto.owl#Ind", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#Ind", "http://purl.org/olia/olia.owl#IndicativeMood");
		links.put("http://purl.org/olia/morphisto.owl#VPREV", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/morphisto.owl#VPREV", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/morphisto.owl#weak", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/morphisto.owl#Simp", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/morphisto.owl#VPREnull", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/morphisto.owl#VPREnull", "http://purl.org/olia/olia.owl#Unique");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("V", "http://purl.org/olia/morphisto.owl#V");
		hasTag.put("VPRE", "http://purl.org/olia/morphisto.owl#VPREnull");
	}
	

	}
	
