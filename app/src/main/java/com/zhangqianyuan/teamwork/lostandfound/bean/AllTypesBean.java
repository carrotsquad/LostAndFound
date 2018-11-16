package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

public class AllTypesBean {

    private List<TypeBean> types;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public List<TypeBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypeBean> types) {
        this.types = types;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
