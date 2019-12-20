package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.SignInBean;

import io.reactivex.Observer;


public interface ISignModel {
    void signIn(String stu, String password, Observer<SignInBean> observer);
}
