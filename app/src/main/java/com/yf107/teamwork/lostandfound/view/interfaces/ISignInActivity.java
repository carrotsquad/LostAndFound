package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.SignInBean;

public interface ISignInActivity extends BaseView{
    void showSignInStatus(Boolean status, SignInBean signInBean);
}
