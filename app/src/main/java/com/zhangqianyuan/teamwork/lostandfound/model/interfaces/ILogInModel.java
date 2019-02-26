package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;

import io.reactivex.Observer;

public interface ILogInModel {
    void getInfo(String email, Observer<SendCheckCodeBean> observer);
}
