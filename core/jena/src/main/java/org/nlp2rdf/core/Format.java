package org.nlp2rdf.core;

/**
 * User: hellmann
 * Date: 06.07.13
 */

//TODO this class will be generated from an ontology someday
public class Format {

    public static String toJena(String toBeMapped) {

        return toBeMapped.replace("rdfxml", "RDF/XML").replace("turtle", "N3").replace("ntriples", "N-TRIPLE");
    }

}
