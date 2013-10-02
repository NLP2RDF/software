package org.nlp2rdf.core.util;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;

import java.util.Comparator;

/**
 * User: hellmann
 * Date: 01.10.13
 */
public class WordComparator implements Comparator<Individual> {
    final DatatypeProperty beginIndex;


    public WordComparator(DatatypeProperty beginIndex) {
        this.beginIndex = beginIndex;
    }

    @Override
    public int compare(Individual individual, Individual individual1) {
        int a = individual.getPropertyValue(beginIndex).asLiteral().getInt();
        int b = individual1.getPropertyValue(beginIndex).asLiteral().getInt();
        return a - b;
    }

}
