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

package org.nlp2rdf.webservice;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.nlp2rdf.cli.ParameterException;
import org.nlp2rdf.cli.ParameterParser;
import org.nlp2rdf.core.NIFParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * User: Sebastian Hellmann
 * See   http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html
 */
public class NIFParameterWebserviceFactory {
    private static Logger log = LoggerFactory.getLogger(NIFParameterWebserviceFactory.class);


    /**
     * Factory method
     *
     * @param httpServletRequest
     * @return
     */
    public static NIFParameters getInstance(HttpServletRequest httpServletRequest, String defaultPrefix) throws ParameterException, IOException {

        //twice the size to split key value
        String[] args = new String[httpServletRequest.getParameterMap().size() * 2];

        int x = 0;

        for (Object key : httpServletRequest.getParameterMap().keySet()) {
            String pname = (String) key;
            //transform key to CLI style
            pname = (pname.length() == 1) ? "-" + pname : "--" + pname;

            //collect CLI args
            args[x++] = pname;
            args[x++] = httpServletRequest.getParameter((String) key);

        }

        //parse CLI args
        OptionParser parser = ParameterParser.getParser(args, defaultPrefix);
        OptionSet options = ParameterParser.getOption(parser, args);


        // print help screen
        if (options.has("h")) {
            String addHelp = "";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            parser.printHelpOn(baos);
            throw new ParameterException(baos.toString());
        }

        // parse options with webservice setted to true
        return ParameterParser.parseOptions(options, true);
    }
}
