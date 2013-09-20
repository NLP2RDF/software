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

package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntModel;
import joptsimple.OptionSet;
import org.nlp2rdf.core.urischemes.URIScheme;

import java.util.Arrays;
import java.util.Map;

/**
 * A simple wrapper for the common options in NIF Services
 */
public class NIFParameters {

    private final OntModel inputModel;
    private Map<String, String> parameterMap;
    private OptionSet options;

    private final String prefix;
    private final String logPrefix;
    private final URIScheme uriScheme;
    private final String[] uriSchemeParameters;
    private final String outputFormat;

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
        this.parameterMap = null;
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

    public void setConfig(String config) {
        this.config = config;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
