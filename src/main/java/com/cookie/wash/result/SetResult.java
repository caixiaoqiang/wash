package com.cookie.wash.result;

import java.util.Set;

/**
 * Created by cxq on 2017/5/24.
 */
public class SetResult extends Result{

    private Set<String> data;

    public SetResult(){}

    public SetResult(Set<String> data){
        this.data = data;
    }

    public Set<String> getData() {
        return data;
    }

    public void setData(Set<String> data) {
        this.data = data;
    }
}
