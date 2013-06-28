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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.Arrays;
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
public class NIFParameters {
    private static Logger log = LoggerFactory.getLogger(NIFParameters.class);

    private final OntModel inputModel;
    private final Map<String, String> parameterMap;

    private final String prefix;
    private final String logPrefix;
    private final String uriScheme;
    private final String[] uriSchemeParameters;

    private final String outputFormat;


    public NIFParameters(OntModel inputModel, Map<String, String> parameterMap, String prefix, String logPrefix, String uriScheme, String[] uriSchemeParameters, String outputFormat) {
        this.inputModel = inputModel;
        this.parameterMap = parameterMap;
        this.prefix = prefix;
        this.logPrefix = logPrefix;
        this.uriScheme = uriScheme;
        this.uriSchemeParameters = uriSchemeParameters;
        this.outputFormat = outputFormat;
    }

    /**
     * Factory method
     *
     * @param httpServletRequest
     * @return
     */
    public static NIFParameters getInstance(HttpServletRequest httpServletRequest) {
        String requestUrl = httpServletRequest.getRequestURL().toString();
        try {

            if (!isSet("input", httpServletRequest)) {
                throw new IllegalArgumentException("Missing parameter: 'input' is required and should contain the NIF RDF. ");
            }

            /**Defaults**/
            String prefix = "http://nlp2rdf.lod2.eu/nif/";
            String logPrefix = "http://nlp2rdf.lod2.eu/instance/log/id_";
            String inputFormat = "rdfxml";
            String outputFormat = "rdfxml";
            String uriScheme = "rfc5147string";
            String[] uriSchemeParameters = null;

            //prefix
            if (isSet("prefix", httpServletRequest)) {
                prefix = httpServletRequest.getParameter("prefix");
            }

            //logPrefix
            if (isSet("logPrefix", httpServletRequest)) {
                logPrefix = httpServletRequest.getParameter("logPrefix");
            }

            //inputFormat
            if (isSet("inputFormat", httpServletRequest)) {
                inputFormat = requiredParameter("inputFormat", httpServletRequest, "rdfxml", "turtle", "ntriples");
                inputFormat = inputFormat.replace("rdfxml", "RDF/XML").replace("turtle", "N3").replace("ntriples", "N-TRIPLE");

            }

            //outputFormat
            if (isSet("outputFormat", httpServletRequest)) {
                outputFormat = requiredParameter("outputFormat", httpServletRequest, "rdfxml", "turtle", "json", "ntriples");
                outputFormat = outputFormat.replace("rdfxml", "RDF/XML").replace("turtle", "N3").replace("ntriples", "N-TRIPLE");
            }

            //uriScheme
            if (isSet("uriScheme", httpServletRequest)) {
                uriScheme = requiredParameter("uriScheme", httpServletRequest, "rfc5147string", "contexthashbasedstring", "offsetbasedstring");
            }
            //uriSchemeParameters
            if (isSet("uriSchemeParameters", httpServletRequest)) {
                uriSchemeParameters = httpServletRequest.getParameter("uriSchemeParameters").split("|");
            }


            // Read the model directly from the input
            OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
            ByteArrayInputStream bais = new ByteArrayInputStream(httpServletRequest.getParameter("input").getBytes());
            try {

                inputModel.read(bais, "", inputFormat);
            } catch (JenaException e) {
                throw new InvalidParameterException("Jena could not read the presented NIF in RDF/XML format.");
            }

            NIFParameters nifParameters = new NIFParameters(inputModel, copyParameterMap(httpServletRequest), prefix, logPrefix, uriScheme, uriSchemeParameters, outputFormat);
            log.trace("created NIFParameters instance from " + inputModel.size() + " triples: " + nifParameters.toString());
            return nifParameters;

        } catch (InvalidParameterException ipe) {
            throw new InvalidParameterException(ipe.getMessage() + getDocumentation(requestUrl));
        }
    }


    @Override
    public String toString() {
        return "NIFParameters{" +
                "input=" +
                ", parameterMap=" + parameterMap +
                ", prefix='" + prefix + '\'' +
                ", logPrefix='" + logPrefix + '\'' +
                ", uriScheme='" + uriScheme + '\'' +
                ", uriSchemeParameters=" + (uriSchemeParameters == null ? null : Arrays.asList(uriSchemeParameters)) +
                ", outputFormat='" + outputFormat + '\'' +
                '}';
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


    public OntModel getInputModel() {
        return inputModel;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLogPrefix() {
        return logPrefix;
    }

    public String getUriScheme() {
        return uriScheme;
    }

    public String[] getUriSchemeParameters() {
        return uriSchemeParameters;
    }

    public String getOutputFormat() {
        return outputFormat;
    }



}
