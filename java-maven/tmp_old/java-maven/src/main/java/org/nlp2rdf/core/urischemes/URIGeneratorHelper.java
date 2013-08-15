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

package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Sebastian Hellmann
 *         Created: 29.06.11
 *         <p/>
 *         This class contains static helper methods for handling and validating a model
 */
public class URIGeneratorHelper {
    private static final Logger log = LoggerFactory.getLogger(URIGeneratorHelper.class);


    public static String join(String[] s, String glue) {
        int k = s.length;
        if (k == 0) {
            return null;
        }
        StringBuilder out = new StringBuilder();
        out.append(s[0]);
        for (int x = 1; x < k; ++x)
            out.append(glue).append(s[x]);
        return out.toString();
    }

    /**
     * @param span          the span of the addressed String
     * @param text
     * @param contextLength
     * @return
     */
    public static String getContextBefore(Span span, String text, int contextLength) {
        int before = (span.getStart() - contextLength < 0) ? 0 : span.getStart() - contextLength;
        return text.substring(before, span.getStart());
    }

    /**
     * @param span          the span of the addressed String
     * @param text
     * @param contextLength
     * @return
     */
    public static String getContextAfter(Span span, String text, int contextLength) {
        int after = (span.getEnd() + contextLength > text.length()) ? text.length() : span.getEnd() + contextLength;
        return text.substring(span.getEnd(), after);
    }


    /**
     * return the first characters of the anchored part urlencoded
     *
     * @param anchoredPart
     * @param firstCharLength
     * @return
     */
    public static String getFirstCharacters(String anchoredPart, int firstCharLength) {
        String firstChars = (anchoredPart.length() > firstCharLength) ? anchoredPart.substring(0, firstCharLength) : anchoredPart;
        try {
            firstChars = URLEncoder.encode(firstChars, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            log.error("", e);
        }
        return firstChars;
    }

}
