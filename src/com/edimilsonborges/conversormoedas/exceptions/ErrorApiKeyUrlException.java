package com.edimilsonborges.conversormoedas.exceptions;

public class ErrorApiKeyUrlException extends RuntimeException{
    private final String message;
    public ErrorApiKeyUrlException(String message){
     this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
