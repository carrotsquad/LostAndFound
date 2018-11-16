package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 我的界面 数据类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserInfoBean {
    private int headImg;       //头像
    private String neckname;   //昵称
    private String phone;      //电话号码 可传给设置界面
    private String emai;       //用户邮箱 可传给设置界面

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int headImg) {
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
