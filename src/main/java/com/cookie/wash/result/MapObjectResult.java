package com.cookie.wash.result;

import java.util.Map;

/**
 * Created by chenlei on 2017/5/24.
 */
public class MapObjectResult extends Result{

    private Map<String, Object> data;

    public MapObjectResult(){}

    public MapObjectResult(Map<String, Object> data){
        this.data = data;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
