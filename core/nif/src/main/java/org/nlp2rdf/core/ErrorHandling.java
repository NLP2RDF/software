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

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author Sebastian Hellmann
 *         Date: 11/18/11
 */
public class ErrorHandling {

    private static ErrorHandling errorHandling = new ErrorHandling();

    private int counter = 0;

    /**
     * writes an  error into the model with the given message
     * source is not added, please add it yourself
     * Note that the error is already written to model.
     * The URI was generated by prefix+"error"+counter
     * where counter is increased everytime the method is called.
     *
     * @param fatal   if true the :fatal "1"^^xsd:boolean is added else "0"
     * @param prefix  used to create the URI
     * @param message
     * @param model
     * @return the error object
     */
    public static Individual createError(boolean fatal, String prefix, String message, OntModel model) {
        model.setNsPrefix("error", "http://nlp2rdf.lod2.eu/schema/error/");
        String filler = "";
        if (!(prefix.endsWith("/") || prefix.endsWith("#"))) {
            filler = "/";
        }
        Individual error = model.createIndividual(prefix + filler + "error" + errorHandling.getCounter(), ErrorVocabulary.getErrorClass(model));

        error.addProperty(ErrorVocabulary.getFatalProperty(model), fatal + "", XSDDatatype.XSDboolean);
        error.addProperty(ErrorVocabulary.getMessageProperty(model), message);
        return error;
    }

    private synchronized int getCounter() {
        return counter++;
    }


}
