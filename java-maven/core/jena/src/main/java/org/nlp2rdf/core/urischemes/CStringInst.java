package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;
import org.nlp2rdf.core.vocab.NIFOntClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * User: hellmann
 * Date: 09.09.13
 */
public class CStringInst extends AbstractURIScheme implements URIScheme {
    private static Logger log = LoggerFactory.getLogger(CStringInst.class);


    @Override
    public String getOWLClassURI() {
        return NIFOntClasses.CStringInst.getUri();
    }

    @Override
    public Span[] parse(String prefix, String uri, String context) throws NIFParserException {
        throw new NIFParserException("Parsing of URIs with type " + getOWLClassURI() + " doesn't make sense" + uri);
    }

    @Override
    public boolean validate(String prefix, String uri, String context) {
        //TODO check??
        return true;
    }

    @Override
    public String generate(String prefix, String context, Span[] spans) {
        StringBuilder sb = new StringBuilder(prefix);
        sb.append("uuid-");
        sb.append(UUID.randomUUID());
        return sb.toString();
    }

}
