package org.nlp2rdf.cli;

/**
 * User: hellmann
 * Date: 19.09.13
 */
public class ParameterException extends Exception {

    public ParameterException(String message){
        super(message);
    }
    public ParameterException(String message, Throwable throwable){
        super(message,throwable);
    }
}
