package eu.lod2.nlp2rdf.schema.str;

import java.util.List;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.Individual;

/**
 * Interface http://nlp2rdf.lod2.eu/schema/string/Document
 */

public interface IDocument extends Individual, eu.lod2.nlp2rdf.schema.str.IString {

	/**
	 * Domain property SourceUrl
	 * with uri http://nlp2rdf.lod2.eu/schema/string/sourceUrl
	 */

	public boolean existsSourceUrl();

	public boolean hasSourceUrl(eu.lod2.nlp2rdf.schema.IThing thingValue);

	public eu.lod2.nlp2rdf.schema.Thing getSourceUrl();

	public void setSourceUrl(eu.lod2.nlp2rdf.schema.IThing thingValue);

	public void removeSourceUrl();

	/**
	 * Domain property Topic
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/hasTopic
	 */

	public boolean existsTopic();

	public boolean hasTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue);

	public int countTopic();

	public Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> iterateTopic();

	public List<eu.lod2.nlp2rdf.schema.topic.Topic> listTopic();

	public void addTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue);

	public void addAllTopic(List<? extends eu.lod2.nlp2rdf.schema.topic.ITopic> topicList);

	public void removeTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue);

	public void removeAllTopic();

	/**
	 * Domain property DominatingTopic
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/dominatingTopic
	 */

	public boolean existsDominatingTopic();

	public boolean hasDominatingTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue);

	public eu.lod2.nlp2rdf.schema.topic.Topic getDominatingTopic();

	public void setDominatingTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue);

	public void removeDominatingTopic();

	/**
	 * Domain property SourceString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/sourceString
	 */

	public boolean existsSourceString();

	public boolean hasSourceString(java.lang.String stringValue);

	public java.lang.String getSourceString();

	public void setSourceString(java.lang.String stringValue);

	public void removeSourceString();

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