package com.yh.store.exception;

/**
 * 收货地址超出限制异常（20）条
 */
public class AddressCountLimitException extends RuntimeException{

    public static final int CODE = 7000;
    public AddressCountLimitException() {
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    public AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
