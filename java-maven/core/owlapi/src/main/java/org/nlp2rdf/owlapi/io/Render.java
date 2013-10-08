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
