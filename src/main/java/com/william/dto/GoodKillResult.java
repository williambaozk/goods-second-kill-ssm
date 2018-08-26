package com.william.dto;

/**
 * Created by Baozhikuan on 2018/8/22.
 */
public class GoodKillResult<T> {
    private boolean success;
    private T data;
    private String error;

    public GoodKillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public GoodKillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
