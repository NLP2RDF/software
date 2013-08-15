package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: hellmann
 * Date: 12.02.13
 * RFC 5147:
 * http://tools.ietf.org/html/rfc5147#section-3
 * <p/>
 * text-fragment   =  text-scheme 0*( ";" integrity-check )
 * text-scheme     =  ( char-scheme / line-scheme )
 * char-scheme     =  "char=" ( position / range )
 * line-scheme     =  "line=" ( position / range )
 * integrity-check =  ( length-scheme / md5-scheme )
 * [ "," mime-charset ]
 * position        =  number
 * range           =  ( position "," [ position ] ) / ( "," position )
 * number          =  1*( DIGIT )
 * length-scheme   =  "length=" number
 * md5-scheme      =  "md5=" md5-value
 * md5-value       =  32HEXDIG
 */
public class RFC5147String implements URIScheme {
    private static Logger log = LoggerFactory.getLogger(RFC5147String.class);
    public static final String charIdentifier = "char=";
    public static final String lineIdentifier = "line=";

    @Override
    public String getOWLClassURI() {
        //TODO
        return null;
    }

    @Override
    public Span[] parse(String prefix, String uri, String context) throws NIFParserException {
        //separate the integrity check
        String[] st = uri.substring(prefix.length()).split(";");
        String text_scheme = st[0];
        if (text_scheme.startsWith(charIdentifier)) {
            try {
                return new Span[]{getSpan(text_scheme.substring(charIdentifier.length()), context)};
            } catch (NumberFormatException nfe) {
                throw new NIFParserException("URI violates syntax of scheme " + getOWLClassURI(), nfe);
            }
        } else if (text_scheme.startsWith(lineIdentifier)) {
            //TODO
            throw new NIFParserException("not implemented yet");
        } else {
            throw new NIFParserException("URI violates syntax of scheme " + getOWLClassURI());
        }
    }

    private Span getSpan(String range, String context) {
        String[] pos = range.split(",");
        if (pos[0].isEmpty()) {
            return new Span(0, Integer.parseInt(pos[1]));
        } else if (pos[1].isEmpty()) {
            return new Span(Integer.parseInt(pos[0]), context.length());
        } else {
            return new Span(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        }
    }

    @Override
    public boolean validate(String prefix, String uri, String context) {
        Span[] span = null;
        try {
            span = parse(prefix, uri, context);
        } catch (NIFParserException npe) {
            return false;
        }

        String[] st = uri.substring(prefix.length()).split(";");
        if (st.length <= 1) {
            return true;
        }

        String integrity_check = st[1];
        //TODO
        return true;

    }

    @Override
    public String generate(String prefix, String context, Span[] spans) {
        if (spans.length != 1) {
            log.debug(getOWLClassURI() + " scheme only takes the first span for generation of URIs, but the array contains " + spans.length);
        }
        StringBuilder sb = new StringBuilder(prefix);
        sb.append(charIdentifier);
        sb.append(spans[0].getStart());
        sb.append(",");
        sb.append(spans[0].getEnd());
        return sb.toString();
    }
}
