/******************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                               */
/*                                                                            */
/*  Licensed under the Apache License, Version 2.0 (the "License");           */
/*  you may not use this file except in compliance with the License.          */
/*  You may obtain a copy of the License at                                   */
/*                                                                            */
/*      http://www.apache.org/licenses/LICENSE-2.0                            */
/*                                                                            */
/*  Unless required by applicable law or agreed to in writing, software       */
/*  distributed under the License is distributed on an "AS IS" BASIS,         */
/*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  */
/*  See the License for the specific language governing permissions and       */
/*  limitations under the License.                                            */
/******************************************************************************/

package org.nlp2rdf.implementation.opennlp;

import static java.util.Arrays.asList;

import javax.servlet.ServletException;

import joptsimple.OptionParser;

import org.apache.xerces.dom.ParentNode;
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

public class OpenNLPWS extends NIFServlet {

	private static Logger log = LoggerFactory.getLogger(OpenNLPWS.class);

	@Override
	public void init() throws ServletException {
		log.debug("Calling NIFServlet");
		OptionParser parser = ParameterParser.getParser();
		parser.acceptsAll(asList("modelFolder"),
				"The folder containing the NLP models to be used")
				.withRequiredArg().ofType(String.class).describedAs("folder");
		parser.acceptsAll(asList("language"),
				"A string denoting the language of the model used, usually 2 characters")
				.withRequiredArg().ofType(String.class)
				.describedAs("Language string");

		super.init();
	}

	public OntModel execute(NIFParameters nifParameters) throws Exception {
		log.debug("Creating new model from input.");

		// some stats
		Monitor mon = MonitorFactory.getTimeMonitor(
				this.getClass().getCanonicalName()).start();

		log.debug("Iterating each individual...");

		if (!nifParameters.getOptions().has("modelFolder")) {
			this.log.error("No model folder specified, please specify OpenNLP model location via -modelFolder");

			return null;
		}

		// customize
		OntModel model = nifParameters.getInputModel();

		int x = 0;
		for (ExtendedIterator<Individual> it = model
				.listIndividuals(NIFOntClasses.Context.getOntClass(model)); it
				.hasNext();) {
			Individual context = it.next();
			OpenNLPWrapper w = new OpenNLPWrapper(context, nifParameters, model);
			w.processText(context, nifParameters);
			x++;
		}
		model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");
		NIFNamespaces.addNifPrefix(model);
		model.setNsPrefix("olia", "http://purl.org/olia/olia.owl#");
		model.setNsPrefix("p", nifParameters.getPrefix());
		log.debug("... Done!");

		double lv = mon.stop().getLastValue();
		double avg = lv / x;

		log.debug("Annotating stats...");
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
