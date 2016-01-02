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

import com.google.common.collect.Maps;
import com.hp.hpl.jena.ontology.OntModel;
import joptsimple.OptionSet;
import org.nlp2rdf.core.urischemes.URIScheme;

import java.util.Arrays;
import java.util.Map;

/**
 * A simple wrapper for the common options in NIF Services
 * TODO unify parametermap
 */
public class NIFParameters {

    private OntModel inputModel;
    private Map<String, String> parameterMap;
    private OptionSet options;

    private final String prefix;
    private final String logPrefix;
    private final URIScheme uriScheme;
    private final String[] uriSchemeParameters;
    private final String outputFormat;


    private  boolean validate =false;
    private String config;
    private String configFile;

    //TODO add text and context[] list
    public NIFParameters(OntModel inputModel, Map<String, String> parameterMap, String prefix, String logPrefix, URIScheme uriScheme, String[] uriSchemeParameters, String outputFormat) {
        this(inputModel, prefix, logPrefix, uriScheme, uriSchemeParameters, outputFormat);
        this.parameterMap = parameterMap;
        this.options = null;
    }

    public NIFParameters(OntModel inputModel, OptionSet options, String prefix, String logPrefix, URIScheme uriScheme, String[] uriSchemeParameters, String outputFormat) {
        this(inputModel, prefix, logPrefix, uriScheme, uriSchemeParameters, outputFormat);
        this.options = options;
        this.parameterMap = Maps.newHashMap();
    }


    public NIFParameters(OntModel inputModel, String prefix, String logPrefix, URIScheme uriScheme, String[] uriSchemeParameters, String outputFormat) {
        this.inputModel = inputModel;
        NIFNamespaces.addNifPrefix(inputModel);
        NIFNamespaces.addRLOGPrefix(inputModel);
        this.prefix = prefix;
        this.logPrefix = logPrefix;
        this.uriScheme = uriScheme;
        this.uriSchemeParameters = uriSchemeParameters;
        this.outputFormat = outputFormat;
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

    public URIScheme getUriScheme() {
        return uriScheme;
    }

    public String[] getUriSchemeParameters() {
        return uriSchemeParameters;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public OptionSet getOptions() {
        return options;
    }

    public String getConfig() {
        return config;
    }

    public boolean isValidate() {
        return validate;
    }


    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public void setInputModel(OntModel inputModel) {
        this.inputModel = inputModel;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }


}
