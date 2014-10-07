package org.nlp2rdf.implementation.stanfordcorenlp;

import javax.servlet.ServletException;

import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.webservice.NIFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

/**
 * NIF Stanford webservice running without RDFUnit
 * User: Ciro Date: 11.09.14
 */
public class StanfordWSNoValidation extends NIFServlet {
	private static Logger log = LoggerFactory.getLogger(StanfordWSNoValidation.class);

	@Override
	public void init() throws ServletException {
		log.debug("Calling NIFServlet");
		super.init();
	}

	private final StanfordWrapper stanfordWrapper;

	public StanfordWSNoValidation() {
		log.debug("Creating stanford wrapper.");
		this.stanfordWrapper = new StanfordWrapper();
	}

	public OntModel execute(NIFParameters nifParameters) throws Exception {
		log.debug("Creating new model from input.");
		OntModel model = nifParameters.getInputModel();
		
		// some stats
		Monitor mon = MonitorFactory.getTimeMonitor(
				stanfordWrapper.getClass().getCanonicalName()).start();
		int x = 0;

		log.debug("Iterating each individual...");
		ExtendedIterator<Individual> eit = model
				.listIndividuals(NIFOntClasses.Context.getOntClass(model));

		for (; eit.hasNext();) {
			Individual context = eit.next();
			stanfordWrapper.processText(context, model, model, nifParameters);
			x++;
		}
		log.debug("... Done!");
		
		double lv = mon.stop().getLastValue();
		double avg = lv / x;

		log.debug("Anotating stats...");
		String finalMessage = "Annotated " + x + " nif:Context(s)  in " + lv
				+ " ms. (avg " + avg + ") producing " + model.size()
				+ " triples";
		model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(),
				finalMessage, RLOGIndividuals.DEBUG, stanfordWrapper.getClass()
						.getCanonicalName(), null, null));
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		log.debug("Done!");
		return model;
	}
}
