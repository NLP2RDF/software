package eu.lod2.nlp2rdf.schema.error;

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
import eu.lod2.nlp2rdf.schema.error.IError;

/**
 * Class http://nlp2rdf.lod2.eu/schema/error/Error
 */
public class Error extends IndividualImpl implements IError {

	private static Log log = LogFactory.getLog(Error.class);

	/**
	 * Implementation factory for Error
	 */
	static final public Implementation factory = new Implementation() {

		/**
		 * Convert a Node into an instance of the class
		 */
		public EnhNode wrap(Node n, EnhGraph eg) {
			if (canWrap(n, eg)) {
				return new Error(n, eg);
			} else {
				log.warn("Cannot convert node " + n.toString() + " to  Error");
				return null;
			}
		}

		/**
		 * Return true iff the node can be converted to an instance of
		 * this class (Error)
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
			return graph.contains(n, com.hp.hpl.jena.vocabulary.RDF.type.asNode(), eu.lod2.nlp2rdf.schema.tools.Vocabulary.Error.asNode());
		}
	};

	/**
	 * Filtering support for Error
	 */
	static final public Filter<Error> nullFilter = new NullFilter<Error>();

	/**
	 * Mapping support for Error
	 */
	public static <From> Map1<From, Error> mapperFrom(Class<From> from) {
		return new Map1<From, Error>() {
			@Override
			public Error map1(Object x) {
				if (x instanceof Statement) {
					Resource r = ((Statement) x).getResource();
					if (r.canAs(Error.class))
						return r.as(Error.class);
				} else if (x instanceof RDFNode) {
					if (((RDFNode) x).canAs(Error.class))
						return ((RDFNode) x).as(Error.class);
				}
				return null;
			}
		};
	}

	// Instantiate some mappers for general use
	static final public Map1<Statement, Error> statementMapper = mapperFrom(Statement.class);
	static final public Map1<Individual, Error> individualMapper = mapperFrom(Individual.class);
	static final public Map1<RDFNode, Error> nodeMapper = mapperFrom(RDFNode.class);

	/**
	 * Generic functions from parent class
	 */
	public Error(Node n, EnhGraph g) {
		super(n, g);
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void register() {
		log.debug("Registering custom class Error with jena");
		BuiltinPersonalities.model.add(Error.class, Error.factory);
		BuiltinPersonalities.model.add(eu.lod2.nlp2rdf.schema.error.Error.class, Error.factory);
	}

	/**
	 * Static Functions for instance handling
	 */
	public static Error get(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		return (eu.lod2.nlp2rdf.schema.error.Error) individual.as(eu.lod2.nlp2rdf.schema.error.Error.class);
	}

	public static Error get(java.lang.String uri) {
		return get(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Error> iterate(OntModel ontModel) {
		ExtendedIterator<Individual> it = ontModel.listIndividuals(eu.lod2.nlp2rdf.schema.tools.Vocabulary.Error);
		return it.mapWith(individualMapper).filterDrop(nullFilter);
	}

	public static Iterator<Error> iterate() {
		return iterate(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Error> list(OntModel ontModel) {
		List<Error> list = new ArrayList<Error>();
		Iterator<Error> it = iterate(ontModel);
		while (it.hasNext()) {
			Error cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Error> list() {
		return list(eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Iterator<Error> iterate(boolean direct, OntModel ontModel) {
		OntClass cls = ontModel.getOntClass("http://nlp2rdf.lod2.eu/schema/error/Error");
		ExtendedIterator<? extends RDFNode> it = cls.listInstances(direct);
		ExtendedIterator<RDFNode> nodeIt = new WrappedIterator<RDFNode>(it) {
		};
		return nodeIt.mapWith(nodeMapper).filterDrop(nullFilter);
	}

	public static Iterator<Error> iterate(boolean direct) {
		return iterate(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static List<Error> list(boolean direct, OntModel ontModel) {
		List<Error> list = new ArrayList<Error>();
		Iterator<Error> it = iterate(direct, ontModel);
		while (it.hasNext()) {
			Error cls = it.next();
			list.add(cls);
		}
		return list;
	}

	public static List<Error> list(boolean direct) {
		return list(direct, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static int count(OntModel ontModel) {
		int count = 0;
		Iterator<Error> it = iterate(ontModel);
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
		Iterator<Error> it = iterate(direct, ontModel);
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

	public static Error create(java.lang.String uri, OntModel ontModel) {
		return (Error) ontModel.createOntResource(Error.class, eu.lod2.nlp2rdf.schema.tools.Vocabulary.Error, uri);
	}

	public static Error create(OntModel ontModel) {
		return create(null, ontModel);
	}

	public static Error create(java.lang.String uri) {
		return create(uri, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static Error create() {
		return create(null, eu.lod2.nlp2rdf.schema.tools.Factory.getDefaultModel());
	}

	public static void delete(java.lang.String uri, OntModel ontModel) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri, ontModel);
	}

	public static void delete(java.lang.String uri) {
		eu.lod2.nlp2rdf.schema.tools.Factory.deleteInstance(uri);
	}

	/**
	 * Domain property fatal
	 * with uri http://nlp2rdf.lod2.eu/schema/error/fatal
	 */
	public boolean existsFatal() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal);
	}

	public boolean hasFatal(java.lang.Boolean booleanValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal);
	}

	public java.lang.Boolean getFatal() {
		RDFNode n = getPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal);
		if (n instanceof Literal) {
			Literal l = (Literal) n;
			return (java.lang.Boolean) (nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getBoolean(l));
		} else {
			log.warn("Could not convert fatal of " + getURI() + " (" + n + ") to type Boolean");
			return null;
		}
	}

	public void setFatal(java.lang.Boolean booleanValue) {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal);
		nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), booleanValue, "http://www.w3.org/2001/XMLSchema#boolean");
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), booleanValue, "http://www.w3.org/2001/XMLSchema#boolean");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal, literal);
	}

	public void removeFatal() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.fatal);
	}

	/**
	 * Domain property source
	 * with uri http://nlp2rdf.lod2.eu/schema/error/source
	 */
	public boolean existsSource() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source);
	}

	public boolean hasSource(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source);
	}

	public int countSource() {
		int count = 0;
		Iterator<java.lang.String> it = iterateSource();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateSource() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listSource() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateSource();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addSource(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source, literal);
	}

	public void addAllSource(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addSource(o);
	}

	public void removeSource(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source, literal);
	}

	public void removeAllSource() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.source);

	}

	/**
	 * Domain property message
	 * with uri http://nlp2rdf.lod2.eu/schema/error/hasMessage
	 */
	public boolean existsMessage() {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message);
	}

	public boolean hasMessage(java.lang.String stringValue) {
		return hasProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message);
	}

	public int countMessage() {
		int count = 0;
		Iterator<java.lang.String> it = iterateMessage();
		while (it.hasNext()) {
			it.next();
			count++;
		}
		return count;
	}

	public Iterator<java.lang.String> iterateMessage() {
		ExtendedIterator<Statement> it = listProperties(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message);
		return it.mapWith(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.objectAsStringMapper).filterDrop(new NullFilter<java.lang.String>());
	}

	public List<java.lang.String> listMessage() {
		List<java.lang.String> list = new ArrayList<java.lang.String>();
		Iterator<java.lang.String> it = iterateMessage();
		while (it.hasNext()) {
			java.lang.String inst = it.next();
			list.add(inst);
		}
		return list;
	}

	public void addMessage(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		setPropertyValue(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message, literal);
	}

	public void addAllMessage(List<java.lang.String> stringList) {
		for (java.lang.String o : stringList)
			addMessage(o);
	}

	public void removeMessage(java.lang.String stringValue) {
		Literal literal = nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.createTypedLiteral((OntModel) getModel(), stringValue, "http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral");
		removeProperty(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message, literal);
	}

	public void removeAllMessage() {
		removeAll(eu.lod2.nlp2rdf.schema.tools.Vocabulary.message);

	}

}
