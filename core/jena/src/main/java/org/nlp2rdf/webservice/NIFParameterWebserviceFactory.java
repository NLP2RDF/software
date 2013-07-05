/***************************************************************************/
/*  Copyright (C) 2010-2011, Sebastian Hellmann                            */
/*  Note: If you need parts of NLP2RDF in another licence due to licence   */
/*  incompatibility, please mail hellmann@informatik.uni-leipzig.de        */
/*                                                                         */
/*  This file is part of NLP2RDF.                                          */
/*                                                                         */
/*  NLP2RDF is free software; you can redistribute it and/or modify        */
/*  it under the terms of the GNU General Public License as published by   */
/*  the Free Software Foundation; either version 3 of the License, or      */
/*  (at your option) any later version.                                    */
/*                                                                         */
/*  NLP2RDF is distributed in the hope that it will be useful,             */
/*  but WITHOUT ANY WARRANTY; without even the implied warranty of         */
/*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the           */
/*  GNU General Public License for more details.                           */
/*                                                                         */
/*  You should have received a copy of the GNU General Public License      */
/*  along with this program. If not, see <http://www.gnu.org/licenses/>.   */
/***************************************************************************/

package org.nlp2rdf.webservice;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.shared.JenaException;
import org.apache.commons.lang.StringUtils;
import org.nlp2rdf.core.Format;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.Text2RDF;
import org.nlp2rdf.core.urischemes.ContextHashBasedString;
import org.nlp2rdf.core.urischemes.OffsetBasedString;
import org.nlp2rdf.core.urischemes.RFC5147String;
import org.nlp2rdf.core.urischemes.URIScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Sebastian Hellmann
 * See    http://nlp2rdf.org/nif-1-0#toc-parameters
 * A simple wrapper for the common options in NIF Services
 * Use the following to retrieve the input variable:
 * if (nifParameters.inputWasText()) {
 * String text =  nifParameters.getInputAsText() ;
 * }else {
 * OntModel model = getInputAsOntModel();
 * }
 */
public class NIFParameterWebserviceFactory {
    private static Logger log = LoggerFactory.getLogger(NIFParameterWebserviceFactory.class);


    /**
     * Factory method
     *
     * @param httpServletRequest
     * @return
     */
    public static NIFParameters getInstance(HttpServletRequest httpServletRequest) {
        String requestUrl = httpServletRequest.getRequestURL().toString();
        try {

            /***********
             * Defaults
             ***********/
            String inputType = "rdf";
            String inputFormat = "RDF/XML";
            OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            String prefix = "http://nlp2rdf.lod2.eu/nif/";
            String logPrefix = "http://nlp2rdf.lod2.eu/instance/log/id_";
            String outputFormat = "RDF/XML";
            URIScheme uriScheme = new RFC5147String();
            String[] uriSchemeParameters = null;


            /************
             * Get input type
             ************/
            if (!isSet("inputtype", httpServletRequest)) {
                inputType = requiredParameter("inputtype", httpServletRequest, "rdf", "text", "url");
            }
            /************
             * Read RDF input
             ************/
            if (inputType.equalsIgnoreCase("rdf")) {

                //inputFormat
                if (isSet("inputFormat", httpServletRequest)) {
                    inputFormat = requiredParameter("inputFormat", httpServletRequest, "rdfxml", "turtle", "ntriples");
                    inputFormat = Format.toJena(inputFormat);
                }

                if (!isSet("input", httpServletRequest)) {
                    throw new IllegalArgumentException("Missing parameter: 'input' is required and should contain NIF in RDF format. To fix either post NIF-RDF or add , because of 'inputtype=text', input='your text' ");
                }

                // Read the model directly from the input
                ByteArrayInputStream bais = new ByteArrayInputStream(httpServletRequest.getParameter("input").getBytes());
                try {
                    inputModel.read(bais, "", inputFormat);
                } catch (JenaException e) {
                    throw new InvalidParameterException("Jena could not read the presented NIF in RDF/XML format." + e.getMessage());
                }

            }

            /******
             * text input
             *****/
            //prefix
            if (isSet("prefix", httpServletRequest)) {
                prefix = httpServletRequest.getParameter("prefix");
            }

            //uriSchemeParameters
            if (isSet("uriSchemeParameters", httpServletRequest)) {
                uriSchemeParameters = httpServletRequest.getParameter("uriSchemeParameters").split("|");
            }
            //uriScheme
            if (isSet("uriScheme", httpServletRequest)) {

                String us = requiredParameter("uriScheme", httpServletRequest,
                        "rfc5147string", "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#RFC5147String",
                        "contexthashbasedstring",
                        "offsetbasedstring");
                if (us.equalsIgnoreCase("rfc5147string") || us.equalsIgnoreCase("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#RFC5147String")) {
                    uriScheme = new RFC5147String();
                } else if (us.equalsIgnoreCase("contexthashbasedstring") || us.equalsIgnoreCase("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#ContextHashBasedString")) {
                    uriScheme = new ContextHashBasedString();
                } else if (us.equalsIgnoreCase("offsetbasedstring") || us.equalsIgnoreCase("http://persistence.uni-leipzig.org/nlp2rdf/ontologies/nif-core#OffsetBasedString")) {
                    uriScheme = new OffsetBasedString();
                }

            }


            if (inputType.equalsIgnoreCase("text")) {
                String text = httpServletRequest.getParameter("text");
                new Text2RDF().createContextIndividual(prefix, text, uriScheme, inputModel);

            }

            /******
             * url input
             *****/
            if (inputType.equalsIgnoreCase("url")) {
                throw new InvalidParameterException(" Parameter 'inputtype=url' is not implemented yet.");
            }

            //logPrefix
            if (isSet("logPrefix", httpServletRequest)) {
                logPrefix = httpServletRequest.getParameter("logPrefix");
            }
            //outputFormat
            if (isSet("outputFormat", httpServletRequest)) {
                outputFormat = requiredParameter("outputFormat", httpServletRequest, "rdfxml", "turtle", "json", "ntriples");
                outputFormat = outputFormat.replace("rdfxml", "RDF/XML").replace("turtle", "N3").replace("ntriples", "N-TRIPLE");
            }

            NIFParameters nifParameters = new NIFParameters(inputModel, copyParameterMap(httpServletRequest), prefix, logPrefix, uriScheme, uriSchemeParameters, outputFormat);
            log.trace("created NIFParameters instance from " + inputModel.size() + " triples: " + nifParameters.toString());
            return nifParameters;

        } catch (InvalidParameterException ipe) {
            throw new InvalidParameterException(ipe.getMessage() + getDocumentation(requestUrl));
        }
    }


    public static String requiredParameter(String parameterName, HttpServletRequest hsr) {

        if (!isSet(parameterName, hsr)) {
            throw new IllegalArgumentException("Missing parameter: " + parameterName + " is required. ");
        }
        return hsr.getParameter(parameterName);
    }

    public static String requiredParameter(String parameterName, HttpServletRequest hsr, String... requiredValues) {
        String value = requiredParameter(parameterName, hsr);
        if (!oneOf(value, requiredValues)) {
            throw new InvalidParameterException("Wrong value for parameter " + parameterName + ", value was: " + value + ", but must be one of ( " + StringUtils.join(requiredValues, ", ") + " ) ");
        }
        return value;
    }


    public static String getDocumentation(String serviceUrl) {
        String doc = "";
        try {
            doc = "\nExample1: \n " + serviceUrl + "?input=" + URLEncoder.encode("That's a lot of nuts! That'll be four bucks, baby! You want fries with that? ", "UTF-8") + "&input-type=text";
            doc += "\nExample2 for sending rdf/xml: \n use \"input-type=nif-owl\" and send rdf/xml via POST, to change the output style use the \"format\" parameter";

        } catch (Exception e) {
            log.error("", e);
        }
        return doc;
    }

    public static boolean oneOf(String value, String... possibleValues) {
        for (String s : possibleValues) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSet(String parameterName, HttpServletRequest hsr) {
        boolean retVal = hsr.getParameterValues(parameterName) != null && hsr.getParameterValues(parameterName).length == 1 && hsr.getParameter(parameterName).length() > 0;
        if (log.isTraceEnabled()) {
            log.trace("Parameter " + parameterName + " isSet: " + retVal + " with value: " + hsr.getParameter(parameterName) + ")");
        }
        return retVal;
    }

    public static Map<String, String> copyParameterMap(HttpServletRequest httpServletRequest) {
        Map<String, String> ret = new HashMap<String, String>();
        for (Object key : httpServletRequest.getParameterMap().keySet()) {
            ret.put((String) key, httpServletRequest.getParameter((String) key));
        }
        return ret;
    }
}
