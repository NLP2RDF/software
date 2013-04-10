package eu.lod2.nlp2rdf.schema.tools;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;

/**
 * A vocabulary for all properties, classes etc. used
 * in the ontology. This is based on the jena schemagen
 * output
 *
 * Note, that the current implementation assumes unique names
 * for class names.
 */
public class Vocabulary {
	public static final List<java.lang.String> NAMESPACES;

	public static final Resource NSP3;
	public static final Resource NSSTR;
	public static final Resource NSP1;
	public static final Resource NSERROR;
	public static final Resource NSSSO;
	public static final Resource NSP2;
	public static final Resource NSP5;
	public static final Resource NSP4;
	public static final Resource NSTOPIC;
	public static final Resource NSSCMS;
	public static final Resource NSXML;

	public static final OntClass Thing;
	public static final OntClass StopWord;
	public static final OntClass Error;
	public static final OntClass Word;
	public static final OntClass Phrase;
	public static final OntClass OffsetBasedString;
	public static final OntClass Document;
	public static final OntClass Sentence;
	public static final OntClass String;
	public static final OntClass Topic;
	public static final OntClass ContextHashBasedString;

	public static final ObjectProperty parent;
	public static final ObjectProperty word;
	public static final DatatypeProperty rightContext;
	public static final ObjectProperty nextSentenceTrans;
	public static final ObjectProperty superString;
	public static final ObjectProperty topic;
	public static final ObjectProperty previousSentence;
	public static final DatatypeProperty leftContext;
	public static final ObjectProperty nextWord;
	public static final ObjectProperty subString;
	public static final DatatypeProperty endIndex;
	public static final DatatypeProperty fatal;
	public static final DatatypeProperty source;
	public static final DatatypeProperty stem;
	public static final DatatypeProperty anchorOf;
	public static final ObjectProperty nextWordTrans;
	public static final ObjectProperty previousWordTrans;
	public static final ObjectProperty firstWord;
	public static final ObjectProperty subStringTrans;
	public static final ObjectProperty superStringTrans;
	public static final DatatypeProperty posTag;
	public static final ObjectProperty nextSentence;
	public static final DatatypeProperty lemma;
	public static final ObjectProperty child;
	public static final ObjectProperty occursIn;
	public static final DatatypeProperty characterticLemma;
	public static final ObjectProperty oliaLink;
	public static final DatatypeProperty beginIndex;
	public static final ObjectProperty lastWord;
	public static final ObjectProperty previousSentenceTrans;
	public static final ObjectProperty sentence;
	public static final DatatypeProperty message;
	public static final ObjectProperty means;
	public static final DatatypeProperty sourceString;
	public static final ObjectProperty previousWord;
	public static final ObjectProperty dominatingTopic;
	public static final ObjectProperty sourceUrl;

	static {
		NAMESPACES = new ArrayList<java.lang.String>();
		NAMESPACES.add("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/string/string-v1.0.ttl#");
		NAMESPACES.add("http://nlp2rdf.lod2.eu/schema/string/");
		NAMESPACES.add("http://nlp2rdf.lod2.eu/schema/string/#");
		NAMESPACES.add("http://nlp2rdf.lod2.eu/schema/error/");
		NAMESPACES.add("http://nlp2rdf.lod2.eu/schema/sso/");
		NAMESPACES.add("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/sso/sso-v1.0.ttl#");
		NAMESPACES.add("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/error/error-v0.1.ttl#");
		NAMESPACES.add("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/topic/topic-v0.8.ttl#");
		NAMESPACES.add("http://nlp2rdf.lod2.eu/schema/topic/");
		NAMESPACES.add("http://ns.aksw.org/scms/");
		NAMESPACES.add("http://www.w3.org/XML/1998/namespace");

		OntModel resourceModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);

		NSP3 = resourceModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/string/string-v1.0.ttl#");
		NSSTR = resourceModel.createResource("http://nlp2rdf.lod2.eu/schema/string/");
		NSP1 = resourceModel.createResource("http://nlp2rdf.lod2.eu/schema/string/#");
		NSERROR = resourceModel.createResource("http://nlp2rdf.lod2.eu/schema/error/");
		NSSSO = resourceModel.createResource("http://nlp2rdf.lod2.eu/schema/sso/");
		NSP2 = resourceModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/sso/sso-v1.0.ttl#");
		NSP5 = resourceModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/error/error-v0.1.ttl#");
		NSP4 = resourceModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/topic/topic-v0.8.ttl#");
		NSTOPIC = resourceModel.createResource("http://nlp2rdf.lod2.eu/schema/topic/");
		NSSCMS = resourceModel.createResource("http://ns.aksw.org/scms/");
		NSXML = resourceModel.createResource("http://www.w3.org/XML/1998/namespace");

		Thing = resourceModel.createClass("http://www.w3.org/2002/07/owl#Thing");
		StopWord = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/sso/StopWord");
		Error = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/error/Error");
		Word = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/sso/Word");
		Phrase = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/sso/Phrase");
		OffsetBasedString = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/string/OffsetBasedString");
		Document = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/string/Document");
		Sentence = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/sso/Sentence");
		String = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/string/String");
		Topic = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/topic/Topic");
		ContextHashBasedString = resourceModel.createClass("http://nlp2rdf.lod2.eu/schema/string/ContextHashBasedString");

		parent = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/parent");
		word = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/word");
		rightContext = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/rightContext");
		nextSentenceTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/nextSentenceTrans");
		superString = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/string/superString");
		topic = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/topic/hasTopic");
		previousSentence = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/previousSentence");
		leftContext = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/leftContext");
		nextWord = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/nextWord");
		subString = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/string/subString");
		endIndex = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/endIndex");
		fatal = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/error/fatal");
		source = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/error/source");
		stem = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/sso/stem");
		anchorOf = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/anchorOf");
		nextWordTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/nextWordTrans");
		previousWordTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/previousWordTrans");
		firstWord = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/firstWord");
		subStringTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/string/subStringTrans");
		superStringTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/string/superStringTrans");
		posTag = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/sso/posTag");
		nextSentence = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/nextSentence");
		lemma = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/sso/lemma");
		child = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/child");
		occursIn = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/topic/occursIn");
		characterticLemma = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/topic/characteristicLemma");
		oliaLink = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/oliaLink");
		beginIndex = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/beginIndex");
		lastWord = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/lastWord");
		previousSentenceTrans = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/previousSentenceTrans");
		sentence = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/sentence");
		message = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/error/hasMessage");
		means = resourceModel.createObjectProperty("http://ns.aksw.org/scms/means");
		sourceString = resourceModel.createDatatypeProperty("http://nlp2rdf.lod2.eu/schema/string/sourceString");
		previousWord = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/sso/previousWord");
		dominatingTopic = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/topic/dominatingTopic");
		sourceUrl = resourceModel.createObjectProperty("http://nlp2rdf.lod2.eu/schema/string/sourceUrl");
	}
}
