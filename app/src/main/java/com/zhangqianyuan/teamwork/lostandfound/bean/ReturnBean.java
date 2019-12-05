package com.zhangqianyuan.teamwork.lostandfound.bean;

public class ReturnBean{

    public ReturnBean(int id,String phone){
        this.id=id;
        this.phone=phone;

    }

    private int id;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
