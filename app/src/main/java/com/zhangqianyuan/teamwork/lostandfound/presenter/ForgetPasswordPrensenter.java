package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
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

    @Override
    public void getCode(String email) {
        forgetPasswordModel = new ForgetPasswordModel();
        forgetPasswordModel.sendCode(email, new Observer<SendCheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SendCheckCodeBean sendCheckCodeBean) {

                if (sendCheckCodeBean.getStatus() == 200 && sendCheckCodeBean != null) {
                    v.showcheckcodestatus(true, sendCheckCodeBean.getJSESSIONID());
                } else {
                    v.showcheckcodestatus(false, sendCheckCodeBean.getJSESSIONID());
                }
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
    public void IsRight(String session, String checkcode, String password, String email) {

        forgetPasswordModel = new ForgetPasswordModel();
        forgetPasswordModel.checkEmail(session, checkcode, new Observer<CheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckCodeBean checkCodeBean) {

                if (checkCodeBean.getStatus() == 200) {
                    forgetPasswordModel.getNewPassword(password, email, session, new Observer<CheckCodeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(CheckCodeBean checkCodeBean) {

                            if (checkCodeBean.getStatus() == 200) {
                                v.checkCodeIsRight(true);
                            }
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

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
