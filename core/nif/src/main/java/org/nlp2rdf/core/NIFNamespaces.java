package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * @author kurzum
 */
public class NIFNamespaces {
    public static final String BASE = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/";
    public static final String NIF = BASE + "nif-core#";
    public static final String ERROR = BASE + "error#";

    public static void addNifPrefix(OntModel model) {
        model.setNsPrefix("nif", NIF);
    }

    public static void addErrorPrefix(OntModel model) {
        model.setNsPrefix("error", ERROR);
    }

}
