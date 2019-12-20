package com.yf107.teamwork.lostandfound.bean;


public class UserInfoBean {
    private String headImg;       //头像
    private String neckname;   //昵称
    private String phone;      //电话号码 可传给设置界面
    private String emai;       //用户邮箱 可传给设置界面

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNeckname() {
        return neckname;
    }

    public void setNeckname(String neckname) {
        this.neckname = neckname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmai() {
        return emai;
    }

    public void setEmai(String emai) {
        this.emai = emai;
    }
}
