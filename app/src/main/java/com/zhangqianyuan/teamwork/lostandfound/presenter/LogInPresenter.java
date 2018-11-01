package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.annotation.SuppressLint;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ILogInModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ILogInActivity;

public class LogInPresenter implements ILogInPresenter{

    private ILogInModel iLogInModel;
    private ILogInActivity iLogInActivity;

    LogInPresenter(ILogInActivity iLogInActivity){
        this.iLogInActivity = iLogInActivity;
    }

    @SuppressLint("NewApi")
    @Override
    public void getCodeStatus(String email) {
        iLogInModel.getInfo(email, new java.util.function.Consumer<SendCheckCodeBean>() {
            @Override
            public void accept(SendCheckCodeBean sendCheckCodeBean) {
                iLogInActivity.showEmailStatus(sendCheckCodeBean.getStatus());
            }
        });
    }
}
