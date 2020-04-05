package com.yf107.teamwork.lostandfound.model.interfaces;


import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import io.reactivex.Observer;

public interface IChangePasswordModel {
    void uploadPassword(String password, String session, String email, Observer<CheckCodeBean> observer);
}