package com.start.mvc.exception;

public class NickNameBusyException extends RuntimeException {
    public NickNameBusyException(String message) {
        super(message);
    }
}
