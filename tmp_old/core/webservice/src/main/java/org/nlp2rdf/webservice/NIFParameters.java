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
import eu.lod2.nlp2rdf.schema.str.Document;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
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

    //can be String or OntModel
    private final Object input;
    private final Map<String, String> parameterMap;

    private final String prefix;
    private final String uriRecipe;
    private final int contextLength;

    private final String format;

    //not in nif 1.0 diff or full
    private final String output;

    public NIFParameters(Object input, Map<String, String> parameterMap, String prefix, String uriRecipe, int contextLength, String format, String output) {
        this.input = input;
        this.parameterMap = parameterMap;
        this.prefix = prefix;
        this.uriRecipe = uriRecipe;
        this.contextLength = contextLength;
        this.format = format;
        this.output = output;
    }

    /**
     * Tests whether the input was text in opposition to inputWasRDF()
     *
     * @return true if the input is an instance of String
     */
    public boolean inputWasText() {
        return input instanceof String;
    }

    /**
     * Tests whether the input was RDF in opposition to inputWasText()
     *
     * @return true if the input is an instance of OntModel
     */
    public boolean inputWasRDF() {
        return input instanceof OntModel;
    }

    /**
     * Casts the input var to String
     *
     * @return the text of the input variable
     * @throws ClassCastException
     */
    public String getInputAsText() throws ClassCastException {
        return (String) input;
    }

    /**
     * Casts the input var to OntModel
     *
     * @return the OntModel  from the input variable
     * @throws ClassCastException
     */
    public OntModel getInputAsOntModel() throws ClassCastException {
        return (OntModel) input;
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

            //required Parameter input-type
            String input_type = requiredParameter("input-type", httpServletRequest, "text", "nif-owl");

            if (!isSet("input", httpServletRequest)) {
                throw new IllegalArgumentException("Missing parameter: input is required. ");
            }
            //optional parameters
            //note that nif=true is intentionally left out here, because it would be too late
            String prefix = "http://nlp2rdf.lod2.eu/nif/";
            String format = "rdfxml";
            String urirecipe = "offset";
            int contextLength = 10;
            //this is not in the nif 1.0 spec
            String output = "full";

            //prefix
            if (isSet("prefix", httpServletRequest)) {
                prefix = httpServletRequest.getParameter("prefix");
            }

            //format
            if (isSet("format", httpServletRequest)) {
                format = requiredParameter("format", httpServletRequest, "rdfxml", "turtle", "json", "ntriples", "n3");
            }
            //urirecipe
            if (isSet("urirecipe", httpServletRequest)) {
                urirecipe = requiredParameter("urirecipe", httpServletRequest, "offset", "context-hash");
            }
            //contextLength
            if (isSet("context-length", httpServletRequest)) {
                contextLength = Integer.parseInt(httpServletRequest.getParameter("context-length"));
            }
            //output
            if (isSet("output", httpServletRequest)) {
                output = requiredParameter("output", httpServletRequest, "full", "diff");
            }

            Object input;
            //normalize input, i.e. fill the variables for text and model
            if (input_type.equals("text")) {
                log.trace("Handling type text");
                // read the text
                input = httpServletRequest.getParameter("input");
                //make a NIF model to work on
                //URIGenerator uriGenerator = ModelHelper.determineGenerator(urirecipe);
                //inputModel = new Text2RDF().processAsDocument(prefix, text, new FakeTokenizer(), uriGenerator);

                /**********************
                 * NOTE that parsing another NIF model is a little bit harder than just the output so this reference implementation is not yet complete.
                 * *********************/
            } else if (input_type.equals("nif-owl")) {
                // Read the model directly from the input
                OntModel inputModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
                ByteArrayInputStream bais = new ByteArrayInputStream(httpServletRequest.getParameter("input").getBytes());
                try {
                    inputModel.read(bais, "");
                } catch (JenaException e) {
                    throw new InvalidParameterException("Jena could not read the presented nif-owl in RDF/XML format.\nFor the conversion of text \"input-type=text\" has to be set.");
                }
                input = inputModel;
            } else {
                throw new InvalidParameterException("third way in a binary path: maybe");
            }

            NIFParameters nifParameters = new NIFParameters(input, copyParameterMap(httpServletRequest), prefix, urirecipe, contextLength, format, output);
            log.trace("created NIFParameters instance from " + input_type + ": " + nifParameters.toString());
            return nifParameters;

        } catch (InvalidParameterException ipe) {
            throw new InvalidParameterException(ipe.getMessage() + getDocumentation(requestUrl));
        }
    }


    @Override
    public String toString() {
        return "NIFParameters{" +
                "input=" + input +
                ", parameterMap=" + parameterMap +
                ", prefix='" + prefix + '\'' +
                ", uriRecipe='" + uriRecipe + '\'' +
                ", format='" + format + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUriRecipe() {
        return uriRecipe;
    }

    public String getFormat() {
        return format;
    }

    public String getOutput() {
        return output;
    }

    public int getContextLength() {
        return contextLength;
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
            doc += "\nExample2 for sending rdf/xml: \n use \"input-type=nif-owl\" and send rdf/xml via POST, to change the output style use the \"format\" parameter" ;

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
