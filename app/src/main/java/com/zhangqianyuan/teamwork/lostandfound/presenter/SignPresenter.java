package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ISignModel;
import com.zhangqianyuan.teamwork.lostandfound.model.SignModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISignInActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignPresenter implements ISignPresenter {

    private ISignModel signModel;
    private ISignInActivity iSignInActivity;

    public SignPresenter(ISignInActivity iSignInActivity){
        this.iSignInActivity = iSignInActivity;
    }

    @Override
    public void getSignIn(String email, String password) {
        signModel = new SignModel();
        signModel.signIn(email, password, new Observer<SignInBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SignInBean signInBean) {
                if (signInBean==null||signInBean.getStatus()==200) {
                    iSignInActivity.showSignInStatus(true,signInBean);
                }else {
                    iSignInActivity.showSignInStatus(false,null);
                }
            }

            @Override
            public void onError(Throwable e) {
                iSignInActivity.showSignInStatus(false,null);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
