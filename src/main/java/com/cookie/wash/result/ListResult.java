package com.cookie.wash.result;

import java.util.List;
import java.util.Map;

/**
 * Created by cxq on 2017/5/24.
 */
public class ListResult extends Result{

    private List<Map<String, String>> data;

    public ListResult(){}

    public ListResult(List<Map<String, String>> data){
        this.data = data;
    }

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }
}
