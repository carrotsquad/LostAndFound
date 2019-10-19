package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

public interface IForgetPasswordPresenter {
    void getCodeStatus(String email);
    void checkCode(String session,String checkcode);
}
