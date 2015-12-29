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

package org.nlp2rdf.implementation.lexo;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import joptsimple.OptionParser;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.owlapi.io.Render;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

//import org.eclipse.jetty.server.Server;

/**
 * User: hellmann
 * Date: 08.09.13
 */
public class LexoCLI {
    public static void main(String[] args) throws IOException {
        OptionParser parser = ParameterParser.getParser(args, "http://cli.nlp2rdf.org/lexo#");
        try {
            Monitor total = MonitorFactory.getTimeMonitor("total").start();
            NIFParameters nifParameters = ParameterParser.CLIbefore(args, parser, "");


            //customize
            OntModel outputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RDFS_INF, ModelFactory.createDefaultModel());
            inputModel.add(nifParameters.getInputModel());
            inputModel.createOntology(nifParameters.getPrefix());
            nifParameters.setInputModel(inputModel);

            Monitor mon = MonitorFactory.getTimeMonitor(LexoCLI.class.getCanonicalName());
            LExO lexo = new LExO();
            int x = 0;
            for (Individual context : inputModel.listIndividuals(NIFOntClasses.Context.getOntClass(inputModel)).toList()) {
                lexo.processText(context, inputModel, outputModel, nifParameters);
                x++;
            }

            String finalMessage = "Annotated " + x + " nif:Context(s)  in " + mon.stop().getTotal() + " ms.  (avg.:" + (mon.getAvg()) + ") producing " + outputModel.size() + " triples";
            outputModel.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, lexo.getClass().getCanonicalName(), null, null));

            if (nifParameters.getOutputFormat().equalsIgnoreCase("owldl")) {

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                outputModel.write(out);
                try {
                    System.out.println(Render.render(new ByteArrayInputStream(out.toByteArray())));
                } catch (OWLOntologyCreationException oce) {
                    oce.printStackTrace();
                }
                System.exit(0);

            }

            NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
            nf.setMinimumFractionDigits(2);
            System.err.println("needed total: " + nf.format(total.getLastValue()));
        } catch (Exception e) {
            ParameterParser.die(parser, e.getMessage());
            // main script
        }
    }
}
