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

package org.nlp2rdf.corpusconversion;

/**
 * @author Sebastian Hellmann <hellmann@informatik.uni-leipzig.de>
 */
public class OneLine {

    public boolean root = false;

    public String word;
    public String lemma;
    public String tag;
    public String morph;
    public String edge;
    public String parent;
    public String secedge;
    public String comment;
    public int position;

    public boolean isWord() {
        return !word.startsWith("#");
    }

    public boolean hasLemma() {
        return !lemma.equals("--");
    }

    @Override
    public String toString() {
        return "OneLine{" + "word='" + word + '\'' + ", lemma='" + lemma + '\'' + ", tag='" + tag + '\'' + ", morph='" + morph + '\'' + ", edge='" + edge + '\'' + ", parent='" + parent + '\'' + ", secedge='" + secedge + '\'' + ", comment='" + comment + '\'' + ", position=" + position + '}';
    }
}
