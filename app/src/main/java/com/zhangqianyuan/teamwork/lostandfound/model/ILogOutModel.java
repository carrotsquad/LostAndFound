package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;


public interface ILogOutModel {
    void logOut(String session, Observer<StatusBean> observer);
}
