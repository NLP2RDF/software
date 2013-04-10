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
import eu.lod2.nlp2rdf.schema.sso.IStopWord;

/**
 * Class http://nlp2rdf.lod2.eu/schema/sso/StopWord
 */
public class StopWord extends IndividualImpl implements IStopWord {

	private static Log log = LogFactory.getLog(StopWord.class);

	/**
	 * Implementation factory for StopWord
	 */
	static final public Implementation factory = new Implementation() {

		/**
		 * Convert a Node into an instance of the class
		 */
		public EnhNode wrap(Node n, EnhGraph eg) {
			if (canWrap(n, eg)) {
				return new StopWord(n, eg);
			} else {
				log.warn("Cannot convert node " + n.toString() + " to  StopWord");
				return null;
			}
		}

		/**
		 * Return true iff the node can be converted to an instance of
		 * this class (StopWord)
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
			return graph.contains(n, com.hp.hpl.jena.vocabulary.RDF.type.asNode(), eu.lod2.nlp2rdf.schema.tools.Vocabulary.StopWord.asNode());
		}
	};

	/**
	 * Filtering support for StopWord
	 */
	static final public Filter<StopWord> nullFilter = new NullFilter<StopWord>();

	/**
	 * Mapping support for StopWord
	 */
	public static <From> Map1<From, StopWord> mapperFrom(Class<From> from) {
		return new Map1<From, StopWord>() {
			@Override
			public StopWord map1(Object x) {
				if (x instanceof Statement) {
					Resource r = ((Statement) x).getResource();
					if (r.canAs(StopWord.class))
						return r.as(StopWord.class);
				} else if (x instanceof RDFNode) {
					if (((RDFNode) x).canAs(StopWord.class))
						return ((RDFNode) x).as(StopWord.class);
				}
				return null;
			}
		};
	}

	// Instantiate some mappers for general use
	static final public Map1<Statement, StopWord> statementMapper = mapperFrom(Statement.class);
	static final public Map1<Individual, StopWord> individualMapper = mapperFrom(Individual.class);
	static final public Map1<RDFNode, StopWord> nodeMapper = mapperFrom(RDFNode.class);

	/**
	 * Generic functions from parent class
	 */
	public StopWord(Node n, EnhGraph g) {
		super(n, g);
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void register() {
		log.debug("Registering custom class StopWord with jena");
		BuiltinPersonalities.model.add(StopWord.class, StopWord.factory);
		BuiltinPersonalities.model.add(eu.lod2.nlp2rdf.schema.sso.StopWord.class, StopWord.factory);
	}

	/**
	 * Static Functions for instance handling
	 */
	public static StopWord get(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		return (eu.lod2.nlp2rdf.schema.sso.StopWord) individual.as(eu.lod2.nlp2rdf.schema.sso.StopWord.class);
	}

	public static StopWord get(java.lang.String uri) {
		return get(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<StopWord> iterate(OntModel ontModel) {
		ExtendedIterator<Individual> it = ontModel.listIndividuals(eu.lod2.nlp2rdf.schema.tools.Vocabulary.StopWord);
		return it.mapWith(individualMapper).filterDrop(nullFilter);
	}

	public static Iterator<StopWord> iterate() {
		return iterate(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<StopWord> list(OntModel ontModel) {
		List<StopWord> list = new ArrayList<StopWord>();
		Iterator<StopWord> it = iterate(ontModel);
		while (it.hasNext()) {
			StopWord cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<StopWord> list() {
		return list(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<StopWord> iterate(boolean direct, OntModel ontModel) {
		OntClass cls = ontModel.getOntClass("http://nlp2rdf.lod2.eu/schema/sso/StopWord");
		ExtendedIterator<? extends RDFNode> it = cls.listInstances(direct);
		ExtendedIterator<RDFNode> nodeIt = new WrappedIterator<RDFNode>(it) {
		};
		return nodeIt.mapWith(nodeMapper).filterDrop(nullFilter);
	}

	public static Iterator<StopWord> iterate(boolean direct) {
		return iterate(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<StopWord> list(boolean direct, OntModel ontModel) {
		List<StopWord> list = new ArrayList<StopWord>();
		Iterator<StopWord> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			StopWord cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<StopWord> list(boolean direct) {
		return list(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(OntModel ontModel) {
		int count = 0;
		Iterator<StopWord> it = iterate(ontModel);
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
		Iterator<StopWord> it = iterate(direct, ontModel);
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

	public static StopWord create(java.lang.String uri, OntModel ontModel) {
		return (StopWord) ontModel.createOntResource(StopWord.class, eu.lod2.nlp2rdf.schema.tools.Vocabulary.StopWord, uri);
	}

	public static StopWord create(OntModel ontModel) {
		return create(null, ontModel);
	}

	public static StopWord create(java.lang.String uri) {
		return create(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static StopWord create() {
		return create(null, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static void delete(java.lang.String uri, OntModel ontModel) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri, ontModel);
	}

	public static void delete(java.lang.String uri) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri);
	}

	/**
	 * Domain property sentence
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/sentence
	 */
	public boolean existsSentence() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence);
	}

	public boolean hasSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence, sentenceValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Sentence getSentence() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Sentence.class))
			return (eu.lod2.nlp2rdf.schema.sso.Sentence) n.as(eu.lod2.nlp2rdf.schema.sso.Sentence.class);
		else {
			log.warn("Could not convert sentence of " + getURI() + " (" + n + ") to type Sentence");
			return null;
		}
	}

	public void setSentence(eu.lod2.nlp2rdf.schema.sso.ISentence sentenceValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence, sentenceValue);
	}

	public void removeSentence() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sentence);
	}

	/**
	 * Domain property oliaLink
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/oliaLink
	 */
	public boolean existsOliaLink() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink);
	}

	public boolean hasOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink, thingValue);
	}

	public int countOliaLink() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.Thing> it = iterateOliaLink();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.Thing> iterateOliaLink() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink);
		return it.mapWith(eu.lod2.nlp2rdf.schema.Thing.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.Thing.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.Thing> listOliaLink() {
		List<eu.lod2.nlp2rdf.schema.Thing> list = new ArrayList<eu.lod2.nlp2rdf.schema.Thing>();
		Iterator<eu.lod2.nlp2rdf.schema.Thing> it = iterateOliaLink();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.Thing inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink, thingValue);
	}

	public void addAllOliaLink(List<? extends eu.lod2.nlp2rdf.schema.IThing> thingList) {
		for (eu.lod2.nlp2rdf.schema.IThing o : thingList)
			addOliaLink(o);

	}

	public void removeOliaLink(eu.lod2.nlp2rdf.schema.IThing thingValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink, thingValue);
	}

	public void removeAllOliaLink() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.oliaLink);
	}

	/**
	 * Domain property previousWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousWord
	 */
	public boolean existsPreviousWord() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord);
	}

	public boolean hasPreviousWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord, wordValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Word getPreviousWord() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Word.class))
			return (eu.lod2.nlp2rdf.schema.sso.Word) n.as(eu.lod2.nlp2rdf.schema.sso.Word.class);
		else {
			log.warn("Could not convert previousWord of " + getURI() + " (" + n + ") to type Word");
			return null;
		}
	}

	public void setPreviousWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord, wordValue);
	}

	public void removePreviousWord() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWord);
	}

	/**
	 * Domain property nextWord
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextWord
	 */
	public boolean existsNextWord() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord);
	}

	public boolean hasNextWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord, wordValue);
	}

	public eu.lod2.nlp2rdf.schema.sso.Word getNextWord() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord);
		if (n.canAs(eu.lod2.nlp2rdf.schema.sso.Word.class))
			return (eu.lod2.nlp2rdf.schema.sso.Word) n.as(eu.lod2.nlp2rdf.schema.sso.Word.class);
		else {
			log.warn("Could not convert nextWord of " + getURI() + " (" + n + ") to type Word");
			return null;
		}
	}

	public void setNextWord(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord, wordValue);
	}

	public void removeNextWord() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWord);
	}

	/**
	 * Domain property nextSentenceTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextSentenceTrans
	 */
	public boolean existsNextSentenceTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans);
	}

	public boolean hasNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans, wordValue);
	}

	public int countNextSentenceTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateNextSentenceTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iterateNextSentenceTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.sso.Word.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.sso.Word.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listNextSentenceTrans() {
		List<eu.lod2.nlp2rdf.schema.sso.Word> list = new ArrayList<eu.lod2.nlp2rdf.schema.sso.Word>();
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateNextSentenceTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.sso.Word inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans, wordValue);
	}

	public void addAllNextSentenceTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList) {
		for (eu.lod2.nlp2rdf.schema.sso.IWord o : wordList)
			addNextSentenceTrans(o);

	}

	public void removeNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans, wordValue);
	}

	public void removeAllNextSentenceTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextSentenceTrans);
	}

	/**
	 * Domain property previousWordTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/previousWordTrans
	 */
	public boolean existsPreviousWordTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans);
	}

	public boolean hasPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans, wordValue);
	}

	public int countPreviousWordTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iteratePreviousWordTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iteratePreviousWordTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.sso.Word.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.sso.Word.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listPreviousWordTrans() {
		List<eu.lod2.nlp2rdf.schema.sso.Word> list = new ArrayList<eu.lod2.nlp2rdf.schema.sso.Word>();
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iteratePreviousWordTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.sso.Word inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans, wordValue);
	}

	public void addAllPreviousWordTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList) {
		for (eu.lod2.nlp2rdf.schema.sso.IWord o : wordList)
			addPreviousWordTrans(o);

	}

	public void removePreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans, wordValue);
	}

	public void removeAllPreviousWordTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.previousWordTrans);
	}

	/**
	 * Domain property nextWordTrans
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/nextWordTrans
	 */
	public boolean existsNextWordTrans() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans);
	}

	public boolean hasNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans, wordValue);
	}

	public int countNextWordTrans() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateNextWordTrans();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.sso.Word> iterateNextWordTrans() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans);
		return it.mapWith(eu.lod2.nlp2rdf.schema.sso.Word.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.sso.Word.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.sso.Word> listNextWordTrans() {
		List<eu.lod2.nlp2rdf.schema.sso.Word> list = new ArrayList<eu.lod2.nlp2rdf.schema.sso.Word>();
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = iterateNextWordTrans();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.sso.Word inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans, wordValue);
	}

	public void addAllNextWordTrans(List<? extends eu.lod2.nlp2rdf.schema.sso.IWord> wordList) {
		for (eu.lod2.nlp2rdf.schema.sso.IWord o : wordList)
			addNextWordTrans(o);

	}

	public void removeNextWordTrans(eu.lod2.nlp2rdf.schema.sso.IWord wordValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans, wordValue);
	}

	public void removeAllNextWordTrans() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.nextWordTrans);
	}

	/**
	 * Domain property posTag
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/posTag
	 */
	public boolean existsPosTag() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag);
	}

	public boolean hasPosTag(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag);
	}

	public int countPosTag() {
		int count = 0;
		Iterator<java.lang.String> it = iteratePosTag();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iteratePosTag() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listPosTag() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iteratePosTag();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addPosTag(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag, literal);
	}

	public void addAllPosTag(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addPosTag(o);
	}

	public void removePosTag(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag, literal);
	}

	public void removeAllPosTag() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.posTag);

	}

	/**
	 * Domain property lemma
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/lemma
	 */
	public boolean existsLemma() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma);
	}

	public boolean hasLemma(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma);
	}

	public int countLemma() {
		int count = 0;
		Iterator<java.lang.String> it = iterateLemma();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateLemma() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listLemma() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateLemma();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addLemma(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma, literal);
	}

	public void addAllLemma(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addLemma(o);
	}

	public void removeLemma(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma, literal);
	}

	public void removeAllLemma() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.lemma);

	}

	/**
	 * Domain property stem
	 * with uri http://nlp2rdf.lod2.eu/schema/sso/stem
	 */
	public boolean existsStem() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem);
	}

	public boolean hasStem(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem);
	}

	public int countStem() {
		int count = 0;
		Iterator<java.lang.String> it = iterateStem();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateStem() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listStem() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateStem();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addStem(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem, literal);
	}

	public void addAllStem(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addStem(o);
	}

	public void removeStem(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem, literal);
	}

	public void removeAllStem() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.stem);

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
