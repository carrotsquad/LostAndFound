package com.zhangqianyuan.teamwork.lostandfound.bean;

public class ReturnBean{

    public ReturnBean(int id,String qq,String phone){
        this.id=id;
        this.qq = qq;
        this.phone=phone;

    }

    private int id;
    private String qq;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQq(){
        return qq;
    }

    public void setQq(String qq){
        this.qq = qq;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
