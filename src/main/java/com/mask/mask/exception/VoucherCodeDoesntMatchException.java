package com.mask.mask.exception;

public class VoucherCodeDoesntMatchException extends RuntimeException {
    public VoucherCodeDoesntMatchException (String message){
        super(message);
    }
}
