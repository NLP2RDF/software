package org.nlp2rdf.vm.dep;

import com.hp.hpl.jena.rdf.model.Model;

public class StanfordSimple {

    public static final String NS = "http://persistence.uni-leipzig.org/nlp2rdf/ontologies/vm/dep/stanford#";

    public static void addStanfordSimplePrefix(Model model) {
        model.setNsPrefix("stanford", NS);
    }

    public static String[] getURIforEdgeLabel(String edgeLabel) {
        int index = edgeLabel.indexOf("_");

        if (index == -1) {
            return new String[] {NS + edgeLabel};
        } else {
            return new String[] {NS + edgeLabel, NS + edgeLabel.substring(0, index)};
        }
    }
}
	
