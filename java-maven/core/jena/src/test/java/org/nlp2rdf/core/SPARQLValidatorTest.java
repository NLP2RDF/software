package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * User: hellmann
 * Date: 06.07.13
 */
public class SPARQLValidatorTest {

    @Test
    public void testValidate() {
        OntModel output = null;
        OntModel correct = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        correct.read(SPARQLValidator.class.getClassLoader().getResourceAsStream("nif-correct-model.ttl"), "", "Turtle");
        output = SPARQLValidator.getInstance().validate(correct);
        assertTrue(output.isEmpty());

        OntModel erroneous = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, ModelFactory.createDefaultModel());
        erroneous.read(SPARQLValidator.class.getClassLoader().getResourceAsStream("nif-erroneous-model.ttl"), "", "Turtle");
        output = SPARQLValidator.getInstance().validate(erroneous);
        assertFalse(output.isEmpty());

        //RDFWriter rw = new JenaReadersWriters.RDFWriterRIOT_Turtle();
        //rw.write(output, System.out, "");

    }

}
