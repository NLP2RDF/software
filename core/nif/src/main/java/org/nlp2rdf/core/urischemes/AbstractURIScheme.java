package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;

/**
 * User: hellmann
 * Date: 27.02.13
 */
public abstract class AbstractURIScheme implements URIScheme {

    public String generate(String prefix, String context, Span span) {
        return generate(prefix, context, new Span[]{span});
    }
}
