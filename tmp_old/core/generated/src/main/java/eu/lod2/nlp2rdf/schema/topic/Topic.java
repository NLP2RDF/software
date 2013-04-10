package eu.lod2.nlp2rdf.schema.topic;

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
import eu.lod2.nlp2rdf.schema.topic.ITopic;

/**
 * Class http://nlp2rdf.lod2.eu/schema/topic/Topic
 */
public class Topic extends IndividualImpl implements ITopic {

	private static Log log = LogFactory.getLog(Topic.class);

	/**
	 * Implementation factory for Topic
	 */
	static final public Implementation factory = new Implementation() {

		/**
		 * Convert a Node into an instance of the class
		 */
		public EnhNode wrap(Node n, EnhGraph eg) {
			if (canWrap(n, eg)) {
				return new Topic(n, eg);
			} else {
				log.warn("Cannot convert node " + n.toString() + " to  Topic");
				return null;
			}
		}

		/**
		 * Return true iff the node can be converted to an instance of
		 * this class (Topic)
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
			return graph.contains(n, com.hp.hpl.jena.vocabulary.RDF.type.asNode(), eu.lod2.nlp2rdf.schema.tools.Vocabulary.Topic.asNode());
		}
	};

	/**
	 * Filtering support for Topic
	 */
	static final public Filter<Topic> nullFilter = new NullFilter<Topic>();

	/**
	 * Mapping support for Topic
	 */
	public static <From> Map1<From, Topic> mapperFrom(Class<From> from) {
		return new Map1<From, Topic>() {
			@Override
			public Topic map1(Object x) {
				if (x instanceof Statement) {
					Resource r = ((Statement) x).getResource();
					if (r.canAs(Topic.class))
						return r.as(Topic.class);
				} else if (x instanceof RDFNode) {
					if (((RDFNode) x).canAs(Topic.class))
						return ((RDFNode) x).as(Topic.class);
				}
				return null;
			}
		};
	}

	// Instantiate some mappers for general use
	static final public Map1<Statement, Topic> statementMapper = mapperFrom(Statement.class);
	static final public Map1<Individual, Topic> individualMapper = mapperFrom(Individual.class);
	static final public Map1<RDFNode, Topic> nodeMapper = mapperFrom(RDFNode.class);

	/**
	 * Generic functions from parent class
	 */
	public Topic(Node n, EnhGraph g) {
		super(n, g);
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void register() {
		log.debug("Registering custom class Topic with jena");
		BuiltinPersonalities.model.add(Topic.class, Topic.factory);
		BuiltinPersonalities.model.add(eu.lod2.nlp2rdf.schema.topic.Topic.class, Topic.factory);
	}

	/**
	 * Static Functions for instance handling
	 */
	public static Topic get(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		return (eu.lod2.nlp2rdf.schema.topic.Topic) individual.as(eu.lod2.nlp2rdf.schema.topic.Topic.class);
	}

	public static Topic get(java.lang.String uri) {
		return get(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Topic> iterate(OntModel ontModel) {
		ExtendedIterator<Individual> it = ontModel.listIndividuals(eu.lod2.nlp2rdf.schema.tools.Vocabulary.Topic);
		return it.mapWith(individualMapper).filterDrop(nullFilter);
	}

	public static Iterator<Topic> iterate() {
		return iterate(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Topic> list(OntModel ontModel) {
		List<Topic> list = new ArrayList<Topic>();
		Iterator<Topic> it = iterate(ontModel);
		while (it.hasNext()) {
			Topic cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Topic> list() {
		return list(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Topic> iterate(boolean direct, OntModel ontModel) {
		OntClass cls = ontModel.getOntClass("http://nlp2rdf.lod2.eu/schema/topic/Topic");
		ExtendedIterator<? extends RDFNode> it = cls.listInstances(direct);
		ExtendedIterator<RDFNode> nodeIt = new WrappedIterator<RDFNode>(it) {
		};
		return nodeIt.mapWith(nodeMapper).filterDrop(nullFilter);
	}

	public static Iterator<Topic> iterate(boolean direct) {
		return iterate(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Topic> list(boolean direct, OntModel ontModel) {
		List<Topic> list = new ArrayList<Topic>();
		Iterator<Topic> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			Topic cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Topic> list(boolean direct) {
		return list(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(OntModel ontModel) {
		int count = 0;
		Iterator<Topic> it = iterate(ontModel);
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
		Iterator<Topic> it = iterate(direct, ontModel);
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

	public static Topic create(java.lang.String uri, OntModel ontModel) {
		return (Topic) ontModel.createOntResource(Topic.class, eu.lod2.nlp2rdf.schema.tools.Vocabulary.Topic, uri);
	}

	public static Topic create(OntModel ontModel) {
		return create(null, ontModel);
	}

	public static Topic create(java.lang.String uri) {
		return create(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Topic create() {
		return create(null, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static void delete(java.lang.String uri, OntModel ontModel) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri, ontModel);
	}

	public static void delete(java.lang.String uri) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri);
	}

	/**
	 * Domain property occursIn
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/occursIn
	 */
	public boolean existsOccursIn() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn);
	}

	public boolean hasOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn, documentValue);
	}

	public int countOccursIn() {
		int count = 0;
		Iterator<eu.lod2.nlp2rdf.schema.str.Document> it = iterateOccursIn();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<eu.lod2.nlp2rdf.schema.str.Document> iterateOccursIn() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn);
		return it.mapWith(eu.lod2.nlp2rdf.schema.str.Document.statementMapper).filterDrop(eu.lod2.nlp2rdf.schema.str.Document.nullFilter);
	}

	public List<eu.lod2.nlp2rdf.schema.str.Document> listOccursIn() {
		List<eu.lod2.nlp2rdf.schema.str.Document> list = new ArrayList<eu.lod2.nlp2rdf.schema.str.Document>();
		Iterator<eu.lod2.nlp2rdf.schema.str.Document> it = iterateOccursIn();
		while (it.hasNext()) {
			eu.lod2.nlp2rdf.schema.str.Document inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue) {
		addProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn, documentValue);
	}

	public void addAllOccursIn(List<? extends eu.lod2.nlp2rdf.schema.str.IDocument> documentList) {
		for (eu.lod2.nlp2rdf.schema.str.IDocument o : documentList)
			addOccursIn(o);

	}

	public void removeOccursIn(eu.lod2.nlp2rdf.schema.str.IDocument documentValue) {
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn, documentValue);
	}

	public void removeAllOccursIn() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.occursIn);
	}

	/**
	 * Domain property characterticLemma
	 * with uri http://nlp2rdf.lod2.eu/schema/topic/characteristicLemma
	 */
	public boolean existsCharacterticLemma() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma);
	}

	public boolean hasCharacterticLemma(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma);
	}

	public int countCharacterticLemma() {
		int count = 0;
		Iterator<java.lang.String> it = iterateCharacterticLemma();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateCharacterticLemma() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listCharacterticLemma() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateCharacterticLemma();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addCharacterticLemma(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma, literal);
	}

	public void addAllCharacterticLemma(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addCharacterticLemma(o);
	}

	public void removeCharacterticLemma(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma, literal);
	}

	public void removeAllCharacterticLemma() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.characterticLemma);

	}

}
