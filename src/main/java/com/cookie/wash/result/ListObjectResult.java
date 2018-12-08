package com.cookie.wash.result;

import java.util.List;
import java.util.Map;

/**
 * Created by chenlei on 2017/5/24.
 */
public class ListObjectResult extends Result{

    private List<Map<String, Object>> data;

    public ListObjectResult(){}

    public ListObjectResult(List<Map<String, Object>> data){
        this.data = data;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }
}
