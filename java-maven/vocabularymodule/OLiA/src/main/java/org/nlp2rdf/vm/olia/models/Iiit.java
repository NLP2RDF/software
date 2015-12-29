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

public class Iiit { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/iiit.owl#JJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/iiit.owl#QF", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/iiit.owl#VFM", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VFM", "http://purl.org/olia/olia.owl#MainVerb");
		links.put("http://purl.org/olia/iiit.owl#CC", "http://purl.org/olia/olia.owl#Conjunction");
		links.put("http://purl.org/olia/iiit.owl#INTF", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/iiit.owl#INTF", "http://purl.org/olia/olia.owl#Intensifier");
		links.put("http://purl.org/olia/iiit.owl#NN", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/iiit.owl#NN", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/iiit.owl#VNNN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VNNN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VNNN", "http://purl.org/olia/olia.owl#Gerund");
		links.put("http://purl.org/olia/iiit.owl#VNN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VNN", "http://purl.org/olia/olia.owl#Gerund");
		links.put("http://purl.org/olia/iiit.owl#VNN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#NEG", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/iiit.owl#NEG", "http://purl.org/olia/olia.owl#NegativeParticle");
		links.put("http://purl.org/olia/iiit.owl#NEG", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/iiit.owl#VAUX", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VAUX", "http://purl.org/olia/olia.owl#AuxiliaryVerb");
		links.put("http://purl.org/olia/iiit.owl#VRBN", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/iiit.owl#VRBN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/iiit.owl#VRBN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VRBN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VRBN", "http://purl.org/olia/olia.owl#AdverbialParticiple");
		links.put("http://purl.org/olia/iiit.owl#NNP", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/iiit.owl#NNP", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/iiit.owl#RP", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/iiit.owl#RP", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/iiit.owl#NNPC", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/iiit.owl#NNPC", "http://purl.org/olia/olia.owl#ProperNoun");
		links.put("http://purl.org/olia/iiit.owl#NNPC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/iiit.owl#NNC", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/iiit.owl#NNC", "http://purl.org/olia/olia.owl#Composition");
		links.put("http://purl.org/olia/iiit.owl#NNC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/iiit.owl#QFNUM", "http://purl.org/olia/olia.owl#Quantifier");
		links.put("http://purl.org/olia/iiit.owl#QFNUM", "http://purl.org/olia/olia.owl#Numeral");
		links.put("http://purl.org/olia/iiit.owl#JVB", "http://purl.org/olia/olia.owl#LightVerb");
		links.put("http://purl.org/olia/iiit.owl#JVB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#PREP", "http://purl.org/olia/olia.owl#Postposition");
		links.put("http://purl.org/olia/iiit.owl#PREP", "http://purl.org/olia/olia.owl#Adposition");
		links.put("http://purl.org/olia/iiit.owl#NLOC", "http://purl.org/olia/olia.owl#Noun");
		links.put("http://purl.org/olia/iiit.owl#NLOC", "http://purl.org/olia/olia.owl#SpatiotemporalNoun");
		links.put("http://purl.org/olia/iiit.owl#NLOC", "http://purl.org/olia/olia.owl#CommonNoun");
		links.put("http://purl.org/olia/iiit.owl#RB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/iiit.owl#UH", "http://purl.org/olia/olia.owl#Interjection");
		links.put("http://purl.org/olia/iiit.owl#VRB", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VRB", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/iiit.owl#VRB", "http://purl.org/olia/olia.owl#Adverb");
		links.put("http://purl.org/olia/iiit.owl#VRB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VRB", "http://purl.org/olia/olia.owl#AdverbialParticiple");
		links.put("http://purl.org/olia/iiit.owl#VJJ", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#VJJ", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/iiit.owl#VJJ", "http://purl.org/olia/olia.owl#ParticipleAdjective");
		links.put("http://purl.org/olia/iiit.owl#VJJ", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VJJ", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/iiit.owl#VJJN", "http://purl.org/olia/olia.owl#Adjective");
		links.put("http://purl.org/olia/iiit.owl#VJJN", "http://purl.org/olia/olia.owl#NonFiniteVerb");
		links.put("http://purl.org/olia/iiit.owl#VJJN", "http://purl.org/olia/olia.owl#ParticipleAdjective");
		links.put("http://purl.org/olia/iiit.owl#VJJN", "http://purl.org/olia/olia.owl#Participle");
		links.put("http://purl.org/olia/iiit.owl#VJJN", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#PRP", "http://purl.org/olia/olia.owl#Pronoun");
		links.put("http://purl.org/olia/iiit.owl#PRP", "http://purl.org/olia/olia.owl#PronounOrDeterminer");
		links.put("http://purl.org/olia/iiit.owl#QW", "http://purl.org/olia/olia.owl#Particle");
		links.put("http://purl.org/olia/iiit.owl#QW", "http://purl.org/olia/olia.owl#InterrogativeParticle");
		links.put("http://purl.org/olia/iiit.owl#QW", "http://purl.org/olia/olia.owl#Unique");
		links.put("http://purl.org/olia/iiit.owl#RBVB", "http://purl.org/olia/olia.owl#LightVerb");
		links.put("http://purl.org/olia/iiit.owl#RBVB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#SYM", "http://purl.org/olia/olia.owl#Symbol");
		links.put("http://purl.org/olia/iiit.owl#SYM", "http://purl.org/olia/olia.owl#Residual");
		links.put("http://purl.org/olia/iiit.owl#NVB", "http://purl.org/olia/olia.owl#Verb");
		links.put("http://purl.org/olia/iiit.owl#NVB", "http://purl.org/olia/olia.owl#LightVerb");
	}
	
	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("CC", "http://purl.org/olia/iiit.owl#CC");
		hasTag.put("INTF", "http://purl.org/olia/iiit.owl#INTF");
		hasTag.put("JJ", "http://purl.org/olia/iiit.owl#JJ");
		hasTag.put("JVB", "http://purl.org/olia/iiit.owl#JVB");
		hasTag.put("NEG", "http://purl.org/olia/iiit.owl#NEG");
		hasTag.put("NLOC", "http://purl.org/olia/iiit.owl#NLOC");
		hasTag.put("NN", "http://purl.org/olia/iiit.owl#NN");
		hasTag.put("NNC", "http://purl.org/olia/iiit.owl#NNC");
		hasTag.put("NNP", "http://purl.org/olia/iiit.owl#NNP");
		hasTag.put("NNPC", "http://purl.org/olia/iiit.owl#NNPC");
		hasTag.put("NVB", "http://purl.org/olia/iiit.owl#NVB");
		hasTag.put("PREP", "http://purl.org/olia/iiit.owl#PREP");
		hasTag.put("PRP", "http://purl.org/olia/iiit.owl#PRP");
		hasTag.put("QF", "http://purl.org/olia/iiit.owl#QF");
		hasTag.put("QFNUM", "http://purl.org/olia/iiit.owl#QFNUM");
		hasTag.put("QW", "http://purl.org/olia/iiit.owl#QW");
		hasTag.put("RB", "http://purl.org/olia/iiit.owl#RB");
		hasTag.put("RBVB", "http://purl.org/olia/iiit.owl#RBVB");
		hasTag.put("RB", "http://purl.org/olia/iiit.owl#RP");
		hasTag.put("SYM", "http://purl.org/olia/iiit.owl#SYM");
		hasTag.put("UH", "http://purl.org/olia/iiit.owl#UH");
		hasTag.put("VAUX", "http://purl.org/olia/iiit.owl#VAUX");
		hasTag.put("VFM", "http://purl.org/olia/iiit.owl#VFM");
		hasTag.put("VJJ", "http://purl.org/olia/iiit.owl#VJJ");
		hasTag.put("VJJN", "http://purl.org/olia/iiit.owl#VJJN");
		hasTag.put("VNN", "http://purl.org/olia/iiit.owl#VNN");
		hasTag.put("VNNN", "http://purl.org/olia/iiit.owl#VNNN");
		hasTag.put("VRB", "http://purl.org/olia/iiit.owl#VRB");
		hasTag.put("VRBN", "http://purl.org/olia/iiit.owl#VRBN");
	}
	

	}
	
