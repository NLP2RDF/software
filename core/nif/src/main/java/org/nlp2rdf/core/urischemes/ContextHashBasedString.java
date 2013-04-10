package org.nlp2rdf.core.urischemes;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;
import org.apache.commons.codec.digest.DigestUtils;
import org.nlp2rdf.core.NIFVocabulary;
import org.nlp2rdf.core.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * User: hellmann
 * Date: 12.02.13
 */
public class ContextHashBasedString extends AbstractURIScheme implements URIScheme  {

    private static final Logger log = LoggerFactory.getLogger(ContextHashBasedString.class);
    public static final int firstCharLength = 20;
    public static final int defaultContextLength = 10;
    public static final String IDENTIFIER = "hash";
    public static final String BRA = "(";
    public static final String KET = ")";

    @Override
    public String getOWLClassURI() {
        return NIFVocabulary.NAMESPACE+ "ContextHashBasedString";
    }

    @Override
    public Span[] parse(String prefix, String uri, String context) throws NIFParserException {
        String[] st = uri.substring(prefix.length()).split("_");

        if (st.length < 5) {
            throw new NIFParserException("Too few parameters in " + uri + " expected 5");
        }

        if (!IDENTIFIER.equals(st[0])) {
            throw new NIFParserException("Wrong identifier for " + getOWLClassURI() + " expected " + IDENTIFIER + " found " + st[0] + " in " + uri);
        }

        int contextLength = 0;
        int anchoredPartLength = 0;
        String digest = null;

        try {

            contextLength = Integer.parseInt(st[1]);
            anchoredPartLength = Integer.parseInt(st[2]);
            digest = st[3];

        } catch (NumberFormatException npe) {
            throw new NIFParserException("The span could not be recognized correctly for scheme " + getOWLClassURI() + " expected int_int_MD5 , found " + st[1] + "_" + st[2] + "_" + st[3], npe);
        }

        StringBuilder addressedString = new StringBuilder();
        addressedString.append(st[4]);
        for (int x = 4; x < st.length; x++) {
            addressedString.append(st[x]).append("_");
        }


        int offset = 0;
        int index;
        ArrayList<Span> spans = new ArrayList<Span>();
        while ((index = context.indexOf(addressedString.toString(), offset)) != -1) {
            StringBuilder message = new StringBuilder();

            Span spanCandidate = new Span(index, index + anchoredPartLength);
            //calculate the context boundaries
            message.append(URISchemeHelper.getContextBefore(spanCandidate, context, contextLength));
            message.append(BRA);
            message.append(spanCandidate.getCoveredText(context));
            message.append(KET);
            message.append(URISchemeHelper.getContextAfter(spanCandidate, context, contextLength));

            String digestNew = DigestUtils.md5Hex(message.toString());
            if (digest.equals(digestNew)) {
                spans.add(spanCandidate);
            } else {
                //try the next one
                offset = index;
            }
        }
        if (spans.isEmpty()) {
            throw new NIFParserException("Could not calculate spans for uri " + uri + " of scheme " + getOWLClassURI() + " string not found in context");
        }
        return spans.toArray(new Span[spans.size()]);
    }

    @Override
    public String generate(String prefix, String context, Span[] spans) {
        return generate(prefix, context, spans, defaultContextLength);
    }

    public String generate(String prefix, String context, Span[] spans, int contextLength) {
        if (spans.length != 1) {
            log.debug(getOWLClassURI() + " scheme only takes the first span for generation of URIs, but the array contains " + spans.length);
        }
        Span span = spans[0];
        //the substring
        String anchoredPart = span.getCoveredText(context).toString();
        StringBuilder message = new StringBuilder();
        //calculate the context boundaries
        message.append(URISchemeHelper.getContextBefore(span, context, contextLength));
        message.append(BRA);
        message.append(anchoredPart);
        message.append(KET);
        message.append(URISchemeHelper.getContextAfter(span, context, contextLength));

        String digest = DigestUtils.md5Hex(message.toString());
        String firstChars = URISchemeHelper.getFirstCharacters(anchoredPart, firstCharLength);
        StringBuilder uri = new StringBuilder();
        uri.append(prefix);
        uri.append(IDENTIFIER).append("_");
        uri.append(contextLength).append("_");
        uri.append(anchoredPart.length()).append("_");
        uri.append(digest).append("_");
        uri.append(firstChars);

        if (log.isTraceEnabled()) {
            log.trace("Text (" + context.length() + " chars): " + context);
            log.trace("Word (" + span.getCoveredText(context).length() + " chars): " + span.getCoveredText(context));
            log.trace("Span: " + span.getStart() + "|" + span.getEnd());
            //log.trace("Before|After: " + before + "|" + after);
            log.trace("Context (" + contextLength + ") before: |" + URISchemeHelper.getContextBefore(span, context, contextLength));
            log.trace("Context (" + contextLength + ") after: |" + URISchemeHelper.getContextAfter(span, context, contextLength) + "|");
            log.trace("Message: |" + message.toString() + "|");
            log.trace("URI: " + uri.toString());
        }

        return uri.toString();
    }


    @Override
    public boolean validate(String prefix, String uri, String context) {
        Span[] span = null;
        try {
            span = parse(prefix, uri, context);
        } catch (NIFParserException npe) {
            return false;
        }

        if (span.length > 1) {
            log.warn(uri + "this uri addresses several strings in the context, validation failed, not unique");
            return false;
        }

        return true;

    }


    public int calculateMinimalContextLength(String text, Set<Span> spans) {
        Monitor mon = MonitorFactory.getTimeMonitor(this.getClass().getSimpleName() + "init").start();
        int contextLength = 0;
        contextLength = repeat(text, spans, contextLength);
        log.info("Minimal context calculated: " + contextLength + " needed: " + mon.stop().getLastValue() + " ms. ");
        return contextLength;
    }

    private int repeat(String text, Set<Span> allSpans, int contextLength) {
        Set<String> collision = new HashSet<String>();
        for (Span span : allSpans) {
            if (false == collision.add(generate("", text, new Span[]{span}))) {
                contextLength++;
                return repeat(text, allSpans, contextLength);
            }
        }
        return contextLength;
    }


}
