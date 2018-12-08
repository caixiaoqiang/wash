package com.cookie.wash.result;

import java.util.Map;

/**
 * Created by chenlei on 2017/5/24.
 */
public class MapResult extends Result{

    private Map<String, String> data;

    public MapResult(){}

    public MapResult(Map<String, String> data){
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
