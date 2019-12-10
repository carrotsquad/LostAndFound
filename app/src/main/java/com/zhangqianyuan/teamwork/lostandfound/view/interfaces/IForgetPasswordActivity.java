package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

public interface IForgetPasswordActivity extends BaseView {

    void showcheckcodestatus(Boolean status,String session);
    void checkCodeIsRight(int isright);
}
