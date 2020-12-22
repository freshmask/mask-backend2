package com.mask.mask.exception;

public class EmailDoesntSendException extends RuntimeException {
    public EmailDoesntSendException(String message){
        super(message);
    }
}
