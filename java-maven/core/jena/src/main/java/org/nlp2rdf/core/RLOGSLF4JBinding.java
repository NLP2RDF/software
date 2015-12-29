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

package org.nlp2rdf.core;


import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.RDF;
import org.nlp2rdf.core.vocab.RLOGDatatypeProperties;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.core.vocab.RLOGObjectProperties;
import org.nlp2rdf.core.vocab.RLOGOntClasses;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.UUID;

/**
 * @author kurzum
 */
public class RLOGSLF4JBinding {
    private static int counter = 0;
    public static String defaultlogprefix = "urn:uuid:";
    //public static String defaultlogprefix = "http://nlp2rdf.lod2.eu/instance/log/id_";

    public static Model log(String message, RLOGIndividuals level) {
        return log(defaultlogprefix, message, level, null, null, null);
    }




    /**
     * the last three can be null
     * log( message, level, null, null, null);
     * log(defaultlogprefix, message, level, null, null, null);
     *
     * @param logPrefix
     * @param message
     * @param level
     * @param dc_creator
     * @param resourceURI
     * @param logger
     * @return
     */
    public static OntModel log(String logPrefix, String message, RLOGIndividuals level, String dc_creator, String resourceURI, Logger logger) {
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        model.setNsPrefix("rlog", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/rlog#");

        // urn:uuid:f81d4fae-7dec-11d0-a765-00a0c91e6bf6
//        Resource r = model.createResource(logPrefix + level.name() + "_" + UUID.randomUUID() + "_" + System.currentTimeMillis());
        Resource r = model.createResource(logPrefix + UUID.randomUUID());
        r.addProperty(RDF.type, model.createResource(RLOGOntClasses.Entry.getUri()));
        r.addProperty(RLOGObjectProperties.level.getObjectProperty(model), model.createResource(level.getUri()));
        r.addProperty(RLOGDatatypeProperties.message.getDatatypeProperty(model), model.createLiteral(message));
        XSDDateTime date = new XSDDateTime(Calendar.getInstance());
        r.addProperty(RLOGDatatypeProperties.date.getDatatypeProperty(model), date.toString(), date.getNarrowedDatatype());

        if (dc_creator != null) {
            r.addProperty(DC.creator, model.createLiteral(dc_creator));
        }

        if (resourceURI != null) {
            r.addProperty(RLOGObjectProperties.resource.getObjectProperty(model), model.createResource(resourceURI));
        }

        if (logger != null) {
            r.addProperty(RLOGDatatypeProperties.className.getDatatypeProperty(model), logger.getName());
            switch (level) {
                case TRACE:
                    logger.trace(message);
                    break;
                case DEBUG:
                    logger.debug(message);
                    break;
                case INFO:
                    logger.info(message);
                    break;
                case WARN:
                    logger.warn(message);
                    break;
                case ERROR:
                    logger.error(message);
                    break;
                case FATAL:
                    logger.error(message);
                    break;

            }

        }

        return model;
    }

    private static synchronized int getCounter() {
        return counter++;
    }

}