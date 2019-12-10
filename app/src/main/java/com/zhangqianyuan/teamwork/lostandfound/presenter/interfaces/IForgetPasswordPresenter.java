package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

public interface IForgetPasswordPresenter {
    void getCode(String email);
    void IsRight(String session,String checkcode,String password,String email);
}
