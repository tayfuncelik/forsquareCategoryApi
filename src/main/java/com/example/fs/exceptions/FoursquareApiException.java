package com.example.fs.exceptions;


public class FoursquareApiException extends Exception {

    private static final long serialVersionUID = -4581357612541474483L;

    public FoursquareApiException(String message) {
        super(message);
    }

    public FoursquareApiException(Throwable t) {
        super(t);
    }
}
