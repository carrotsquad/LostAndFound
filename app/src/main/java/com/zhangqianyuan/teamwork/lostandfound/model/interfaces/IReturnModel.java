package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;

public interface IReturnModel {
    void sendMessage(String session,int id , Observer<StatusBean> observer);
}
