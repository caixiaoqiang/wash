package com.cookie.wash.result;

/**
 * Created by chenlei on 2017/5/24.
 */
public class BooleanResult extends Result{

    private boolean data;

    public BooleanResult(){}

    public BooleanResult(Boolean data){
        this.data = data;
    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
