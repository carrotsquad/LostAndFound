package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;

import io.reactivex.Observer;


public interface ISignModel {
    void signIn(String email, String password, Observer<SignInBean> observer);
}
