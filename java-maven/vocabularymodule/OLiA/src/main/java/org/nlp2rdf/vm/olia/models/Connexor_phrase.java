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

public class Connexor_phrase { 

	public static MultiValueMap links = MultiValueMap.decorate(new HashMap<String, ArrayList<String>>());
	
	static {

		links.put("http://purl.org/olia/connexor.owl#MAIN", "http://purl.org/olia/olia.owl#Clause");
		links.put("http://purl.org/olia/connexor.owl#ADVL", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#ADVL", "http://purl.org/olia/olia.owl#AdverbPhrase");
		links.put("http://purl.org/olia/connexor.owl#PREMOD", "http://purl.org/olia/olia.owl#AdjectivePhrase");
		links.put("http://purl.org/olia/connexor.owl#PREMOD", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#NH", "http://purl.org/olia/olia.owl#NounHeadedPhrase");
		links.put("http://purl.org/olia/connexor.owl#NH", "http://purl.org/olia/olia.owl#Phrase");
		links.put("http://purl.org/olia/connexor.owl#POSTMOD", "http://purl.org/olia/olia.owl#AdjectivePhrase");
		links.put("http://purl.org/olia/connexor.owl#POSTMOD", "http://purl.org/olia/olia.owl#Phrase");
	}
	

	}
	
