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

package org.nlp2rdf.implementation.opennlp;

import org.nlp2rdf.core.Span;

/**
 *
 *
 * @author Sebastian Hellmann
 *         Date: 11/8/11
 *
 * Boilerplate
 */
public class SpanDecorator extends Span{
    //private final opennlp.tools.util.Span span ;

    public SpanDecorator(int s, int e, String type) {
        super(s, e, type);
    }

    public SpanDecorator(int s, int e) {
        super(s, e);
    }

    public SpanDecorator(Span span, int offset) {
        super(span, offset);
    }

    public SpanDecorator(opennlp.tools.util.Span span) {
        super(span.getStart(), span.getEnd());
    }



}
