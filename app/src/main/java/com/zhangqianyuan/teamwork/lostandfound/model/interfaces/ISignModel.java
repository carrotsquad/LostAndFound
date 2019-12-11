package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;

import io.reactivex.Observer;


public interface ISignModel {
    void signIn(String stu, String password, Observer<SignInBean> observer);
}
