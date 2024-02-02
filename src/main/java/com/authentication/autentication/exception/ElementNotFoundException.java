package com.authentication.autentication.exception;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String message) {
        super(message);
    }
}
