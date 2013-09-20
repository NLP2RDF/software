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

package org.nlp2rdf.implementation.stanfordcore;

import com.hp.hpl.jena.ontology.OntModel;
import org.nlp2rdf.core.ErrorHandling;
import org.nlp2rdf.core.URIGenerator;
import org.nlp2rdf.core.urischemes.URIGeneratorHelper;
import org.nlp2rdf.webservice.NIFParameters;
import org.nlp2rdf.webservice.NIFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIFStemmer extends NIFServlet {
    private static Logger log = LoggerFactory.getLogger(NIFStemmer.class);

    @Override
    public void execute(NIFParameters nifParameters, OntModel model) throws Exception {

        String stemmer = (nifParameters.getParameterMap().get("stemmer") == null) ? "PorterStemmer" : nifParameters.getParameterMap().get("stemmer");
        SnowballStemmer ss = new SnowballStemmer(stemmer);
        if (nifParameters.inputWasText()) {
            URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(nifParameters.getUriRecipe(), nifParameters.getContextLength());
            ss.processText(nifParameters.getPrefix(), uriGenerator, nifParameters.getInputAsText(), model);
        } else if (nifParameters.inputWasRDF()) {
            ss.processNIFModel(model);
        } else {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "Could not determine whether input was text or rdf", model);
        }
        log.debug("returning " + model.size() + " triples");
    }


}
