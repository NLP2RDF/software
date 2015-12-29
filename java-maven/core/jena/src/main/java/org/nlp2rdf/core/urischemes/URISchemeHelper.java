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

package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.NIFNamespaces;
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
public class URISchemeHelper {
    private static final Logger log = LoggerFactory.getLogger(URISchemeHelper.class);

    /**
     * transform the uriScheme parameter according to:
     * http://persistence.uni-leipzig.org/nlp2rdf/specification/api.html#urischeme
     *
     * @param uriSchemeParameter
     */
    public static URIScheme getInstance(String uriSchemeParameter) {

        uriSchemeParameter = uriSchemeParameter.replace(NIFNamespaces.NIF, "");

        switch (uriSchemeParameter) {
            case "RFC5147String":
                return new RFC5147String();
            case "OffsetBasedString":
                return new OffsetBasedString();
            case "ContextHashBasedString":
                return new ContextHashBasedString();
            default:
                return new RFC5147String();
        }

    }

    public static String[] getCoveredTexts(Span[] spans, String context) {
        String[] texts = new String[]{};
        for (int x = 0; x > spans.length; x++) {
            texts[x] = spans[x].getCoveredText(context).toString();
        }
        return texts;
    }


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
