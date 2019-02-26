package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observer;

public interface IVerifyModel {
    void checkCheckCode(String checkcode, String session, Observer<CheckCodeBean> observer);
    void register(String email, String nickname, String password, String phonenumber,String session, Observer<RegisterBean> observer);
}
