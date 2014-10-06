package org.nlp2rdf.implementation.spotlight;

import static java.util.Arrays.asList;

import javax.servlet.ServletException;

import joptsimple.OptionParser;

import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.NIFNamespaces;
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

public class SpotlightWS extends NIFServlet{
	private static Logger log = LoggerFactory.getLogger(SpotlightWS.class);

	@Override
	public void init() throws ServletException {
		log.debug("Calling NIFServlet");
		OptionParser parser = ParameterParser.getParser();
		 parser.acceptsAll(asList("confidence"), "The confidence level (0 to 1)").withRequiredArg().ofType(String.class).describedAs("confidence");
	     
		super.init();
	}

	public OntModel execute(NIFParameters nifParameters) throws Exception {
		log.debug("Creating new model from input.");

		// some stats
		Monitor mon = MonitorFactory.getTimeMonitor(
				this.getClass().getCanonicalName()).start();

		log.debug("Iterating each individual...");
		if(!nifParameters.getOptions().has("confidence")) {
			log.error("No confidence level specified, please specify the confidence level via -confidence");
			return null;
		}

		// customize
		OntModel model = nifParameters.getInputModel();

		int x = 0;
		for (ExtendedIterator<Individual> it = model
				.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it
				.hasNext();) {
			Individual context = it.next();
			SpotlightWrapper s = new SpotlightWrapper();
			s.processText(context, model, model, nifParameters);
			x++;
		}
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		NIFNamespaces.addNifPrefix(model);
		model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
		model.setNsPrefix("p", nifParameters.getPrefix());
		log.debug("... Done!");

		double lv = mon.stop().getLastValue();
		double avg = lv / x;

		log.debug("Anotating stats...");
		String finalMessage = "Annotated " + x + " nif:Context(s)  in " + lv
				+ " ms. (avg " + avg + ") producing " + model.size()
				+ " triples";
		model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(),
				finalMessage, RLOGIndividuals.DEBUG, this.getClass()
						.getCanonicalName(), null, null));
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		log.debug("Done!");
		return model;
	}
}
