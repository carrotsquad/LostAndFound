package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.SendCheckCodeBean;

import io.reactivex.Observer;

public interface IForgetPasswordModel{
    void sendCode(String email,Observer<SendCheckCodeBean> observer);
    void checkEmail(String session,String checkcode,Observer<CheckCodeBean> observer);
    void getNewPassword(String password, String email,String session, Observer<CheckCodeBean> observer);
}
