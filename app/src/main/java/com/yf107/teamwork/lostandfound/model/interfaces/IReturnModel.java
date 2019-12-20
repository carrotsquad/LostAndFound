package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;

public interface IReturnModel {
    void sendMessage(String session,int id , Observer<StatusBean> observer);
}
