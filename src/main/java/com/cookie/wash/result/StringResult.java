package com.cookie.wash.result;

/**
 * Created by chenlei on 2017/5/24.
 */
public class StringResult extends Result{

    private String data;

    public StringResult(){}

    public StringResult(String data){
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
