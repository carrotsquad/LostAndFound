package com.yf107.teamwork.lostandfound.bean;

import java.util.List;

public class SearchBean {

    private Integer status;

    private List<DynamicItemBean> dynamics;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DynamicItemBean> getDynamics() {
        return dynamics;
    }

    public void setDynamics(List<DynamicItemBean> dynamics) {
        this.dynamics = dynamics;
    }

}
