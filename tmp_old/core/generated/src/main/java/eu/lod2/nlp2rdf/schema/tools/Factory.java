package eu.lod2.nlp2rdf.schema.tools;

import java.util.Map;
import java.util.HashMap;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.ontology.OntDocumentManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Factory
 */
public class Factory {

	private static Log log = LogFactory.getLog(Factory.class);

	private static OntModel defaultModel = null;

	private static Map<java.lang.String, java.lang.String> uri2Type;

	static {
		uri2Type = new HashMap<java.lang.String, java.lang.String>();
		uri2Type.put("http://www.w3.org/2002/07/owl#Thing", "eu.lod2.nlp2rdf.schema.IThing");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/sso/StopWord", "eu.lod2.nlp2rdf.schema.sso.IStopWord");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/error/Error", "eu.lod2.nlp2rdf.schema.error.IError");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/sso/Word", "eu.lod2.nlp2rdf.schema.sso.IWord");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/sso/Phrase", "eu.lod2.nlp2rdf.schema.sso.IPhrase");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/string/OffsetBasedString", "eu.lod2.nlp2rdf.schema.str.IOffsetBasedString");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/string/Document", "eu.lod2.nlp2rdf.schema.str.IDocument");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/sso/Sentence", "eu.lod2.nlp2rdf.schema.sso.ISentence");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/string/String", "eu.lod2.nlp2rdf.schema.str.IString");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/topic/Topic", "eu.lod2.nlp2rdf.schema.topic.ITopic");
		uri2Type.put("http://nlp2rdf.lod2.eu/schema/string/ContextHashBasedString", "eu.lod2.nlp2rdf.schema.str.IContextHashBasedString");
	}

	/**
	 * Sets the default ontModel that may be used by ontology wrapper classes when no explicit ontModel argument is provided
	 */
	public static void setDefaultModel(OntModel defaultModel) {
		Factory.defaultModel = defaultModel;
	}

	/**
	 * Returns the default ontModel set with setDefaultModel()
	 */
	public static OntModel getDefaultModel() {
		if (defaultModel == null) {
			throw new RuntimeException("No default OntModel was provided to eu.lod2.nlp2rdf.schema.tools.Factory");
		}
		return defaultModel;
	}

	/**
	 * Returns the interface name for a given OWL Class
	 */
	public static java.lang.String getJavaInterfaceName(java.lang.String uri) {
		return uri2Type.get(uri);
	}

	/**
	 * Returns true if there is a java interface for the
	 * given OWL Class
	 */
	public static boolean hasJavaType(java.lang.String uri) {
		return uri2Type.containsKey(uri);
	}

	/**
	 * Deletes the individual with URI from the OntModel
	 */
	public static boolean deleteInstance(java.lang.String uri, OntModel ontModel) {
		Individual individual = ontModel.getIndividual(uri);
		if (individual != null) {
			individual.remove();
			return true;
		}
		log.warn("Could not remove non existing instance " + uri + " from model");
		return false;
	}

	/**
	 * Same as deleteInstance, but works with the default OntModel
	 * @see Factory#deleteInstance(java.lang.String, OntModel)
	 */
	public static boolean deleteInstance(java.lang.String uri) {
		return deleteInstance(uri, getDefaultModel());
	}

	/**
	 * Registers all custom classes with jena
	 */
	public static void registerCustomClasses() {
		log.info("Registering custom classes with jena");
		eu.lod2.nlp2rdf.schema.Thing.register();
		eu.lod2.nlp2rdf.schema.sso.StopWord.register();
		eu.lod2.nlp2rdf.schema.error.Error.register();
		eu.lod2.nlp2rdf.schema.sso.Word.register();
		eu.lod2.nlp2rdf.schema.sso.Phrase.register();
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.register();
		eu.lod2.nlp2rdf.schema.str.Document.register();
		eu.lod2.nlp2rdf.schema.sso.Sentence.register();
		eu.lod2.nlp2rdf.schema.str.String.register();
		eu.lod2.nlp2rdf.schema.topic.Topic.register();
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.register();
	}

	/**
	 * Adds imports statements to an ontology and adds
	 * imported subModels to a model.
	 *
	 * Currently, this uses the namespace URI without trailing '#' or ':'
	 * as location.
	 */
	public static void registerImports(Ontology ontology, OntModel ontModel) {
		log.info("Adding import statements to the model");
		OntDocumentManager odm = OntDocumentManager.getInstance();
		log.debug("Adding import http://nlp2rdf.lod2.eu/schema/string/ to the model");
		odm.loadImport(ontModel, "http://nlp2rdf.lod2.eu/schema/string/");
		ontology.addImport(ontModel.createResource("http://nlp2rdf.lod2.eu/schema/string/"));
		log.debug("Adding import file:/home/sebastian/svn/idea/nlp2rdf/ontologies/sso/sso-v1.0.ttl to the model");
		odm.loadImport(ontModel, "file:/home/sebastian/svn/idea/nlp2rdf/ontologies/sso/sso-v1.0.ttl");
		ontology.addImport(ontModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/sso/sso-v1.0.ttl"));
		log.debug("Adding import http://nlp2rdf.lod2.eu/schema/error/ to the model");
		odm.loadImport(ontModel, "http://nlp2rdf.lod2.eu/schema/error/");
		ontology.addImport(ontModel.createResource("http://nlp2rdf.lod2.eu/schema/error/"));
		log.debug("Adding import http://nlp2rdf.lod2.eu/schema/sso/ to the model");
		odm.loadImport(ontModel, "http://nlp2rdf.lod2.eu/schema/sso/");
		ontology.addImport(ontModel.createResource("http://nlp2rdf.lod2.eu/schema/sso/"));
		log.debug("Adding import file:/home/sebastian/svn/idea/nlp2rdf/ontologies/topic/topic-v0.8.ttl to the model");
		odm.loadImport(ontModel, "file:/home/sebastian/svn/idea/nlp2rdf/ontologies/topic/topic-v0.8.ttl");
		ontology.addImport(ontModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/topic/topic-v0.8.ttl"));
		log.debug("Adding import file:/home/sebastian/svn/idea/nlp2rdf/ontologies/string/string-v1.0.ttl to the model");
		odm.loadImport(ontModel, "file:/home/sebastian/svn/idea/nlp2rdf/ontologies/string/string-v1.0.ttl");
		ontology.addImport(ontModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/string/string-v1.0.ttl"));
		log.debug("Adding import http://nlp2rdf.lod2.eu/schema/topic/ to the model");
		odm.loadImport(ontModel, "http://nlp2rdf.lod2.eu/schema/topic/");
		ontology.addImport(ontModel.createResource("http://nlp2rdf.lod2.eu/schema/topic/"));
		log.debug("Adding import http://ns.aksw.org/scms/ to the model");
		odm.loadImport(ontModel, "http://ns.aksw.org/scms/");
		ontology.addImport(ontModel.createResource("http://ns.aksw.org/scms/"));
		log.debug("Adding import http://www.w3.org/XML/1998/namespace to the model");
		odm.loadImport(ontModel, "http://www.w3.org/XML/1998/namespace");
		ontology.addImport(ontModel.createResource("http://www.w3.org/XML/1998/namespace"));
		log.debug("Adding import file:/home/sebastian/svn/idea/nlp2rdf/ontologies/error/error-v0.1.ttl to the model");
		odm.loadImport(ontModel, "file:/home/sebastian/svn/idea/nlp2rdf/ontologies/error/error-v0.1.ttl");
		ontology.addImport(ontModel.createResource("file:/home/sebastian/svn/idea/nlp2rdf/ontologies/error/error-v0.1.ttl"));
	}

	/**
	 * Same as registerImports, but works with the default OntModel
	 * @see Factory#registerImports(Ontology, OntModel)
	 */
	public void registerImports(Ontology ontology) {
		registerImports(ontology, getDefaultModel());
	}
}
