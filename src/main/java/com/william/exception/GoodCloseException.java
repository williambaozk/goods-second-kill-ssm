package com.william.exception;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public class GoodCloseException extends GoodException {
    public GoodCloseException(String message) {
        super(message);
    }

    public GoodCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
