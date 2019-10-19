package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ForgetPasswordModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IForgetPasswordPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPasswordPrensenter extends AbstractBasePresenter<IForgetPasswordActivity> implements IForgetPasswordPresenter {
    private ForgetPasswordModel forgetPasswordModel;

    public ForgetPasswordPrensenter(IForgetPasswordActivity iForgetPasswordActivity) {
        super(iForgetPasswordActivity);
    }

    @SuppressLint("NewApi")
    @Override
    public void getCodeStatus(String email) {
        forgetPasswordModel = new ForgetPasswordModel();
        forgetPasswordModel.getInfo(email,new Observer<SendCheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SendCheckCodeBean sendCheckCodeBean) {

                v.showEmailStatus(sendCheckCodeBean.getStatus(),sendCheckCodeBean.getJSESSIONID());
                Log.d("TAG","邮箱获取验证码成功了的");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void checkCode(String session, String checkcode) {
        forgetPasswordModel = new ForgetPasswordModel();
        forgetPasswordModel.checkEmail(session, checkcode, new Observer<CheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckCodeBean checkCodeBean) {
                Boolean t  = true;
                if(checkCodeBean==null||checkCodeBean.getStatus()==400){
                    t = false;
                }

                v.showcheckcodestatus(t,session,checkcode);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
