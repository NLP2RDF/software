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

public class Tcodex { 

	public static MultiValueMap hasTag = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		hasTag.put("ADVCOM", "http://purl.org/olia/tcodex.owl#ADVCOM");
		hasTag.put("ADVCOND", "http://purl.org/olia/tcodex.owl#ADVCOND");
		hasTag.put("ADVINSTR", "http://purl.org/olia/tcodex.owl#ADVINSTR");
		hasTag.put("ADVSENT", "http://purl.org/olia/tcodex.owl#ADVSENT");
		hasTag.put("AR", "http://purl.org/olia/tcodex.owl#AR");
		hasTag.put("FOPR", "http://purl.org/olia/tcodex.owl#FOPR");
		hasTag.put("GV", "http://purl.org/olia/tcodex.owl#GV");
		hasTag.put("HT", "http://purl.org/olia/tcodex.owl#HT");
		hasTag.put("LD", "http://purl.org/olia/tcodex.owl#LD");
		hasTag.put("PRD", "http://purl.org/olia/tcodex.owl#PRD");
		hasTag.put("Situationswechsel", "http://purl.org/olia/tcodex.owl#Situationswechsel");
		hasTag.put("ACC", "http://purl.org/olia/tcodex.owl#acc");
		hasTag.put("A", "http://purl.org/olia/tcodex.owl#adj");
		hasTag.put("adjp", "http://purl.org/olia/tcodex.owl#adjp");
		hasTag.put("ADV", "http://purl.org/olia/tcodex.owl#adv");
		hasTag.put("adv:caus", "http://purl.org/olia/tcodex.owl#adv_caus");
		hasTag.put("adv:dir", "http://purl.org/olia/tcodex.owl#adv_dir");
		hasTag.put("adv:loc", "http://purl.org/olia/tcodex.owl#adv_loc");
		hasTag.put("adv:mod", "http://purl.org/olia/tcodex.owl#adv_mod");
		hasTag.put("adv:temp", "http://purl.org/olia/tcodex.owl#adv_temp");
		hasTag.put("adv", "http://purl.org/olia/tcodex.owl#adverbial");
		hasTag.put("ADVP", "http://purl.org/olia/tcodex.owl#advp");
		hasTag.put("APPOS", "http://purl.org/olia/tcodex.owl#appos");
		hasTag.put("ATTR", "http://purl.org/olia/tcodex.owl#attr");
		hasTag.put("attr/do", "http://purl.org/olia/tcodex.owl#attr_do");
		hasTag.put("ATTRPRDNOM", "http://purl.org/olia/tcodex.owl#attr_predn");
		hasTag.put("attr/su", "http://purl.org/olia/tcodex.owl#attr_su");
		hasTag.put("VAUX", "http://purl.org/olia/tcodex.owl#aux");
		hasTag.put("ADVCAUS", "http://purl.org/olia/tcodex.owl#caus_adverbial");
		hasTag.put("CAUSAL", "http://purl.org/olia/tcodex.owl#causal");
		hasTag.put("CF", "http://purl.org/olia/tcodex.owl#cf");
		hasTag.put("COMM", "http://purl.org/olia/tcodex.owl#comm");
		hasTag.put("CONJ", "http://purl.org/olia/tcodex.owl#conj");
		hasTag.put("VCOP", "http://purl.org/olia/tcodex.owl#cop");
		hasTag.put("CP", "http://purl.org/olia/tcodex.owl#cp");
		hasTag.put("DEF", "http://purl.org/olia/tcodex.owl#def");
		hasTag.put("DIR", "http://purl.org/olia/tcodex.owl#dir");
		hasTag.put("DO", "http://purl.org/olia/tcodex.owl#do");
		hasTag.put("FRAME", "http://purl.org/olia/tcodex.owl#frame");
		hasTag.put("GERUND", "http://purl.org/olia/tcodex.owl#gerund");
		hasTag.put("GIV", "http://purl.org/olia/tcodex.owl#giv");
		hasTag.put("INDEF", "http://purl.org/olia/tcodex.owl#indef");
		hasTag.put("INF", "http://purl.org/olia/tcodex.owl#inf");
		hasTag.put("INFCLAUSE", "http://purl.org/olia/tcodex.owl#infinitival_clause");
		hasTag.put("init", "http://purl.org/olia/tcodex.owl#init");
		hasTag.put("INTERJ", "http://purl.org/olia/tcodex.owl#interj");
		hasTag.put("IO", "http://purl.org/olia/tcodex.owl#io");
		hasTag.put("ADVLOC", "http://purl.org/olia/tcodex.owl#loc_adverbial");
		hasTag.put("MAINDECL", "http://purl.org/olia/tcodex.owl#main_declarative");
		hasTag.put("MAINIMPER", "http://purl.org/olia/tcodex.owl#main_imperative");
		hasTag.put("MAININTERR", "http://purl.org/olia/tcodex.owl#main_interrogative");
		hasTag.put("MAINOPTAT", "http://purl.org/olia/tcodex.owl#main_optative");
		hasTag.put("MAINREQUEST", "http://purl.org/olia/tcodex.owl#main_request");
		hasTag.put("VMOD", "http://purl.org/olia/tcodex.owl#modal");
		hasTag.put("ADVMOD", "http://purl.org/olia/tcodex.owl#modal_adverbial");
		hasTag.put("N", "http://purl.org/olia/tcodex.owl#n");
		hasTag.put("NEG", "http://purl.org/olia/tcodex.owl#neg");
		hasTag.put("NEW", "http://purl.org/olia/tcodex.owl#new");
		hasTag.put("NIF", "http://purl.org/olia/tcodex.owl#nif");
		hasTag.put("NIFCF", "http://purl.org/olia/tcodex.owl#nif-cf");
		hasTag.put("noninit", "http://purl.org/olia/tcodex.owl#noninit");
		hasTag.put("NP", "http://purl.org/olia/tcodex.owl#np");
		hasTag.put("NUM", "http://purl.org/olia/tcodex.owl#num");
		hasTag.put("PARTCONSTR", "http://purl.org/olia/tcodex.owl#participial_construction");
		hasTag.put("PTC", "http://purl.org/olia/tcodex.owl#particle");
		hasTag.put("PARTPF", "http://purl.org/olia/tcodex.owl#partpf");
		hasTag.put("PARTPR", "http://purl.org/olia/tcodex.owl#partpr");
		hasTag.put("PP", "http://purl.org/olia/tcodex.owl#pp");
		hasTag.put("PRONDEM", "http://purl.org/olia/tcodex.owl#prdem");
		hasTag.put("PO", "http://purl.org/olia/tcodex.owl#pred");
		hasTag.put("PRDNOM", "http://purl.org/olia/tcodex.owl#predn");
		hasTag.put("AP", "http://purl.org/olia/tcodex.owl#prep");
		hasTag.put("PRONINTERR", "http://purl.org/olia/tcodex.owl#prindef");
		hasTag.put("printerr", "http://purl.org/olia/tcodex.owl#printerr");
		hasTag.put("PRONPRS", "http://purl.org/olia/tcodex.owl#prpers");
		hasTag.put("PRONPOS", "http://purl.org/olia/tcodex.owl#prposs");
		hasTag.put("PRONRFL", "http://purl.org/olia/tcodex.owl#prref");
		hasTag.put("PRONREL", "http://purl.org/olia/tcodex.owl#prrel");
		hasTag.put("PRONREZ", "http://purl.org/olia/tcodex.owl#prrez");
		hasTag.put("AB", "http://purl.org/olia/tcodex.owl#ref");
		hasTag.put("REL", "http://purl.org/olia/tcodex.owl#relative");
		hasTag.put("relative:obj", "http://purl.org/olia/tcodex.owl#relative_obj");
		hasTag.put("relative:su", "http://purl.org/olia/tcodex.owl#relative_su");
		hasTag.put("SUBJ", "http://purl.org/olia/tcodex.owl#su");
		hasTag.put("SUB", "http://purl.org/olia/tcodex.owl#subordinate");
		hasTag.put("subordinate:attributive", "http://purl.org/olia/tcodex.owl#subordinate_attributive");
		hasTag.put("SUBCOMPAR", "http://purl.org/olia/tcodex.owl#subordinate_comparative");
		hasTag.put("SUBCONC", "http://purl.org/olia/tcodex.owl#subordinate_concessive");
		hasTag.put("SUBCOND", "http://purl.org/olia/tcodex.owl#subordinate_conditional");
		hasTag.put("SUBCONS", "http://purl.org/olia/tcodex.owl#subordinate_consecutive");
		hasTag.put("SUBFINAL", "http://purl.org/olia/tcodex.owl#subordinate_final");
		hasTag.put("subordinate:interrogative", "http://purl.org/olia/tcodex.owl#subordinate_interrogative");
		hasTag.put("SUBLOCAL", "http://purl.org/olia/tcodex.owl#subordinate_local");
		hasTag.put("SUBMODAL", "http://purl.org/olia/tcodex.owl#subordinate_modal");
		hasTag.put("subordinate:object", "http://purl.org/olia/tcodex.owl#subordinate_object");
		hasTag.put("subordinate:su", "http://purl.org/olia/tcodex.owl#subordinate_subject");
		hasTag.put("SUBTEMP", "http://purl.org/olia/tcodex.owl#subordinate_temporal");
		hasTag.put("ADVTEMP", "http://purl.org/olia/tcodex.owl#temporal_adverbial");
		hasTag.put("TOP", "http://purl.org/olia/tcodex.owl#top");
		hasTag.put("V", "http://purl.org/olia/tcodex.owl#v");
		hasTag.put("VFIN", "http://purl.org/olia/tcodex.owl#vfin");
		hasTag.put("VN", "http://purl.org/olia/tcodex.owl#vn");
		hasTag.put("VOC", "http://purl.org/olia/tcodex.owl#voc");
		hasTag.put("VP", "http://purl.org/olia/tcodex.owl#vp");
		hasTag.put("WH", "http://purl.org/olia/tcodex.owl#wh");
	}
	

	}
	
