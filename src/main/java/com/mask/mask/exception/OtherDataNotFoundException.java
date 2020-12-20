package com.mask.mask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OtherDataNotFoundException extends RuntimeException{
    public static final String NOT_FOUND_MESSAGE = "Data not found";
    public OtherDataNotFoundException(String message){
        super(message);
    }
}
