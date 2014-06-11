package org.nlp2rdf.implementation.stanfordcorenlp;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.nlp2rdf.core.NIFParameters;
import org.nlp2rdf.core.RDFUnitWrapperForNIF;
import org.nlp2rdf.core.RLOGSLF4JBinding;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.nlp2rdf.core.vocab.RLOGIndividuals;
import org.nlp2rdf.webservice.NIFServlet;

/**
 * User: hellmann
 * Date: 20.09.13
 */
public class StanfordWS extends NIFServlet {


    private final StanfordWrapper stanfordWrapper;

    public StanfordWS() {
        this.stanfordWrapper = new StanfordWrapper();
    }

    public OntModel execute(NIFParameters nifParameters) throws Exception {

        OntModel model = nifParameters.getInputModel();
        OntModel results = ModelFactory.createOntologyModel();
        //some stats
        Monitor mon = MonitorFactory.getTimeMonitor(stanfordWrapper.getClass().getCanonicalName()).start();
        int x = 0;
        {

            // Convert model to OntModel
            Model validationResults = RDFUnitWrapperForNIF.validate(model);
            results.add(validationResults);
            if(! nifParameters.getParameterMap().containsKey("validationreportonly")) {
                // write results in original model
                model.add(results);
            }



            ExtendedIterator<Individual> eit = model.listIndividuals(NIFOntClasses.Context.getOntClass(model));
            for (; eit.hasNext(); ) {
                stanfordWrapper.processText(eit.next(), model, model, nifParameters);
                x++;
            }
        }
        double lv = mon.stop().getLastValue();
        double avg = lv / x;

        String finalMessage = "Annotated " + x + " nif:Context(s)  in " + lv + " ms. (avg " + avg + ") producing " + model.size() + " triples";
        model.add(RLOGSLF4JBinding.log(nifParameters.getLogPrefix(), finalMessage, RLOGIndividuals.DEBUG, stanfordWrapper.getClass().getCanonicalName(), null, null));
        model.setNsPrefix("dc", "http://purl.org/dc/elements/1.1/");

        return results;
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
