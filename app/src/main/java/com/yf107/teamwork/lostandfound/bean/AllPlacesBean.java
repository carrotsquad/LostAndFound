package com.yf107.teamwork.lostandfound.bean;

import java.util.List;

public class AllPlacesBean {

    private List<PlaceBean> types;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTypes(List<PlaceBean> types) {
        this.types = types;
    }

    public List<PlaceBean> getTypes() {
        return types;
    }
}
