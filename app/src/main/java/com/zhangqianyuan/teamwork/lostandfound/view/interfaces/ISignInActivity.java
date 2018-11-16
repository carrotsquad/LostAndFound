package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;

public interface ISignInActivity extends BaseView{
    void showSignInStatus(Boolean status, SignInBean signInBean);
}
