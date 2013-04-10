package org.nlp2rdf.core.urischemes;

import org.nlp2rdf.core.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class OffsetBasedString implements URIScheme {

    private static Logger log = LoggerFactory.getLogger(OffsetBasedString.class);
    public static final int firstCharLength = 20;
    public static final String IDENTIFIER = "offset";

    @Override
    public String getOWLClassURI() {
        //TODO
        return "http://nlp2rdf.lod2.eu/schema/string/OffsetBasedString";
    }


    public String generate(String prefix, String context, Span[] spans) {
        return generate(prefix, context, spans, false);
    }

    public String generate(String prefix, String context, Span[] spans, boolean appendString) {
        if (spans.length != 1) {
            log.warn(getOWLClassURI() + " scheme only supports exactly one span, but the array contains " + spans.length);
        }
        //just using the first
        Span span = spans[0];

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(IDENTIFIER);
        sb.append("_");
        sb.append(span.getStart());
        sb.append("_");
        sb.append(span.getEnd());
        if (appendString) {
            sb.append("_");
            sb.append(URIGeneratorHelper.getFirstCharacters(span.getCoveredText(context).toString(), firstCharLength));
        }
        log.trace(sb.toString());
        return sb.toString();
    }

    @Override
    public Span[] parse(String prefix, String uri, String context) throws NIFParserException {
        String[] st = uri.substring(prefix.length()).split("_");
        if (!IDENTIFIER.equals(st[0])) {
            throw new NIFParserException("Wrong identifier for " + getOWLClassURI() + " expected " + IDENTIFIER + " found " + st[0] + " in " + uri);
        }
        try {
            return new Span[]{new Span(Integer.parseInt(st[1]), Integer.parseInt(st[2]))};
        } catch (NumberFormatException npe) {
            throw new NIFParserException("The span could not be recognized correctly for scheme " + getOWLClassURI() + " expected int_int , found " + st[1] + "_" + st[2]);
        }

    }

    @Override
    public boolean validate(String prefix, String uri, String context) {
        Span span = null;
        try {
            span = parse(prefix, uri, context)[0];
        } catch (NIFParserException npe) {
            return false;
        }

        if(context==null){
            return true;
        }

        String[] st = uri.substring(prefix.length()).split("_");
        if (3 <= st.length ) {
            return true;
        } else {
            StringBuilder tmp = new StringBuilder();
            tmp.append(st[3]);
            for (int x = 4; x < st.length; x++) {
                tmp.append(st[x]).append("_");
            }
            return tmp.toString().equals(span.getCoveredText(context));
        }

    }

}
