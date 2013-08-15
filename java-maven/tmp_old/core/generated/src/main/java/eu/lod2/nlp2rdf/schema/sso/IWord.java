package eu.lod2.nlp2rdf.schema.sso;

import java.util.List;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Interface http://nlp2rdf.lod2.eu/schema/sso/Word
 */

public interface IWord extends Individual, eu.lod2.nlp2rdf.schema.str.IString {

	/**
	 * Domain property Sentence
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/sentence
	 */

	public boolean existsSentence();

	public boolean hasSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue);

	public eu.lod2.nlp2rdf.schema.sso.Sentence getSentence();

	public void setSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue);

	public void removeSentence();

	/**
	 * Domain property OliaLink
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/oliaLink
	 */

	public boolean existsOliaLink();

	public boolean hasOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue);

	public int countOliaLink();

	public Iterator<eu.lod2.nlp2rdf.schema.Thing> iterateOliaLink();

	public List<eu.lod2.nlp2rdf.schema.Thing> listOliaLink();

	public void addOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue);

	public void addAllOliaLink(List<? extends eu.lod2.nlp2rdf.schema.IThing> thingList);

	public void removeOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue);

	public void removeAllOliaLink();

	/**
	 * Domain property PreviousWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousWord
	 */

	public boolean existsPreviousWord();

	public boolean hasPreviousWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public eu.lod2.nlp2rdf.schema.sso.Word getPreviousWord();

	public void setPreviousWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void removePreviousWord();

	/**
	 * Domain property NextWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextWord
	 */

	public boolean existsNextWord();

	public boolean hasNextWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public eu.lod2.nlp2rdf.schema.sso.Word getNextWord();

	public void setNextWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void removeNextWord();

	/**
	 * Domain property NextSentenceTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextSentenceTrans
	 */

	public boolean existsNextSentenceTrans();

	public boolean hasNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public int countNextSentenceTrans();

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iterateNextSentenceTrans();

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listNextSentenceTrans();

	public void addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void addAllNextSentenceTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList);

	public void removeNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void removeAllNextSentenceTrans();

	/**
	 * Domain property PreviousWordTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousWordTrans
	 */

	public boolean existsPreviousWordTrans();

	public boolean hasPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public int countPreviousWordTrans();

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iteratePreviousWordTrans();

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listPreviousWordTrans();

	public void addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void addAllPreviousWordTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList);

	public void removePreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void removeAllPreviousWordTrans();

	/**
	 * Domain property NextWordTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextWordTrans
	 */

	public boolean existsNextWordTrans();

	public boolean hasNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public int countNextWordTrans();

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iterateNextWordTrans();

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listNextWordTrans();

	public void addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void addAllNextWordTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList);

	public void removeNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue);

	public void removeAllNextWordTrans();

	/**
	 * Domain property PosTag
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/posTag
	 */

	public boolean existsPosTag();

	public boolean hasPosTag(java.lang.String stringValue);

	public int countPosTag();

	public Iterator<java.lang.String> iteratePosTag();

	public List<java.lang.String> listPosTag();

	public void addPosTag(java.lang.String stringValue);

	public void addAllPosTag(List<java.lang.String> stringList);

	public void removePosTag(java.lang.String stringValue);

	public void removeAllPosTag();

	/**
	 * Domain property Lemma
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/lemma
	 */

	public boolean existsLemma();

	public boolean hasLemma(java.lang.String stringValue);

	public int countLemma();

	public Iterator<java.lang.String> iterateLemma();

	public List<java.lang.String> listLemma();

	public void addLemma(java.lang.String stringValue);

	public void addAllLemma(List<java.lang.String> stringList);

	public void removeLemma(java.lang.String stringValue);

	public void removeAllLemma();

	/**
	 * Domain property Stem
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/stem
	 */

	public boolean existsStem();

	public boolean hasStem(java.lang.String stringValue);

	public int countStem();

	public Iterator<java.lang.String> iterateStem();

	public List<java.lang.String> listStem();

	public void addStem(java.lang.String stringValue);

	public void addAllStem(List<java.lang.String> stringList);

	public void removeStem(java.lang.String stringValue);

	public void removeAllStem();

	/**
	 * Domain property SuperString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/superString
	 */

	public boolean existsSuperString();

	public boolean hasSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public int countSuperString();

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSuperString();

	public List<eu.lod2.nlp2rdf.schema.str.String> listSuperString();

	public void addSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void addAllSuperString(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList);

	public void removeSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void removeAllSuperString();

	/**
	 * Domain property SubString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/subString
	 */

	public boolean existsSubString();

	public boolean hasSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public int countSubString();

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSubString();

	public List<eu.lod2.nlp2rdf.schema.str.String> listSubString();

	public void addSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void addAllSubString(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList);

	public void removeSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void removeAllSubString();

	/**
	 * Domain property SuperStringTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/string/superStringTrans
	 */

	public boolean existsSuperStringTrans();

	public boolean hasSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public int countSuperStringTrans();

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSuperStringTrans();

	public List<eu.lod2.nlp2rdf.schema.str.String> listSuperStringTrans();

	public void addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void addAllSuperStringTrans(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList);

	public void removeSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void removeAllSuperStringTrans();

	/**
	 * Domain property SubStringTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/string/subStringTrans
	 */

	public boolean existsSubStringTrans();

	public boolean hasSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public int countSubStringTrans();

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSubStringTrans();

	public List<eu.lod2.nlp2rdf.schema.str.String> listSubStringTrans();

	public void addSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void addAllSubStringTrans(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList);

	public void removeSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue);

	public void removeAllSubStringTrans();

	/**
	 * Domain property AnchorOf
	 * with uri http://nlp2rdf.lod2.eu/schema/string/anchorOf
	 */

	public boolean existsAnchorOf();

	public boolean hasAnchorOf(java.lang.String stringValue);

	public java.lang.String getAnchorOf();

	public void setAnchorOf(java.lang.String stringValue);

	public void removeAnchorOf();

	/**
	 * Domain property EndIndex
	 * with uri http://nlp2rdf.lod2.eu/schema/string/endIndex
	 */

	public boolean existsEndIndex();

	public boolean hasEndIndex(java.lang.String stringValue);

	public int countEndIndex();

	public Iterator<java.lang.String> iterateEndIndex();

	public List<java.lang.String> listEndIndex();

	public void addEndIndex(java.lang.String stringValue);

	public void addAllEndIndex(List<java.lang.String> stringList);

	public void removeEndIndex(java.lang.String stringValue);

	public void removeAllEndIndex();

	/**
	 * Domain property BeginIndex
	 * with uri http://nlp2rdf.lod2.eu/schema/string/beginIndex
	 */

	public boolean existsBeginIndex();

	public boolean hasBeginIndex(java.lang.String stringValue);

	public int countBeginIndex();

	public Iterator<java.lang.String> iterateBeginIndex();

	public List<java.lang.String> listBeginIndex();

	public void addBeginIndex(java.lang.String stringValue);

	public void addAllBeginIndex(List<java.lang.String> stringList);

	public void removeBeginIndex(java.lang.String stringValue);

	public void removeAllBeginIndex();

	/**
	 * Domain property RightContext
	 * with uri http://nlp2rdf.lod2.eu/schema/string/rightContext
	 */

	public boolean existsRightContext();

	public boolean hasRightContext(java.lang.String stringValue);

	public int countRightContext();

	public Iterator<java.lang.String> iterateRightContext();

	public List<java.lang.String> listRightContext();

	public void addRightContext(java.lang.String stringValue);

	public void addAllRightContext(List<java.lang.String> stringList);

	public void removeRightContext(java.lang.String stringValue);

	public void removeAllRightContext();

	/**
	 * Domain property LeftContext
	 * with uri http://nlp2rdf.lod2.eu/schema/string/leftContext
	 */

	public boolean existsLeftContext();

	public boolean hasLeftContext(java.lang.String stringValue);

	public int countLeftContext();

	public Iterator<java.lang.String> iterateLeftContext();

	public List<java.lang.String> listLeftContext();

	public void addLeftContext(java.lang.String stringValue);

	public void addAllLeftContext(List<java.lang.String> stringList);

	public void removeLeftContext(java.lang.String stringValue);

	public void removeAllLeftContext();

}