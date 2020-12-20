package com.mask.mask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LimitOfCompensationException extends RuntimeException {

    public LimitOfCompensationException(String message) {
        super(message);
    }
}
