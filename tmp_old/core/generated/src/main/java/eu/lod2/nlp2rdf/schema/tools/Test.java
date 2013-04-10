package eu.lod2.nlp2rdf.schema.tools;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import eu.lod2.nlp2rdf.schema.tools.Factory;

/**
 * Factory
 */
public class Test {

	private static Log log = LogFactory.getLog(Test.class);

	private static java.lang.String namePrefix = "ClassInstance";
	private static int nameCount = 0;

	public static void main(java.lang.String[] args) {
		run();
	}

	private static java.lang.String getNewInstanceName() {
		nameCount++;
		return namePrefix + nameCount;
	}

	private static java.lang.String getNewInstanceURI() {
		java.lang.String localName = getNewInstanceName();
		java.lang.String prefix = "jmodel.getBaseNamespace()";
		return prefix + "#" + localName;
	}

	public static void run() {
		java.lang.String prefix = "jmodel.getBaseNamespace()";

		log.info("Creating an empty ontology");
		OntModel ontModel = ModelFactory.createOntologyModel();
		Ontology ontology = ontModel.createOntology(prefix);

		log.info("Registering custom classes with jena");
		Factory.registerImports(ontology, ontModel);
		Factory.registerCustomClasses();

		log.info("Starting test case run");
		runThing(ontModel);
		runStopWord(ontModel);
		runError(ontModel);
		runWord(ontModel);
		runPhrase(ontModel);
		runOffsetBasedString(ontModel);
		runDocument(ontModel);
		runSentence(ontModel);
		runString(ontModel);
		runTopic(ontModel);
		runContextHashBasedString(ontModel);
		log.info("DONE DONE DONE DONE DONE DONE DONE DONE");
	}

	protected static void runThing(OntModel ontModel) {
		log.info("Testing class Thing");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.Thing.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.Thing.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.Thing clsInstance = eu.lod2.nlp2rdf.schema.Thing.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.Thing> it = eu.lod2.nlp2rdf.schema.Thing.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.Thing) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.Thing cls : eu.lod2.nlp2rdf.schema.Thing.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.Thing> it2 = eu.lod2.nlp2rdf.schema.Thing.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.Thing) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.Thing cls : eu.lod2.nlp2rdf.schema.Thing.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.Thing.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.Thing.delete(uri, ontModel);

	}

	protected static void runStopWord(OntModel ontModel) {
		log.info("Testing class StopWord");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.sso.StopWord.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.StopWord.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.StopWord.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.sso.StopWord.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.sso.StopWord clsInstance = eu.lod2.nlp2rdf.schema.sso.StopWord.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.StopWord> it = eu.lod2.nlp2rdf.schema.sso.StopWord.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.StopWord) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.sso.StopWord cls : eu.lod2.nlp2rdf.schema.sso.StopWord.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.StopWord> it2 = eu.lod2.nlp2rdf.schema.sso.StopWord.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.StopWord) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.sso.StopWord cls : eu.lod2.nlp2rdf.schema.sso.StopWord.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.sso.StopWord.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.sso.StopWord.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.StopWord.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.sso.StopWord instance = eu.lod2.nlp2rdf.schema.sso.StopWord.get(uri, ontModel);

		// test each representation
		log.info("  Testing property sentence of class StopWord");

		log.debug("    Any property sentence exist?");
		log.debug("    -> exists: " + instance.existsSentence());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setSentence(eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getSentence();

		log.debug("    Removing the property instance");
		instance.removeSentence();

		log.info("  Testing property oliaLink of class StopWord");

		log.debug("    Any property oliaLink exist?");
		log.debug("    -> exists: " + instance.existsOliaLink());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel));
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.Thing> itOliaLink = instance.iterateOliaLink();
		eu.lod2.nlp2rdf.schema.Thing instOliaLink = null;
		while (itOliaLink.hasNext()) {
			instOliaLink = (eu.lod2.nlp2rdf.schema.Thing) itOliaLink.next();
			log.debug("    -> instance: " + instOliaLink.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.Thing iinstOliaLink : instance.listOliaLink())
			log.debug("    -> instance: " + iinstOliaLink.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countOliaLink());

		log.debug("    Removing a known property instance");
		instance.removeOliaLink(instOliaLink);

		log.debug("    Removing all property instances");
		instance.removeAllOliaLink();

		log.info("  Testing property previousWord of class StopWord");

		log.debug("    Any property previousWord exist?");
		log.debug("    -> exists: " + instance.existsPreviousWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setPreviousWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getPreviousWord();

		log.debug("    Removing the property instance");
		instance.removePreviousWord();

		log.info("  Testing property nextWord of class StopWord");

		log.debug("    Any property nextWord exist?");
		log.debug("    -> exists: " + instance.existsNextWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setNextWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getNextWord();

		log.debug("    Removing the property instance");
		instance.removeNextWord();

		log.info("  Testing property nextSentenceTrans of class StopWord");

		log.debug("    Any property nextSentenceTrans exist?");
		log.debug("    -> exists: " + instance.existsNextSentenceTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itNextSentenceTrans = instance.iterateNextSentenceTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instNextSentenceTrans = null;
		while (itNextSentenceTrans.hasNext()) {
			instNextSentenceTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itNextSentenceTrans.next();
			log.debug("    -> instance: " + instNextSentenceTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstNextSentenceTrans : instance.listNextSentenceTrans())
			log.debug("    -> instance: " + iinstNextSentenceTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countNextSentenceTrans());

		log.debug("    Removing a known property instance");
		instance.removeNextSentenceTrans(instNextSentenceTrans);

		log.debug("    Removing all property instances");
		instance.removeAllNextSentenceTrans();

		log.info("  Testing property previousWordTrans of class StopWord");

		log.debug("    Any property previousWordTrans exist?");
		log.debug("    -> exists: " + instance.existsPreviousWordTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itPreviousWordTrans = instance.iteratePreviousWordTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instPreviousWordTrans = null;
		while (itPreviousWordTrans.hasNext()) {
			instPreviousWordTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itPreviousWordTrans.next();
			log.debug("    -> instance: " + instPreviousWordTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstPreviousWordTrans : instance.listPreviousWordTrans())
			log.debug("    -> instance: " + iinstPreviousWordTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countPreviousWordTrans());

		log.debug("    Removing a known property instance");
		instance.removePreviousWordTrans(instPreviousWordTrans);

		log.debug("    Removing all property instances");
		instance.removeAllPreviousWordTrans();

		log.info("  Testing property nextWordTrans of class StopWord");

		log.debug("    Any property nextWordTrans exist?");
		log.debug("    -> exists: " + instance.existsNextWordTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itNextWordTrans = instance.iterateNextWordTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instNextWordTrans = null;
		while (itNextWordTrans.hasNext()) {
			instNextWordTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itNextWordTrans.next();
			log.debug("    -> instance: " + instNextWordTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstNextWordTrans : instance.listNextWordTrans())
			log.debug("    -> instance: " + iinstNextWordTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countNextWordTrans());

		log.debug("    Removing a known property instance");
		instance.removeNextWordTrans(instNextWordTrans);

		log.debug("    Removing all property instances");
		instance.removeAllNextWordTrans();

		log.info("  Testing property posTag of class StopWord");

		log.debug("    Any property posTag exist?");
		log.debug("    -> exists: " + instance.existsPosTag());

		log.debug("    Adding property instance");
		instance.addPosTag(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itPosTag = instance.iteratePosTag();
		java.lang.String instPosTag = null;
		while (itPosTag.hasNext()) {
			instPosTag = (java.lang.String) itPosTag.next();
			log.debug("    -> instance: " + instPosTag);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstPosTag : instance.listPosTag())
			log.debug("    -> instance: " + iinstPosTag);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countPosTag());

		log.debug("    Removing a known property instance");
		instance.removePosTag(instPosTag);

		log.debug("    Removing all property instances");
		instance.removeAllPosTag();

		log.info("  Testing property lemma of class StopWord");

		log.debug("    Any property lemma exist?");
		log.debug("    -> exists: " + instance.existsLemma());

		log.debug("    Adding property instance");
		instance.addLemma(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLemma = instance.iterateLemma();
		java.lang.String instLemma = null;
		while (itLemma.hasNext()) {
			instLemma = (java.lang.String) itLemma.next();
			log.debug("    -> instance: " + instLemma);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLemma : instance.listLemma())
			log.debug("    -> instance: " + iinstLemma);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLemma());

		log.debug("    Removing a known property instance");
		instance.removeLemma(instLemma);

		log.debug("    Removing all property instances");
		instance.removeAllLemma();

		log.info("  Testing property stem of class StopWord");

		log.debug("    Any property stem exist?");
		log.debug("    -> exists: " + instance.existsStem());

		log.debug("    Adding property instance");
		instance.addStem(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itStem = instance.iterateStem();
		java.lang.String instStem = null;
		while (itStem.hasNext()) {
			instStem = (java.lang.String) itStem.next();
			log.debug("    -> instance: " + instStem);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstStem : instance.listStem())
			log.debug("    -> instance: " + iinstStem);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countStem());

		log.debug("    Removing a known property instance");
		instance.removeStem(instStem);

		log.debug("    Removing all property instances");
		instance.removeAllStem();

		log.info("  Testing property superString of class StopWord");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class StopWord");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class StopWord");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class StopWord");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class StopWord");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class StopWord");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class StopWord");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class StopWord");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class StopWord");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runError(OntModel ontModel) {
		log.info("Testing class Error");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.error.Error.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.error.Error.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.error.Error.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.error.Error.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.error.Error clsInstance = eu.lod2.nlp2rdf.schema.error.Error.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.error.Error> it = eu.lod2.nlp2rdf.schema.error.Error.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.error.Error) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.error.Error cls : eu.lod2.nlp2rdf.schema.error.Error.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.error.Error> it2 = eu.lod2.nlp2rdf.schema.error.Error.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.error.Error) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.error.Error cls : eu.lod2.nlp2rdf.schema.error.Error.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.error.Error.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.error.Error.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.error.Error.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.error.Error instance = eu.lod2.nlp2rdf.schema.error.Error.get(uri, ontModel);

		// test each representation
		log.info("  Testing property fatal of class Error");

		log.debug("    Any property fatal exist?");
		log.debug("    -> exists: " + instance.existsFatal());

		log.debug("    Adding property instance");
		instance.setFatal(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getBoolean("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getFatal();

		log.debug("    Removing the property instance");
		instance.removeFatal();

		log.info("  Testing property source of class Error");

		log.debug("    Any property source exist?");
		log.debug("    -> exists: " + instance.existsSource());

		log.debug("    Adding property instance");
		instance.addSource(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itSource = instance.iterateSource();
		java.lang.String instSource = null;
		while (itSource.hasNext()) {
			instSource = (java.lang.String) itSource.next();
			log.debug("    -> instance: " + instSource);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstSource : instance.listSource())
			log.debug("    -> instance: " + iinstSource);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSource());

		log.debug("    Removing a known property instance");
		instance.removeSource(instSource);

		log.debug("    Removing all property instances");
		instance.removeAllSource();

		log.info("  Testing property message of class Error");

		log.debug("    Any property message exist?");
		log.debug("    -> exists: " + instance.existsMessage());

		log.debug("    Adding property instance");
		instance.addMessage(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itMessage = instance.iterateMessage();
		java.lang.String instMessage = null;
		while (itMessage.hasNext()) {
			instMessage = (java.lang.String) itMessage.next();
			log.debug("    -> instance: " + instMessage);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstMessage : instance.listMessage())
			log.debug("    -> instance: " + iinstMessage);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countMessage());

		log.debug("    Removing a known property instance");
		instance.removeMessage(instMessage);

		log.debug("    Removing all property instances");
		instance.removeAllMessage();

	}

	protected static void runWord(OntModel ontModel) {
		log.info("Testing class Word");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.sso.Word.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.sso.Word.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.sso.Word clsInstance = eu.lod2.nlp2rdf.schema.sso.Word.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it = eu.lod2.nlp2rdf.schema.sso.Word.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Word) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.sso.Word cls : eu.lod2.nlp2rdf.schema.sso.Word.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> it2 = eu.lod2.nlp2rdf.schema.sso.Word.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Word) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.sso.Word cls : eu.lod2.nlp2rdf.schema.sso.Word.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.sso.Word.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.sso.Word.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.sso.Word instance = eu.lod2.nlp2rdf.schema.sso.Word.get(uri, ontModel);

		// test each representation
		log.info("  Testing property sentence of class Word");

		log.debug("    Any property sentence exist?");
		log.debug("    -> exists: " + instance.existsSentence());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setSentence(eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getSentence();

		log.debug("    Removing the property instance");
		instance.removeSentence();

		log.info("  Testing property oliaLink of class Word");

		log.debug("    Any property oliaLink exist?");
		log.debug("    -> exists: " + instance.existsOliaLink());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel));
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));
		instance.addOliaLink(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.Thing> itOliaLink = instance.iterateOliaLink();
		eu.lod2.nlp2rdf.schema.Thing instOliaLink = null;
		while (itOliaLink.hasNext()) {
			instOliaLink = (eu.lod2.nlp2rdf.schema.Thing) itOliaLink.next();
			log.debug("    -> instance: " + instOliaLink.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.Thing iinstOliaLink : instance.listOliaLink())
			log.debug("    -> instance: " + iinstOliaLink.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countOliaLink());

		log.debug("    Removing a known property instance");
		instance.removeOliaLink(instOliaLink);

		log.debug("    Removing all property instances");
		instance.removeAllOliaLink();

		log.info("  Testing property previousWord of class Word");

		log.debug("    Any property previousWord exist?");
		log.debug("    -> exists: " + instance.existsPreviousWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setPreviousWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getPreviousWord();

		log.debug("    Removing the property instance");
		instance.removePreviousWord();

		log.info("  Testing property nextWord of class Word");

		log.debug("    Any property nextWord exist?");
		log.debug("    -> exists: " + instance.existsNextWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setNextWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getNextWord();

		log.debug("    Removing the property instance");
		instance.removeNextWord();

		log.info("  Testing property nextSentenceTrans of class Word");

		log.debug("    Any property nextSentenceTrans exist?");
		log.debug("    -> exists: " + instance.existsNextSentenceTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addNextSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itNextSentenceTrans = instance.iterateNextSentenceTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instNextSentenceTrans = null;
		while (itNextSentenceTrans.hasNext()) {
			instNextSentenceTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itNextSentenceTrans.next();
			log.debug("    -> instance: " + instNextSentenceTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstNextSentenceTrans : instance.listNextSentenceTrans())
			log.debug("    -> instance: " + iinstNextSentenceTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countNextSentenceTrans());

		log.debug("    Removing a known property instance");
		instance.removeNextSentenceTrans(instNextSentenceTrans);

		log.debug("    Removing all property instances");
		instance.removeAllNextSentenceTrans();

		log.info("  Testing property previousWordTrans of class Word");

		log.debug("    Any property previousWordTrans exist?");
		log.debug("    -> exists: " + instance.existsPreviousWordTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addPreviousWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itPreviousWordTrans = instance.iteratePreviousWordTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instPreviousWordTrans = null;
		while (itPreviousWordTrans.hasNext()) {
			instPreviousWordTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itPreviousWordTrans.next();
			log.debug("    -> instance: " + instPreviousWordTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstPreviousWordTrans : instance.listPreviousWordTrans())
			log.debug("    -> instance: " + iinstPreviousWordTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countPreviousWordTrans());

		log.debug("    Removing a known property instance");
		instance.removePreviousWordTrans(instPreviousWordTrans);

		log.debug("    Removing all property instances");
		instance.removeAllPreviousWordTrans();

		log.info("  Testing property nextWordTrans of class Word");

		log.debug("    Any property nextWordTrans exist?");
		log.debug("    -> exists: " + instance.existsNextWordTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addNextWordTrans(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itNextWordTrans = instance.iterateNextWordTrans();
		eu.lod2.nlp2rdf.schema.sso.Word instNextWordTrans = null;
		while (itNextWordTrans.hasNext()) {
			instNextWordTrans = (eu.lod2.nlp2rdf.schema.sso.Word) itNextWordTrans.next();
			log.debug("    -> instance: " + instNextWordTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstNextWordTrans : instance.listNextWordTrans())
			log.debug("    -> instance: " + iinstNextWordTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countNextWordTrans());

		log.debug("    Removing a known property instance");
		instance.removeNextWordTrans(instNextWordTrans);

		log.debug("    Removing all property instances");
		instance.removeAllNextWordTrans();

		log.info("  Testing property posTag of class Word");

		log.debug("    Any property posTag exist?");
		log.debug("    -> exists: " + instance.existsPosTag());

		log.debug("    Adding property instance");
		instance.addPosTag(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itPosTag = instance.iteratePosTag();
		java.lang.String instPosTag = null;
		while (itPosTag.hasNext()) {
			instPosTag = (java.lang.String) itPosTag.next();
			log.debug("    -> instance: " + instPosTag);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstPosTag : instance.listPosTag())
			log.debug("    -> instance: " + iinstPosTag);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countPosTag());

		log.debug("    Removing a known property instance");
		instance.removePosTag(instPosTag);

		log.debug("    Removing all property instances");
		instance.removeAllPosTag();

		log.info("  Testing property lemma of class Word");

		log.debug("    Any property lemma exist?");
		log.debug("    -> exists: " + instance.existsLemma());

		log.debug("    Adding property instance");
		instance.addLemma(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLemma = instance.iterateLemma();
		java.lang.String instLemma = null;
		while (itLemma.hasNext()) {
			instLemma = (java.lang.String) itLemma.next();
			log.debug("    -> instance: " + instLemma);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLemma : instance.listLemma())
			log.debug("    -> instance: " + iinstLemma);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLemma());

		log.debug("    Removing a known property instance");
		instance.removeLemma(instLemma);

		log.debug("    Removing all property instances");
		instance.removeAllLemma();

		log.info("  Testing property stem of class Word");

		log.debug("    Any property stem exist?");
		log.debug("    -> exists: " + instance.existsStem());

		log.debug("    Adding property instance");
		instance.addStem(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itStem = instance.iterateStem();
		java.lang.String instStem = null;
		while (itStem.hasNext()) {
			instStem = (java.lang.String) itStem.next();
			log.debug("    -> instance: " + instStem);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstStem : instance.listStem())
			log.debug("    -> instance: " + iinstStem);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countStem());

		log.debug("    Removing a known property instance");
		instance.removeStem(instStem);

		log.debug("    Removing all property instances");
		instance.removeAllStem();

		log.info("  Testing property superString of class Word");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class Word");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class Word");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class Word");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class Word");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class Word");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class Word");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class Word");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class Word");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runPhrase(OntModel ontModel) {
		log.info("Testing class Phrase");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.sso.Phrase.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Phrase.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Phrase.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.sso.Phrase.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.sso.Phrase clsInstance = eu.lod2.nlp2rdf.schema.sso.Phrase.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Phrase> it = eu.lod2.nlp2rdf.schema.sso.Phrase.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Phrase) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.sso.Phrase cls : eu.lod2.nlp2rdf.schema.sso.Phrase.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Phrase> it2 = eu.lod2.nlp2rdf.schema.sso.Phrase.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Phrase) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.sso.Phrase cls : eu.lod2.nlp2rdf.schema.sso.Phrase.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.sso.Phrase.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.sso.Phrase.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Phrase.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.sso.Phrase instance = eu.lod2.nlp2rdf.schema.sso.Phrase.get(uri, ontModel);

		// test each representation
		log.info("  Testing property child of class Phrase");

		log.debug("    Any property child exist?");
		log.debug("    -> exists: " + instance.existsChild());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addChild(eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel));
		instance.addChild(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));
		instance.addChild(eu.lod2.nlp2rdf.schema.Thing.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.Thing> itChild = instance.iterateChild();
		eu.lod2.nlp2rdf.schema.Thing instChild = null;
		while (itChild.hasNext()) {
			instChild = (eu.lod2.nlp2rdf.schema.Thing) itChild.next();
			log.debug("    -> instance: " + instChild.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.Thing iinstChild : instance.listChild())
			log.debug("    -> instance: " + iinstChild.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countChild());

		log.debug("    Removing a known property instance");
		instance.removeChild(instChild);

		log.debug("    Removing all property instances");
		instance.removeAllChild();

		log.info("  Testing property superString of class Phrase");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class Phrase");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class Phrase");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class Phrase");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class Phrase");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class Phrase");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class Phrase");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class Phrase");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class Phrase");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runOffsetBasedString(OntModel ontModel) {
		log.info("Testing class OffsetBasedString");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.str.OffsetBasedString.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString clsInstance = eu.lod2.nlp2rdf.schema.str.OffsetBasedString.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.OffsetBasedString> it = eu.lod2.nlp2rdf.schema.str.OffsetBasedString.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.OffsetBasedString) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.str.OffsetBasedString cls : eu.lod2.nlp2rdf.schema.str.OffsetBasedString.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.OffsetBasedString> it2 = eu.lod2.nlp2rdf.schema.str.OffsetBasedString.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.OffsetBasedString) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.str.OffsetBasedString cls : eu.lod2.nlp2rdf.schema.str.OffsetBasedString.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.str.OffsetBasedString.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.str.OffsetBasedString instance = eu.lod2.nlp2rdf.schema.str.OffsetBasedString.get(uri, ontModel);

		// test each representation
		log.info("  Testing property superString of class OffsetBasedString");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class OffsetBasedString");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class OffsetBasedString");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class OffsetBasedString");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class OffsetBasedString");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class OffsetBasedString");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class OffsetBasedString");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class OffsetBasedString");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class OffsetBasedString");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runDocument(OntModel ontModel) {
		log.info("Testing class Document");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.str.Document.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.Document.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.Document.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.str.Document.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.str.Document clsInstance = eu.lod2.nlp2rdf.schema.str.Document.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.Document> it = eu.lod2.nlp2rdf.schema.str.Document.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.Document) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.str.Document cls : eu.lod2.nlp2rdf.schema.str.Document.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.Document> it2 = eu.lod2.nlp2rdf.schema.str.Document.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.Document) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.str.Document cls : eu.lod2.nlp2rdf.schema.str.Document.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.str.Document.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.str.Document.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.Document.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.str.Document instance = eu.lod2.nlp2rdf.schema.str.Document.get(uri, ontModel);

		// test each representation
		log.info("  Testing property sourceUrl of class Document");

		log.debug("    Any property sourceUrl exist?");
		log.debug("    -> exists: " + instance.existsSourceUrl());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setSourceUrl(eu.lod2.nlp2rdf.schema.Thing.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getSourceUrl();

		log.debug("    Removing the property instance");
		instance.removeSourceUrl();

		log.info("  Testing property topic of class Document");

		log.debug("    Any property topic exist?");
		log.debug("    -> exists: " + instance.existsTopic());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addTopic(eu.lod2.nlp2rdf.schema.topic.Topic.create(uri, ontModel));
		instance.addTopic(eu.lod2.nlp2rdf.schema.topic.Topic.create(getNewInstanceURI(), ontModel));
		instance.addTopic(eu.lod2.nlp2rdf.schema.topic.Topic.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> itTopic = instance.iterateTopic();
		eu.lod2.nlp2rdf.schema.topic.Topic instTopic = null;
		while (itTopic.hasNext()) {
			instTopic = (eu.lod2.nlp2rdf.schema.topic.Topic) itTopic.next();
			log.debug("    -> instance: " + instTopic.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.topic.Topic iinstTopic : instance.listTopic())
			log.debug("    -> instance: " + iinstTopic.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countTopic());

		log.debug("    Removing a known property instance");
		instance.removeTopic(instTopic);

		log.debug("    Removing all property instances");
		instance.removeAllTopic();

		log.info("  Testing property dominatingTopic of class Document");

		log.debug("    Any property dominatingTopic exist?");
		log.debug("    -> exists: " + instance.existsDominatingTopic());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setDominatingTopic(eu.lod2.nlp2rdf.schema.topic.Topic.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getDominatingTopic();

		log.debug("    Removing the property instance");
		instance.removeDominatingTopic();

		log.info("  Testing property sourceString of class Document");

		log.debug("    Any property sourceString exist?");
		log.debug("    -> exists: " + instance.existsSourceString());

		log.debug("    Adding property instance");
		instance.setSourceString(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getSourceString();

		log.debug("    Removing the property instance");
		instance.removeSourceString();

		log.info("  Testing property superString of class Document");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class Document");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class Document");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class Document");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class Document");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class Document");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class Document");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class Document");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class Document");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runSentence(OntModel ontModel) {
		log.info("Testing class Sentence");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.sso.Sentence.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.sso.Sentence.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.sso.Sentence clsInstance = eu.lod2.nlp2rdf.schema.sso.Sentence.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> it = eu.lod2.nlp2rdf.schema.sso.Sentence.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Sentence) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.sso.Sentence cls : eu.lod2.nlp2rdf.schema.sso.Sentence.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> it2 = eu.lod2.nlp2rdf.schema.sso.Sentence.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.sso.Sentence) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.sso.Sentence cls : eu.lod2.nlp2rdf.schema.sso.Sentence.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.sso.Sentence.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.sso.Sentence.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.sso.Sentence instance = eu.lod2.nlp2rdf.schema.sso.Sentence.get(uri, ontModel);

		// test each representation
		log.info("  Testing property previousSentence of class Sentence");

		log.debug("    Any property previousSentence exist?");
		log.debug("    -> exists: " + instance.existsPreviousSentence());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setPreviousSentence(eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getPreviousSentence();

		log.debug("    Removing the property instance");
		instance.removePreviousSentence();

		log.info("  Testing property nextSentence of class Sentence");

		log.debug("    Any property nextSentence exist?");
		log.debug("    -> exists: " + instance.existsNextSentence());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setNextSentence(eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getNextSentence();

		log.debug("    Removing the property instance");
		instance.removeNextSentence();

		log.info("  Testing property word of class Sentence");

		log.debug("    Any property word exist?");
		log.debug("    -> exists: " + instance.existsWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));
		instance.addWord(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));
		instance.addWord(eu.lod2.nlp2rdf.schema.sso.Word.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Word> itWord = instance.iterateWord();
		eu.lod2.nlp2rdf.schema.sso.Word instWord = null;
		while (itWord.hasNext()) {
			instWord = (eu.lod2.nlp2rdf.schema.sso.Word) itWord.next();
			log.debug("    -> instance: " + instWord.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Word iinstWord : instance.listWord())
			log.debug("    -> instance: " + iinstWord.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countWord());

		log.debug("    Removing a known property instance");
		instance.removeWord(instWord);

		log.debug("    Removing all property instances");
		instance.removeAllWord();

		log.info("  Testing property firstWord of class Sentence");

		log.debug("    Any property firstWord exist?");
		log.debug("    -> exists: " + instance.existsFirstWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setFirstWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getFirstWord();

		log.debug("    Removing the property instance");
		instance.removeFirstWord();

		log.info("  Testing property lastWord of class Sentence");

		log.debug("    Any property lastWord exist?");
		log.debug("    -> exists: " + instance.existsLastWord());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.setLastWord(eu.lod2.nlp2rdf.schema.sso.Word.create(uri, ontModel));

		log.debug("    Getting property instances");
		instance.getLastWord();

		log.debug("    Removing the property instance");
		instance.removeLastWord();

		log.info("  Testing property previousSentenceTrans of class Sentence");

		log.debug("    Any property previousSentenceTrans exist?");
		log.debug("    -> exists: " + instance.existsPreviousSentenceTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addPreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Sentence.create(uri, ontModel));
		instance.addPreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Sentence.create(getNewInstanceURI(), ontModel));
		instance.addPreviousSentenceTrans(eu.lod2.nlp2rdf.schema.sso.Sentence.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.sso.Sentence> itPreviousSentenceTrans = instance.iteratePreviousSentenceTrans();
		eu.lod2.nlp2rdf.schema.sso.Sentence instPreviousSentenceTrans = null;
		while (itPreviousSentenceTrans.hasNext()) {
			instPreviousSentenceTrans = (eu.lod2.nlp2rdf.schema.sso.Sentence) itPreviousSentenceTrans.next();
			log.debug("    -> instance: " + instPreviousSentenceTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.sso.Sentence iinstPreviousSentenceTrans : instance.listPreviousSentenceTrans())
			log.debug("    -> instance: " + iinstPreviousSentenceTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countPreviousSentenceTrans());

		log.debug("    Removing a known property instance");
		instance.removePreviousSentenceTrans(instPreviousSentenceTrans);

		log.debug("    Removing all property instances");
		instance.removeAllPreviousSentenceTrans();

		log.info("  Testing property superString of class Sentence");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class Sentence");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class Sentence");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class Sentence");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class Sentence");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class Sentence");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class Sentence");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class Sentence");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class Sentence");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runString(OntModel ontModel) {
		log.info("Testing class String");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.str.String.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.str.String.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.str.String clsInstance = eu.lod2.nlp2rdf.schema.str.String.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it = eu.lod2.nlp2rdf.schema.str.String.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.String) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.str.String cls : eu.lod2.nlp2rdf.schema.str.String.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> it2 = eu.lod2.nlp2rdf.schema.str.String.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.String) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.str.String cls : eu.lod2.nlp2rdf.schema.str.String.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.str.String.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.str.String.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.str.String instance = eu.lod2.nlp2rdf.schema.str.String.get(uri, ontModel);

		// test each representation
		log.info("  Testing property superString of class String");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class String");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class String");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class String");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class String");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class String");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class String");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class String");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class String");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}

	protected static void runTopic(OntModel ontModel) {
		log.info("Testing class Topic");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.topic.Topic.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.topic.Topic.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.topic.Topic.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.topic.Topic.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.topic.Topic clsInstance = eu.lod2.nlp2rdf.schema.topic.Topic.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> it = eu.lod2.nlp2rdf.schema.topic.Topic.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.topic.Topic) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.topic.Topic cls : eu.lod2.nlp2rdf.schema.topic.Topic.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.topic.Topic> it2 = eu.lod2.nlp2rdf.schema.topic.Topic.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.topic.Topic) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.topic.Topic cls : eu.lod2.nlp2rdf.schema.topic.Topic.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.topic.Topic.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.topic.Topic.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.topic.Topic.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.topic.Topic instance = eu.lod2.nlp2rdf.schema.topic.Topic.get(uri, ontModel);

		// test each representation
		log.info("  Testing property occursIn of class Topic");

		log.debug("    Any property occursIn exist?");
		log.debug("    -> exists: " + instance.existsOccursIn());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addOccursIn(eu.lod2.nlp2rdf.schema.str.Document.create(uri, ontModel));
		instance.addOccursIn(eu.lod2.nlp2rdf.schema.str.Document.create(getNewInstanceURI(), ontModel));
		instance.addOccursIn(eu.lod2.nlp2rdf.schema.str.Document.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.Document> itOccursIn = instance.iterateOccursIn();
		eu.lod2.nlp2rdf.schema.str.Document instOccursIn = null;
		while (itOccursIn.hasNext()) {
			instOccursIn = (eu.lod2.nlp2rdf.schema.str.Document) itOccursIn.next();
			log.debug("    -> instance: " + instOccursIn.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.Document iinstOccursIn : instance.listOccursIn())
			log.debug("    -> instance: " + iinstOccursIn.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countOccursIn());

		log.debug("    Removing a known property instance");
		instance.removeOccursIn(instOccursIn);

		log.debug("    Removing all property instances");
		instance.removeAllOccursIn();

		log.info("  Testing property characterticLemma of class Topic");

		log.debug("    Any property characterticLemma exist?");
		log.debug("    -> exists: " + instance.existsCharacterticLemma());

		log.debug("    Adding property instance");
		instance.addCharacterticLemma(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itCharacterticLemma = instance.iterateCharacterticLemma();
		java.lang.String instCharacterticLemma = null;
		while (itCharacterticLemma.hasNext()) {
			instCharacterticLemma = (java.lang.String) itCharacterticLemma.next();
			log.debug("    -> instance: " + instCharacterticLemma);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstCharacterticLemma : instance.listCharacterticLemma())
			log.debug("    -> instance: " + iinstCharacterticLemma);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countCharacterticLemma());

		log.debug("    Removing a known property instance");
		instance.removeCharacterticLemma(instCharacterticLemma);

		log.debug("    Removing all property instances");
		instance.removeAllCharacterticLemma();

	}

	protected static void runContextHashBasedString(OntModel ontModel) {
		log.info("Testing class ContextHashBasedString");

		// create, create anonymous, exists, delete, list
		log.debug("  Creating anonymous class instance");
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.create(ontModel);

		log.debug("  Creating two named class instance");
		java.lang.String uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.create(uri, ontModel);
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.create(uri, ontModel);

		log.debug("  Checking for existance of class instance");
		boolean exists = eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.exists(uri, ontModel);
		log.debug("  -> exists: " + exists);

		log.debug("  Fetching known instance");
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString clsInstance = eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.get(uri, ontModel);
		log.debug("  -> instance: " + clsInstance.getLocalName());

		log.debug("  Iterate over all class instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.ContextHashBasedString> it = eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.iterate(ontModel);
		while (it.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.ContextHashBasedString) it.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances and ");
		for (eu.lod2.nlp2rdf.schema.str.ContextHashBasedString cls : eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.list(ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Iterate over all class instances and subclass instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.ContextHashBasedString> it2 = eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.iterate(false, ontModel);
		while (it2.hasNext()) {
			clsInstance = (eu.lod2.nlp2rdf.schema.str.ContextHashBasedString) it2.next();
			log.debug("  -> instance: " + clsInstance.getLocalName());
		}

		log.debug("  List all class instances");
		for (eu.lod2.nlp2rdf.schema.str.ContextHashBasedString cls : eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.list(false, ontModel))
			log.debug("  -> instance: " + cls.getLocalName());

		log.debug("  Counting class instances");
		log.debug("  -> count: " + eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.count(ontModel));

		log.debug("  Deleting a named class instance");
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.delete(uri, ontModel);

		// class instance for property tests
		uri = getNewInstanceURI();
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.create(uri, ontModel);
		eu.lod2.nlp2rdf.schema.str.ContextHashBasedString instance = eu.lod2.nlp2rdf.schema.str.ContextHashBasedString.get(uri, ontModel);

		// test each representation
		log.info("  Testing property superString of class ContextHashBasedString");

		log.debug("    Any property superString exist?");
		log.debug("    -> exists: " + instance.existsSuperString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperString = instance.iterateSuperString();
		eu.lod2.nlp2rdf.schema.str.String instSuperString = null;
		while (itSuperString.hasNext()) {
			instSuperString = (eu.lod2.nlp2rdf.schema.str.String) itSuperString.next();
			log.debug("    -> instance: " + instSuperString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperString : instance.listSuperString())
			log.debug("    -> instance: " + iinstSuperString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperString());

		log.debug("    Removing a known property instance");
		instance.removeSuperString(instSuperString);

		log.debug("    Removing all property instances");
		instance.removeAllSuperString();

		log.info("  Testing property subString of class ContextHashBasedString");

		log.debug("    Any property subString exist?");
		log.debug("    -> exists: " + instance.existsSubString());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubString(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubString = instance.iterateSubString();
		eu.lod2.nlp2rdf.schema.str.String instSubString = null;
		while (itSubString.hasNext()) {
			instSubString = (eu.lod2.nlp2rdf.schema.str.String) itSubString.next();
			log.debug("    -> instance: " + instSubString.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubString : instance.listSubString())
			log.debug("    -> instance: " + iinstSubString.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubString());

		log.debug("    Removing a known property instance");
		instance.removeSubString(instSubString);

		log.debug("    Removing all property instances");
		instance.removeAllSubString();

		log.info("  Testing property superStringTrans of class ContextHashBasedString");

		log.debug("    Any property superStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSuperStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSuperStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSuperStringTrans = instance.iterateSuperStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSuperStringTrans = null;
		while (itSuperStringTrans.hasNext()) {
			instSuperStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSuperStringTrans.next();
			log.debug("    -> instance: " + instSuperStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSuperStringTrans : instance.listSuperStringTrans())
			log.debug("    -> instance: " + iinstSuperStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSuperStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSuperStringTrans(instSuperStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSuperStringTrans();

		log.info("  Testing property subStringTrans of class ContextHashBasedString");

		log.debug("    Any property subStringTrans exist?");
		log.debug("    -> exists: " + instance.existsSubStringTrans());

		log.debug("    Adding property instance");
		uri = getNewInstanceURI();
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(uri, ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));
		instance.addSubStringTrans(eu.lod2.nlp2rdf.schema.str.String.create(getNewInstanceURI(), ontModel));

		log.debug("    Iterate over all property instances");
		Iterator<eu.lod2.nlp2rdf.schema.str.String> itSubStringTrans = instance.iterateSubStringTrans();
		eu.lod2.nlp2rdf.schema.str.String instSubStringTrans = null;
		while (itSubStringTrans.hasNext()) {
			instSubStringTrans = (eu.lod2.nlp2rdf.schema.str.String) itSubStringTrans.next();
			log.debug("    -> instance: " + instSubStringTrans.getLocalName());
		}

		log.debug("    List all property values");
		for (eu.lod2.nlp2rdf.schema.str.String iinstSubStringTrans : instance.listSubStringTrans())
			log.debug("    -> instance: " + iinstSubStringTrans.getLocalName());

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countSubStringTrans());

		log.debug("    Removing a known property instance");
		instance.removeSubStringTrans(instSubStringTrans);

		log.debug("    Removing all property instances");
		instance.removeAllSubStringTrans();

		log.info("  Testing property anchorOf of class ContextHashBasedString");

		log.debug("    Any property anchorOf exist?");
		log.debug("    -> exists: " + instance.existsAnchorOf());

		log.debug("    Adding property instance");
		instance.setAnchorOf(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("$rangeeUri"));

		log.debug("    Getting property instances");
		instance.getAnchorOf();

		log.debug("    Removing the property instance");
		instance.removeAnchorOf();

		log.info("  Testing property endIndex of class ContextHashBasedString");

		log.debug("    Any property endIndex exist?");
		log.debug("    -> exists: " + instance.existsEndIndex());

		log.debug("    Adding property instance");
		instance.addEndIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itEndIndex = instance.iterateEndIndex();
		java.lang.String instEndIndex = null;
		while (itEndIndex.hasNext()) {
			instEndIndex = (java.lang.String) itEndIndex.next();
			log.debug("    -> instance: " + instEndIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstEndIndex : instance.listEndIndex())
			log.debug("    -> instance: " + iinstEndIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countEndIndex());

		log.debug("    Removing a known property instance");
		instance.removeEndIndex(instEndIndex);

		log.debug("    Removing all property instances");
		instance.removeAllEndIndex();

		log.info("  Testing property beginIndex of class ContextHashBasedString");

		log.debug("    Any property beginIndex exist?");
		log.debug("    -> exists: " + instance.existsBeginIndex());

		log.debug("    Adding property instance");
		instance.addBeginIndex(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itBeginIndex = instance.iterateBeginIndex();
		java.lang.String instBeginIndex = null;
		while (itBeginIndex.hasNext()) {
			instBeginIndex = (java.lang.String) itBeginIndex.next();
			log.debug("    -> instance: " + instBeginIndex);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstBeginIndex : instance.listBeginIndex())
			log.debug("    -> instance: " + iinstBeginIndex);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countBeginIndex());

		log.debug("    Removing a known property instance");
		instance.removeBeginIndex(instBeginIndex);

		log.debug("    Removing all property instances");
		instance.removeAllBeginIndex();

		log.info("  Testing property rightContext of class ContextHashBasedString");

		log.debug("    Any property rightContext exist?");
		log.debug("    -> exists: " + instance.existsRightContext());

		log.debug("    Adding property instance");
		instance.addRightContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itRightContext = instance.iterateRightContext();
		java.lang.String instRightContext = null;
		while (itRightContext.hasNext()) {
			instRightContext = (java.lang.String) itRightContext.next();
			log.debug("    -> instance: " + instRightContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstRightContext : instance.listRightContext())
			log.debug("    -> instance: " + iinstRightContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countRightContext());

		log.debug("    Removing a known property instance");
		instance.removeRightContext(instRightContext);

		log.debug("    Removing all property instances");
		instance.removeAllRightContext();

		log.info("  Testing property leftContext of class ContextHashBasedString");

		log.debug("    Any property leftContext exist?");
		log.debug("    -> exists: " + instance.existsLeftContext());

		log.debug("    Adding property instance");
		instance.addLeftContext(nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString("http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral"));

		log.debug("    Iterate over all property values");
		Iterator<java.lang.String> itLeftContext = instance.iterateLeftContext();
		java.lang.String instLeftContext = null;
		while (itLeftContext.hasNext()) {
			instLeftContext = (java.lang.String) itLeftContext.next();
			log.debug("    -> instance: " + instLeftContext);
		}

		log.debug("    List all property values");
		for (java.lang.String iinstLeftContext : instance.listLeftContext())
			log.debug("    -> instance: " + iinstLeftContext);

		log.debug("    Count property values");
		log.debug("    -> count: " + instance.countLeftContext());

		log.debug("    Removing a known property instance");
		instance.removeLeftContext(instLeftContext);

		log.debug("    Removing all property instances");
		instance.removeAllLeftContext();

	}
}
