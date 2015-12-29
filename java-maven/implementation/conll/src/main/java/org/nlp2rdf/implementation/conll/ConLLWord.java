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

package org.nlp2rdf.implementation.conll;

import com.hp.hpl.jena.ontology.Individual;

public class ConLLWord {

	private String wordId = "";
	private String wordString = "";
	private int start = 0;
	private int end = 0;
	private String pos = "";
	private String posFine = "";
	private String entity = "";
	private String lemma = "";
	private String morphs = "";
	private int phraseHeadId = 0;
	private String phraseType = "";
	private Individual resource = null;
	
	
	public ConLLWord() {
		
	}

	public Individual getResource() {
		return resource;
	}

	public void setResource(Individual resource) {
		this.resource = resource;
	}

	public String getWordId() {
		return wordId;
	}
	
	public void setWordId(String wordId) {
		this.wordId = wordId;
	}

	public String getPosFine() {
		return posFine;
	}

	public void setPosFine(String posFine) {
		this.posFine = posFine;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getMorphs() {
		return morphs;
	}

	public void setMorphs(String morphs) {
		this.morphs = morphs;
	}

	public int getPhraseHeadId() {
		return phraseHeadId;
	}

	public void setPhraseHeadId(int phraseHeadId) {
		this.phraseHeadId = phraseHeadId;
	}

	public String getPhraseType() {
		return phraseType;
	}

	public void setPhraseType(String phraseType) {
		this.phraseType = phraseType;
	}

	public String getWordString() {
		return wordString;
	}
	
	public void setWordString(String wordString) {
		this.wordString = wordString;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getPos() {
		return pos;
	}
	
	public void setPos(String pos) {
		this.pos = pos;
	}
	
	public String getEntity() {
		return entity;
	}
	
	public void setEntity(String entity) {
		this.entity = entity;
	}
}
