package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observer;

public interface IVerifyModel {
    void checkCheckCode(String checkcode,String email, String nickname, String password, String phonenumber,String session, IVerifyListener iVerifyListener,Observer<RegisterBean> observer);
//    void register(String email, String nickname, String password, String phonenumber,String session, Observer<RegisterBean> observer);
}
