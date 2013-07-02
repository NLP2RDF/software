package org.nlp2rdf.core;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author kurzum
 */
public class NIFNamespaces {
    public static final String BASE = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/";
    public static final String NIF = BASE + "nif-core#";
    public static final String RLOG = BASE + "rlog#";

    public static void addNifPrefix(Model model) {
        model.setNsPrefix("nif", NIF);
    }

    public static void addRLOGPrefix(Model model) {
        model.setNsPrefix("rlog", RLOG);
    }

}
