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

package org.nlp2rdf.owlapi.io;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.*;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;

import java.io.InputStream;
import java.util.Set;

/**
 * User: hellmann
 * Date: 07.10.13
 */
public class Render {


    public static final OWLObjectRenderer renderer = new DLSyntaxObjectRenderer();

    public static String render(InputStream is) throws OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology inputOntology = manager.loadOntologyFromOntologyDocument(is);

        Set<OWLAxiom> axioms = inputOntology.getAxioms();

        StringBuilder sb = new StringBuilder();
        for (OWLAxiom a : axioms) {
            if (
                    a.isOfType(AxiomType.DECLARATION) ||
                            a.isOfType(AxiomType.ANNOTATION_ASSERTION) ||
                            a.isOfType(AxiomType.CLASS_ASSERTION) ||
                            a.isOfType(AxiomType.OBJECT_PROPERTY_ASSERTION) ||
                            a.isOfType(AxiomType.DATA_PROPERTY_ASSERTION)
                    ) {
                continue;
            }
            sb.append(renderer.render(a)).append("\n");
        }
        return sb.toString();
    }

}
