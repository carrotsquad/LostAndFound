package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ForgetPasswordModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IForgetPasswordPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ForgetPasswordPrensenter extends AbstractBasePresenter<IForgetPasswordActivity> implements IForgetPasswordPresenter {
    private ForgetPasswordModel forgetPasswordModel;

    ForgetPasswordPrensenter(IForgetPasswordActivity iForgetPasswordActivity) {
        super(iForgetPasswordActivity);
    }

    @Override
    public void getCodeStatus(String email) {
        forgetPasswordModel = new ForgetPasswordModel();
        forgetPasswordModel.getInfo(email, new Observer<SendCheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SendCheckCodeBean sendCheckCodeBean) {

                v.showEmailStatus(sendCheckCodeBean.getStatus(),sendCheckCodeBean.getJSESSIONID());
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
