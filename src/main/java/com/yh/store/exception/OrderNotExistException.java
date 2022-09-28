package com.yh.store.exception;

public class OrderNotExistException extends RuntimeException{

    public static final int CODE = 5008;

    public OrderNotExistException() {
    }

    public OrderNotExistException(String message) {
        super(message);
    }

    public OrderNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotExistException(Throwable cause) {
        super(cause);
    }

    public OrderNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
