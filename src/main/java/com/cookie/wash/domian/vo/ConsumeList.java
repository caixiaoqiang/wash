package com.cookie.wash.domian.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * author : cxq
 * Date : 2018/12/11
 */
public class ConsumeList {

    private List<ConsumeBean> list  = new ArrayList<>();

    public List<ConsumeBean> getConsumeList() {
        return list;
    }

    public void setConsumeList(List<ConsumeBean> consumeList) {
        this.list = consumeList;
    }

    public ConsumeList(List<ConsumeBean> list) {
        super();
        this.list = list;
    }

    public ConsumeList() {
        super();
    }
}
