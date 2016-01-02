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
import org.nlp2rdf.core.RDFUnitValidatorWrapper;
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

        new RDFUnitValidatorWrapper().process(nifParameters.getInputModel(),nifParameters.getInputModel(),nifParameters);
        return nifParameters.getInputModel();
    }

}
