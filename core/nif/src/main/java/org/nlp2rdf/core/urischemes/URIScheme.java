package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;

/**
 * User: hellmann
 * Date: 12.02.13
 */
public interface URIScheme {

    public abstract String getOWLClassURI();

    public Span[] parse(String prefix, String uri, String context) throws NIFParserException;

    public boolean validate(String prefix, String uri, String context);

    public String generate(String prefix, String context, Span span);

    public String generate(String prefix, String context, Span[] spans);

}
