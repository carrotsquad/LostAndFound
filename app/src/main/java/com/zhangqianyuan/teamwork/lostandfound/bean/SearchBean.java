package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

public class SearchBean {

    private Integer status;

    private List<SearchItemBean> searchItemBeanList;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SearchItemBean> getSearchItemBeanList() {
        return searchItemBeanList;
    }

    public void setSearchItemBeanList(List<SearchItemBean> searchItemBeanList) {
        this.searchItemBeanList = searchItemBeanList;
    }

}
