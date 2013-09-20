package org.nlp2rdf.implementation.stanfordcore;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.webservice.NIFServlet;

/**
 * User: hellmann
 * Date: 20.09.13
 */
public class StanfordCoreWS extends NIFServlet {


    private final StanfordCoreWrapper stanfordCoreWrapper;

    public StanfordCoreWS() {
        this.stanfordCoreWrapper = new StanfordCoreWrapper();
    }

    public OntModel execute(NIFParameters nifParameters) throws Exception {

        OntModel model = nifParameters.getInputModel();
        ExtendedIterator<Individual> eit = model.listIndividuals(NIFOntClasses.Context.getOntClass(model));
        for (; eit.hasNext(); ) {
            stanfordCoreWrapper.processText(nifParameters.getPrefix(), eit.next(), nifParameters.getUriScheme(), model, null);

        }
        return model;
        /* if (nifParameters.inputWasText()) {
            URIGenerator uriGenerator = URIGeneratorHelper.determineGenerator(nifParameters.getUriRecipe(), nifParameters.getContextLength());
            stanfordCoreNLPWrapper.processText(nifParameters.getPrefix(), nifParameters.getInputAsText(), uriGenerator, model);
        } else if (nifParameters.inputWasRDF()) {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "rdf as input is not implemented yet", model);
        } else {
            ErrorHandling.createError(true, nifParameters.getPrefix(), "Could not determine whether input was text or rdf", model);
        }*/
    }

}
