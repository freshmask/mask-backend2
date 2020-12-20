package com.mask.mask.exception;

public class CannotClaimCausePolisIsInactive extends RuntimeException {
    public CannotClaimCausePolisIsInactive (String message){
        super(message);
    }
}
