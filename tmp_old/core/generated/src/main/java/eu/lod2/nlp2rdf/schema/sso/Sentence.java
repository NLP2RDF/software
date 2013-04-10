package eu.lod2.nlp2rdf.schema.sso;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import nl.tudelft.tbm.eeni.owl2java.model.jenautils.NullFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.enhanced.BuiltinPersonalities;
import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.enhanced.EnhNode;
import com.hp.hpl.jena.enhanced.Implementation;
import com.hp.hpl.jena.graph.Graph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Profile;
import com.hp.hpl.jena.ontology.impl.IndividualImpl;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.util.iterator.WrappedIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.util.iterator.Map1;

// import interface
import eu.lod2.nlp2rdf.schema.sso.ISentence;

/**
 * Class http://nlp2rdf.lod2.eu/schema/sso/Sentence
 */
public class Sentence extends IndividualImpl implements ISentence {

	private static Log log = LogFactory.getLog(Sentence.class);

	/**
	 * Implementation factory for Sentence
	 */
	static final public Implementation factory = new Implementation() {

		/**
		 * Convert a Node into an instance of the class
		 */
		public EnhNode wrap(Node n, EnhGraph eg) {
			if (canWrap(n, eg)) {
				return new Sentence(n, eg);
			} else {
				log.warn("Cannot convert node " + n.toString() + " to  Sentence");
				return null;
			}
		}

		/**
		 * Return true iff the node can be converted to an instance of
		 * this class (Sentence)
		 */
		public boolean canWrap(Node n, EnhGraph eg) {
			Profile profile;
			if (eg instanceof OntModel)
				profile = ((OntModel) eg).getProfile();
			else
				return false;

			if (!profile.isSupported(n, eg, Individual.class)) {
				return false;
			}

			Graph graph = eg.asGraph();
			return graph.contains(n, com.hp.hpl.jena.vocabulary.RDF.type.asNode(), eu.lod2.nlp2rdf.schema.tools.Vocabulary.Sentence.asNode());
		}
	};

	/**
	 * Filtering support for Sentence
	 */
	static final public Filter<Sentence> nullFilter = new NullFilter<Sentence>();

	/**
	 * Mapping support for Sentence
	 */
	public static <From> Map1<From, Sentence> mapperFrom(Class<From> from) {
		return new Map1<From, Sentence>() {
			@Override
			public Sentence map1(Object x) {
				if (x instanceof Statement) {
					Resource r = ((Statement) x).getResource();
					if (r.canAs(Sentence.class))
						return r.as(Sentence.class);
				} else if (x instanceof RDFNode) {
					if (((RDFNode) x).canAs(Sentence.class))
						return ((RDFNode) x).as(Sentence.class);
				}
				return null;
			}
		};
	}

	// Instantiate some mappers for general use
	static final public Map1<Statement, Sentence> statementMapper = mapperFrom(Statement.class);
	static final public Map1<Individual, Sentence> individualMapper = mapperFrom(Individual.class);
	static final public Map1<RDFNode, Sentence> nodeMapper = mapperFrom(RDFNode.class);

	/**
	 * Generic functions from parent class
	 */
	public Sentence(Node n, EnhGraph g) {
		super(n, g);
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void register() {
		log.debug("Registering custom class Sentence with jena");
		BuiltinPersonalities.model.add(Sentence.class, Sentence.factory);
		BuiltinPersonalities.model.add(eu.lod2.nlp2rdf.schema.sso.Sentence.class, Sentence.factory);
	}

	/**
	 * Static Functions for instance handling
	 */
	public static Sentence get(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		return (eu.lod2.nlp2rdf.schema.sso.Sentence) individual.as(eu.lod2.nlp2rdf.schema.sso.Sentence.class);
	}

	public static Sentence get(java.lang.String uri) {
		return get(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Sentence> iterate(OntModel ontModel) {
		ExtendedIterator<Individual> it = ontModel.listIndividuals(eu.lod2.nlp2rdf.schema.tools.Vocabulary.Sentence);
		return it.mapWith(individualMapper).filterDrop(nullFilter);
	}

	public static Iterator<Sentence> iterate() {
		return iterate(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Sentence> list(OntModel ontModel) {
		List<Sentence> list = new ArrayList<Sentence>();
		Iterator<Sentence> it = iterate(ontModel);
		while (it.hasNext()) {
			Sentence cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Sentence> list() {
		return list(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Sentence> iterate(boolean direct, OntModel ontModel) {
		OntClass cls = ontModel.getOntClass("http://nlp2rdf.lod2.eu/schema/sso/Sentence");
		ExtendedIterator<? extends RDFNode> it = cls.listInstances(direct);
		ExtendedIterator<RDFNode> nodeIt = new WrappedIterator<RDFNode>(it) {
		};
		return nodeIt.mapWith(nodeMapper).filterDrop(nullFilter);
	}

	public static Iterator<Sentence> iterate(boolean direct) {
		return iterate(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Sentence> list(boolean direct, OntModel ontModel) {
		List<Sentence> list = new ArrayList<Sentence>();
		Iterator<Sentence> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			Sentence cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Sentence> list(boolean direct) {
		return list(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(OntModel ontModel) {
		int count = 0;
		Iterator<Sentence> it = iterate(ontModel);
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public static int count() {
		return count(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(boolean direct, OntModel ontModel) {
		int count = 0;
		Iterator<Sentence> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public static int count(boolean direct) {
		return count(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static boolean exists(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		if (individual != null)
			return true;
		return false;
	}

	public static boolean exists(java.lang.String uri) {
		return exists(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Sentence create(java.lang.String uri, OntModel ontModel) {
		return (Sentence) ontModel.createOntResource(Sentence.class, eu.lod2.nlp2rdf.schema.tools.Vocabulary.Sentence, uri);
	}

	public static Sentence create(OntModel ontModel) {
		return create(null, ontModel);
	}

	public static Sentence create(java.lang.String uri) {
		return create(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Sentence create() {
		return create(null, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static void delete(java.lang.String uri, OntModel ontModel) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri, ontModel);
	}

	public static void delete(java.lang.String uri) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri);
	}

	/**
	 * Domain property previousSentence
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousSentence
	 */
	public boolean existsPreviousSentence() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence);
	}

	public boolean hasPreviousSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence, sentenceValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Sentence getPreviousSentence() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Sentence.class))
			return (eu.lod2.nlp2rdf.schema.sso.Sentence) n.as(eu.lod2.nlp2rdf.schema.sso.Sentence.class);
		else {
			log.warn("Could not convert previousSentence of " + getURI() + " (" + n + ") to type Sentence");
			return null;
		}
	}

	public void setPreviousSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence, sentenceValue);
	}

	public void removePreviousSentence() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentence);
	}

	/**
	 * Domain property nextSentence
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextSentence
	 */
	public boolean existsNextSentence() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence);
	}

	public boolean hasNextSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence, sentenceValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Sentence getNextSentence() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Sentence.class))
			return (eu.lod2.nlp2rdf.schema.sso.Sentence) n.as(eu.lod2.nlp2rdf.schema.sso.Sentence.class);
		else {
			log.warn("Could not convert nextSentence of " + getURI() + " (" + n + ") to type Sentence");
			return null;
		}
	}

	public void setNextSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence, sentenceValue);
	}

	public void removeNextSentence() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentence);
	}

	/**
	 * Domain property word
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/word
	 */
	public boolean existsWord() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word);
	}

	public boolean hasWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word, wordValue);
	}

	public int countWord() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateWord();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iterateWord() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word);
		return it.mapWith(eu.lod2.nlp2rdf.schema.sso.Word.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.sso.Word.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listWord() {
		List<eu.lod2.nlp2rdf.schema.sso.Word> list = new ArrayList<eu.lod2.nlp2rdf.schema.sso.Word>();
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateWord();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.sso.Word inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word, wordValue);
	}

	public void addAllWord(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList) {
		for (eu.lod2.nlp2rdf.schema.sso.IWord o : wordList)
			addWord(o);

	}

	public void removeWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word, wordValue);
	}

	public void removeAllWord() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.word);
	}

	/**
	 * Domain property firstWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/firstWord
	 */
	public boolean existsFirstWord() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord);
	}

	public boolean hasFirstWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord, wordValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Word getFirstWord() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Word.class))
			return (eu.lod2.nlp2rdf.schema.sso.Word) n.as(eu.lod2.nlp2rdf.schema.sso.Word.class);
		else {
			log.warn("Could not convert firstWord of " + getURI() + " (" + n + ") to type Word");
			return null;
		}
	}

	public void setFirstWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord, wordValue);
	}

	public void removeFirstWord() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.firstWord);
	}

	/**
	 * Domain property lastWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/lastWord
	 */
	public boolean existsLastWord() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord);
	}

	public boolean hasLastWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord, wordValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Word getLastWord() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Word.class))
			return (eu.lod2.nlp2rdf.schema.sso.Word) n.as(eu.lod2.nlp2rdf.schema.sso.Word.class);
		else {
			log.warn("Could not convert lastWord of " + getURI() + " (" + n + ") to type Word");
			return null;
		}
	}

	public void setLastWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord, wordValue);
	}

	public void removeLastWord() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lastWord);
	}

	/**
	 * Domain property previousSentenceTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousSentenceTrans
	 */
	public boolean existsPreviousSentenceTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans);
	}

	public boolean hasPreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans, sentenceValue);
	}

	public int countPreviousSentenceTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> it = iteratePreviousSentenceTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> iteratePreviousSentenceTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.sso.Sentence.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.sso.Sentence.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.sso.Sentence> listPreviousSentenceTrans() {
		List<eu.lod2.nlp2rdf.schema.sso.Sentence> list = new ArrayList<eu.lod2.nlp2rdf.schema.sso.Sentence>();
		Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> it = iteratePreviousSentenceTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.sso.Sentence inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addPreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans, sentenceValue);
	}

	public void addAllPreviousSentenceTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.ISentence> sentenceList) {
		for (eu.lod2.nlp2rdf.schema.sso.ISentence o : sentenceList)
			addPreviousSentenceTrans(o);

	}

	public void removePreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans, sentenceValue);
	}

	public void removeAllPreviousSentenceTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousSentenceTrans);
	}

	/**
	 * Domain property superString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/superString
	 */
	public boolean existsSuperString() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString);
	}

	public boolean hasSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString, stringValue);
	}

	public int countSuperString() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSuperString();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSuperString() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString);
		return it.mapWith(eu.lod2.nlp2rdf.schema.str.String.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.str.String.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.str.String> listSuperString() {
		List<eu.lod2.nlp2rdf.schema.str.String> list = new ArrayList<eu.lod2.nlp2rdf.schema.str.String>();
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSuperString();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.str.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString, stringValue);
	}

	public void addAllSuperString(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList) {
		for (eu.lod2.nlp2rdf.schema.str.IString o : stringList)
			addSuperString(o);

	}

	public void removeSuperString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString, stringValue);
	}

	public void removeAllSuperString() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superString);
	}

	/**
	 * Domain property subString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/subString
	 */
	public boolean existsSubString() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString);
	}

	public boolean hasSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString, stringValue);
	}

	public int countSubString() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSubString();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSubString() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString);
		return it.mapWith(eu.lod2.nlp2rdf.schema.str.String.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.str.String.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.str.String> listSubString() {
		List<eu.lod2.nlp2rdf.schema.str.String> list = new ArrayList<eu.lod2.nlp2rdf.schema.str.String>();
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSubString();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.str.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString, stringValue);
	}

	public void addAllSubString(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList) {
		for (eu.lod2.nlp2rdf.schema.str.IString o : stringList)
			addSubString(o);

	}

	public void removeSubString(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString, stringValue);
	}

	public void removeAllSubString() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subString);
	}

	/**
	 * Domain property superStringTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/string/superStringTrans
	 */
	public boolean existsSuperStringTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans);
	}

	public boolean hasSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans, stringValue);
	}

	public int countSuperStringTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSuperStringTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSuperStringTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.str.String.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.str.String.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.str.String> listSuperStringTrans() {
		List<eu.lod2.nlp2rdf.schema.str.String> list = new ArrayList<eu.lod2.nlp2rdf.schema.str.String>();
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSuperStringTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.str.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans, stringValue);
	}

	public void addAllSuperStringTrans(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList) {
		for (eu.lod2.nlp2rdf.schema.str.IString o : stringList)
			addSuperStringTrans(o);

	}

	public void removeSuperStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans, stringValue);
	}

	public void removeAllSuperStringTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.superStringTrans);
	}

	/**
	 * Domain property subStringTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/string/subStringTrans
	 */
	public boolean existsSubStringTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans);
	}

	public boolean hasSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans, stringValue);
	}

	public int countSubStringTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSubStringTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.str.String> iterateSubStringTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.str.String.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.str.String.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.str.String> listSubStringTrans() {
		List<eu.lod2.nlp2rdf.schema.str.String> list = new ArrayList<eu.lod2.nlp2rdf.schema.str.String>();
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = iterateSubStringTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.str.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans, stringValue);
	}

	public void addAllSubStringTrans(List<? extends eu.lod2.nlp2rdf.schema.str.IString> stringList) {
		for (eu.lod2.nlp2rdf.schema.str.IString o : stringList)
			addSubStringTrans(o);

	}

	public void removeSubStringTrans(eu.lod2.nlp2rdf.schema.str.IString stringValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans, stringValue);
	}

	public void removeAllSubStringTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.subStringTrans);
	}

	/**
	 * Domain property anchorOf
	 * with uri http://nlp2rdf.lod2.eu/schema/string/anchorOf
	 */
	public boolean existsAnchorOf() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf);
	}

	public boolean hasAnchorOf(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf);
	}

	public java.lang.String getAnchorOf() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf);
		if (n instanceof Literal) {
			Literal l = (Literal) n;
			return (java.lang.String) (nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getString(l));
		} else {
			log.warn("Could not convert anchorOf of " + getURI() + " (" + n + ") to type String");
			return null;
		}
	}

	public void setAnchorOf(java.lang.String stringValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf);
		nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf, literal);
	}

	public void removeAnchorOf() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.anchorOf);
	}

	/**
	 * Domain property endIndex
	 * with uri http://nlp2rdf.lod2.eu/schema/string/endIndex
	 */
	public boolean existsEndIndex() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex);
	}

	public boolean hasEndIndex(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex);
	}

	public int countEndIndex() {
		int count = 0;
		Iterator<java.lang.String> it = iterateEndIndex();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateEndIndex() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listEndIndex() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateEndIndex();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addEndIndex(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex, literal);
	}

	public void addAllEndIndex(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addEndIndex(o);
	}

	public void removeEndIndex(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex, literal);
	}

	public void removeAllEndIndex() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.endIndex);

	}

	/**
	 * Domain property beginIndex
	 * with uri http://nlp2rdf.lod2.eu/schema/string/beginIndex
	 */
	public boolean existsBeginIndex() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex);
	}

	public boolean hasBeginIndex(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex);
	}

	public int countBeginIndex() {
		int count = 0;
		Iterator<java.lang.String> it = iterateBeginIndex();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateBeginIndex() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listBeginIndex() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateBeginIndex();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addBeginIndex(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex, literal);
	}

	public void addAllBeginIndex(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addBeginIndex(o);
	}

	public void removeBeginIndex(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex, literal);
	}

	public void removeAllBeginIndex() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.beginIndex);

	}

	/**
	 * Domain property rightContext
	 * with uri http://nlp2rdf.lod2.eu/schema/string/rightContext
	 */
	public boolean existsRightContext() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext);
	}

	public boolean hasRightContext(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext);
	}

	public int countRightContext() {
		int count = 0;
		Iterator<java.lang.String> it = iterateRightContext();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateRightContext() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listRightContext() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateRightContext();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addRightContext(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext, literal);
	}

	public void addAllRightContext(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addRightContext(o);
	}

	public void removeRightContext(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext, literal);
	}

	public void removeAllRightContext() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.rightContext);

	}

	/**
	 * Domain property leftContext
	 * with uri http://nlp2rdf.lod2.eu/schema/string/leftContext
	 */
	public boolean existsLeftContext() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext);
	}

	public boolean hasLeftContext(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext);
	}

	public int countLeftContext() {
		int count = 0;
		Iterator<java.lang.String> it = iterateLeftContext();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateLeftContext() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listLeftContext() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateLeftContext();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addLeftContext(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext, literal);
	}

	public void addAllLeftContext(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addLeftContext(o);
	}

	public void removeLeftContext(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext, literal);
	}

	public void removeAllLeftContext() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.leftContext);

	}

}
