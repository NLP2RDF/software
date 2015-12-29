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

package org.nlp2rdf.implementation.validator;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.aksw.rdfunit.validate.wrappers.RDFUnitStaticValidator;
import org.aksw.rdfunit.validate.wrappers.RDFUnitTestSuiteGenerator;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.webservice.NIFServlet;

import javax.servlet.ServletException;

/**
 * User: hellmann
 * Date: 20.09.13
 */
public class ValidateWS extends NIFServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        RDFUnitStaticValidator.initWrapper(
                new RDFUnitTestSuiteGenerator.Builder()
                        .addLocalResourceOrSchemaURI("nif", "org/uni-leipzig/persistence/nlp2rdf/nif-core/nif-core.ttl", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#")
                        .build()
        );
    }

    public OntModel execute(NIFParameters nifParameters) throws Exception {

        OntModel model = nifParameters.getInputModel();
        OntModel results = ModelFactory.createOntologyModel();
        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor(RDFUnitStaticValidator.class.getCanonicalName()).start();
        int x = 0;
        {

            // Convert model to OntModel
            Model validationResults = RDFUnitStaticValidator.validate(model);
            results.add(validationResults);
            if( nifParameters.getParameterMap() != null && !nifParameters.getParameterMap().containsKey("validationreportonly")) {
                // write results in original model
                model.add(results);
            }




        }
        double lv = mon.stop().getLastValue();
        double avg = lv / x;

        String finalMessage = "Annotated " + x + " nif:Context(s)  in " + lv + " ms. (avg " + avg + ") producing " + model.size() + " triples";
        model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, RDFUnitStaticValidator.class.getCanonicalName(), null, null));
        model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");

        return results;
        /* if (nifParameters.inputWasText()) {
            URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(nifParameters.getUriRecipe(), nifParameters.getContextLength());
            stanfordCoreNLPWrapper.processText(nifParameters.getPrefix(), nifParameters.getInputAsText(), uriGenerator, model);
        } else if (nifParameters.inputWasRDF()) {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "rdf as input is not implemented yet", model);
        } else {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "Could not determine whether input was text or rdf", model);
        }*/
    }

}
