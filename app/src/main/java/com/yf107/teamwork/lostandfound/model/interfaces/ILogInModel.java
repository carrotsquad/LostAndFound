package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.SendCheckCodeBean;

import io.reactivex.Observer;

public interface ILogInModel {
    void getInfo(String email, Observer<SendCheckCodeBean> observer);
}
