package com.william.exception;

/**
 * Created by Baozhikuan on 2018/8/19.
 */
public class RepeatKillException extends GoodException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
