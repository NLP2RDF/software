package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * Created by shellmann on 1/2/16.
 */
public abstract class NIFWrapper {

    public abstract void process(Individual context, OntModel inputModel,
                                 OntModel outputModel, NIFParameters nifParameters);

}
