package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;


public interface ILogOutModel {
    void logOut(String session, Observer<StatusBean> observer);
}
