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

import org.nlp2rdf.core.Span;

/**
 * User: hellmann
 * Date: 12.02.13
 */
public interface URIScheme {

    public abstract String getOWLClassURI();

    public Span[] parse(String prefix, String uri, String context) throws NIFParserException;

    public boolean validate(String prefix, String uri, String context);

    public String generate(String prefix, String context, Span span);

    public String generate(String prefix, String context, Span[] spans);

}
