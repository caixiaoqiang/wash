package com.cookie.wash.result;

/**
 * Created by cxq on 2017/7/10.
 */
public class TResult<T> extends Result {
    private T data;

    public TResult() {}

    public TResult(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
