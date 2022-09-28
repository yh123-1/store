package com.yh.store.exception;

public class CartNotFoundExcetpion extends RuntimeException{

    public static final int CODE = 5007;

    public CartNotFoundExcetpion() {
        super();
    }

    public CartNotFoundExcetpion(String message) {
        super(message);
    }

    public CartNotFoundExcetpion(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundExcetpion(Throwable cause) {
        super(cause);
    }

    protected CartNotFoundExcetpion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
