package eu.lod2.nlp2rdf.schema.str;

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
import eu.lod2.nlp2rdf.schema.str.IDocument;

/**
 * Class http://nlp2rdf.lod2.eu/schema/string/Document
 */
public class Document extends IndividualImpl implements IDocument {

	private static Log log = LogFactory.getLog(Document.class);

	/**
	 * Implementation factory for Document
	 */
	static final public Implementation factory = new Implementation() {

		/**
		 * Convert a Node into an instance of the class
		 */
		public EnhNode wrap(Node n, EnhGraph eg) {
			if (canWrap(n, eg)) {
				return new Document(n, eg);
			} else {
				log.warn("Cannot convert node " + n.toString() + " to  Document");
				return null;
			}
		}

		/**
		 * Return true iff the node can be converted to an instance of
		 * this class (Document)
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
			return graph.contains(n, com.hp.hpl.jena.vocabulary.RDF.type.asNode(), eu.lod2.nlp2rdf.schema.tools.Vocabulary.Document.asNode());
		}
	};

	/**
	 * Filtering support for Document
	 */
	static final public Filter<Document> nullFilter = new NullFilter<Document>();

	/**
	 * Mapping support for Document
	 */
	public static <From> Map1<From, Document> mapperFrom(Class<From> from) {
		return new Map1<From, Document>() {
			@Override
			public Document map1(Object x) {
				if (x instanceof Statement) {
					Resource r = ((Statement) x).getResource();
					if (r.canAs(Document.class))
						return r.as(Document.class);
				} else if (x instanceof RDFNode) {
					if (((RDFNode) x).canAs(Document.class))
						return ((RDFNode) x).as(Document.class);
				}
				return null;
			}
		};
	}

	// Instantiate some mappers for general use
	static final public Map1<Statement, Document> statementMapper = mapperFrom(Statement.class);
	static final public Map1<Individual, Document> individualMapper = mapperFrom(Individual.class);
	static final public Map1<RDFNode, Document> nodeMapper = mapperFrom(RDFNode.class);

	/**
	 * Generic functions from parent class
	 */
	public Document(Node n, EnhGraph g) {
		super(n, g);
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void register() {
		log.debug("Registering custom class Document with jena");
		BuiltinPersonalities.model.add(Document.class, Document.factory);
		BuiltinPersonalities.model.add(eu.lod2.nlp2rdf.schema.str.Document.class, Document.factory);
	}

	/**
	 * Static Functions for instance handling
	 */
	public static Document get(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		return (eu.lod2.nlp2rdf.schema.str.Document) individual.as(eu.lod2.nlp2rdf.schema.str.Document.class);
	}

	public static Document get(java.lang.String uri) {
		return get(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Document> iterate(OntModel ontModel) {
		ExtendedIterator<Individual> it = ontModel.listIndividuals(eu.lod2.nlp2rdf.schema.tools.Vocabulary.Document);
		return it.mapWith(individualMapper).filterDrop(nullFilter);
	}

	public static Iterator<Document> iterate() {
		return iterate(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Document> list(OntModel ontModel) {
		List<Document> list = new ArrayList<Document>();
		Iterator<Document> it = iterate(ontModel);
		while (it.hasNext()) {
			Document cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Document> list() {
		return list(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Document> iterate(boolean direct, OntModel ontModel) {
		OntClass cls = ontModel.getOntClass("http://nlp2rdf.lod2.eu/schema/string/Document");
		ExtendedIterator<? extends RDFNode> it = cls.listInstances(direct);
		ExtendedIterator<RDFNode> nodeIt = new WrappedIterator<RDFNode>(it) {
		};
		return nodeIt.mapWith(nodeMapper).filterDrop(nullFilter);
	}

	public static Iterator<Document> iterate(boolean direct) {
		return iterate(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Document> list(boolean direct, OntModel ontModel) {
		List<Document> list = new ArrayList<Document>();
		Iterator<Document> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			Document cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Document> list(boolean direct) {
		return list(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(OntModel ontModel) {
		int count = 0;
		Iterator<Document> it = iterate(ontModel);
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
		Iterator<Document> it = iterate(direct, ontModel);
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

	public static Document create(java.lang.String uri, OntModel ontModel) {
		return (Document) ontModel.createOntResource(Document.class, eu.lod2.nlp2rdf.schema.tools.Vocabulary.Document, uri);
	}

	public static Document create(OntModel ontModel) {
		return create(null, ontModel);
	}

	public static Document create(java.lang.String uri) {
		return create(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Document create() {
		return create(null, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static void delete(java.lang.String uri, OntModel ontModel) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri, ontModel);
	}

	public static void delete(java.lang.String uri) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri);
	}

	/**
	 * Domain property sourceUrl
	 * with uri http://nlp2rdf.lod2.eu/schema/string/sourceUrl
	 */
	public boolean existsSourceUrl() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl);
	}

	public boolean hasSourceUrl(eu.lod2.nlp2rdf.schema.IThing thingValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl, thingValue);
	}

	public eu.lod2.nlp2rdf.schema.Thing getSourceUrl() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl);
		if (n.canAs(eu.lod2.nlp2rdf.schema.Thing.class))
			return (eu.lod2.nlp2rdf.schema.Thing) n.as(eu.lod2.nlp2rdf.schema.Thing.class);
		else {
			log.warn("Could not convert sourceUrl of " + getURI() + " (" + n + ") to type Thing");
			return null;
		}
	}

	public void setSourceUrl(eu.lod2.nlp2rdf.schema.IThing thingValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl, thingValue);
	}

	public void removeSourceUrl() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceUrl);
	}

	/**
	 * Domain property topic
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/hasTopic
	 */
	public boolean existsTopic() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic);
	}

	public boolean hasTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic, topicValue);
	}

	public int countTopic() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> it = iterateTopic();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> iterateTopic() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic);
		return it.mapWith(eu.lod2.nlp2rdf.schema.topic.Topic.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.topic.Topic.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.topic.Topic> listTopic() {
		List<eu.lod2.nlp2rdf.schema.topic.Topic> list = new ArrayList<eu.lod2.nlp2rdf.schema.topic.Topic>();
		Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> it = iterateTopic();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.topic.Topic inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic, topicValue);
	}

	public void addAllTopic(List<? extends eu.lod2.nlp2rdf.schema.topic.ITopic> topicList) {
		for (eu.lod2.nlp2rdf.schema.topic.ITopic o : topicList)
			addTopic(o);

	}

	public void removeTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic, topicValue);
	}

	public void removeAllTopic() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.topic);
	}

	/**
	 * Domain property dominatingTopic
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/dominatingTopic
	 */
	public boolean existsDominatingTopic() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic);
	}

	public boolean hasDominatingTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic, topicValue);
	}

	public eu.lod2.nlp2rdf.schema.topic.Topic getDominatingTopic() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic);
		if (n.canAs(eu.lod2.nlp2rdf.schema.topic.Topic.class))
			return (eu.lod2.nlp2rdf.schema.topic.Topic) n.as(eu.lod2.nlp2rdf.schema.topic.Topic.class);
		else {
			log.warn("Could not convert dominatingTopic of " + getURI() + " (" + n + ") to type Topic");
			return null;
		}
	}

	public void setDominatingTopic(eu.lod2.nlp2rdf.schema.topic.ITopic topicValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic);
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic, topicValue);
	}

	public void removeDominatingTopic() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.dominatingTopic);
	}

	/**
	 * Domain property sourceString
	 * with uri http://nlp2rdf.lod2.eu/schema/string/sourceString
	 */
	public boolean existsSourceString() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString);
	}

	public boolean hasSourceString(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString);
	}

	public java.lang.String getSourceString() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString);
		if (n instanceof Literal) {
			Literal l = (Literal) n;
			return (java.lang.String) (nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getString(l));
		} else {
			log.warn("Could not convert sourceString of " + getURI() + " (" + n + ") to type String");
			return null;
		}
	}

	public void setSourceString(java.lang.String stringValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString);
		nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString, literal);
	}

	public void removeSourceString() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.sourceString);
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
