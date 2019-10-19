package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

public interface IForgetPasswordActivity extends BaseView {

    void showEmailStatus(Integer status,String session);
    void showcheckcodestatus(Boolean status,String session,String checkcode);
}
