package com.william.exception;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public class GoodException extends RuntimeException {
    public GoodException(String message) {
        super(message);
    }

    public GoodException(String message, Throwable cause) {
        super(message, cause);
    }
}
