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
        NIFParameters nifParameters = null;

        String[] args = new String[httpServletRequest.getParameterMap().size()*2];
        int x = 0;
        for (Object key : httpServletRequest.getParameterMap().keySet()) {
            String pname = (String) key;
            pname = (pname.length() == 1) ? "-" + pname : "--" + pname;
            args[x++] = pname;
            args[x++] = (String) httpServletRequest.getParameter((String) key);

        }

        OptionParser parser = ParameterParser.getParser(args, defaultPrefix);
        OptionSet options = ParameterParser.getOption(parser, args);


        // print help screen
        if (options.has("h")) {
            String addHelp = "";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            parser.printHelpOn(baos);
            throw new ParameterException(baos.toString());
        }

        nifParameters = ParameterParser.parseOptions(options, false);
        return nifParameters;


    }


}
