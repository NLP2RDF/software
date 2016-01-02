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

package org.nlp2rdf.implementation.stanfordcorenlp;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.webservice.NIFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

/**
 * User: hellmann
 * Date: 20.09.13
 */
public class StanfordWS extends NIFServlet {
    private static Logger log = LoggerFactory.getLogger(StanfordWS.class);


    @Override
    public void init() throws ServletException {
        log.debug("Calling NIFServlet");
        super.init();
    }

    private final StanfordWrapper stanfordWrapper;

    public StanfordWS() {
        log.debug("Creating stanford wrapper.");
        this.stanfordWrapper = new StanfordWrapper();
    }

    public OntModel execute(NIFParameters nifParameters) throws Exception {

        log.debug("Creating new model from input.");
        OntModel model = nifParameters.getInputModel();
        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor(stanfordWrapper.getClass().getCanonicalName()).start();
        int x = 0;
        log.debug("Iterating each context...");
        ExtendedIterator<Individual> eit = model.listIndividuals(NIFOntClasses.Context.getOntClass(model));
        for (; eit.hasNext(); ) {
            stanfordWrapper.process(eit.next(), model, model, nifParameters);
            x++;
        }

        log.debug("... Done!");
        double lv = mon.stop().getLastValue();
        double avg = lv / x;

        String finalMessage = "Annotated " + x + " nif:Context(s)  in " + lv + " ms. (avg " + avg + ") producing " +
                model.size() + " triples";
        model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, stanfordWrapper.getClass().getCanonicalName(), null, null));
        model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");

        return model;
        /* if (nifParameters.inputWasText()) {
            URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(nifParameters.getUriRecipe(), nifParameters.getContextLength());
            stanfordCoreNLPWrapper.process(nifParameters.getPrefix(), nifParameters.getInputAsText(), uriGenerator, model);
        } else if (nifParameters.inputWasRDF()) {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "rdf as input is not implemented yet", model);
        } else {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "Could not determine whether input was text or rdf", model);
        }*/
    }

}
