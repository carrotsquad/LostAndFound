package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observer;

public interface IVerifyModel {
    void checkCheckCode(String checkcode, String session, Observer<CheckCodeBean> observer);
    void register(String nickname,String stu, String password, String phonenumber, String session, Observer<RegisterBean> observer);
}
