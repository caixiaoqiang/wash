package com.cookie.wash.result;

/**
 * Created by chenlei on 2017/5/24.
 */
public class IntResult extends Result{

    private int data;

    public IntResult(){}

    public IntResult(int data){
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
