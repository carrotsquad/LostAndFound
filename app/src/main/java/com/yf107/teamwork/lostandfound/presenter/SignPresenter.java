package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.SignInBean;
import com.yf107.teamwork.lostandfound.model.SignModel;
import com.yf107.teamwork.lostandfound.model.interfaces.ISignModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.ISignPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.ISignInActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 登陆Presenter

 */
public class SignPresenter extends AbstractBasePresenter<ISignInActivity> implements ISignPresenter {

    private ISignModel signModel;

    public SignPresenter(ISignInActivity iSignInActivity){
        super(iSignInActivity);
    }

    @Override
    public void getSignIn(String stu, String password) {
        signModel = new SignModel();
        signModel.signIn(stu, password, new Observer<SignInBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SignInBean signInBean) {
                if (signInBean!=null&&signInBean.getStatus()==200) {
                    v.showSignInStatus(true,signInBean);
                }else {
                    v.showSignInStatus(false,null);
                }
                Log.d("fuck","onnext");
            }

            @Override
            public void onError(Throwable e) {
               v.showSignInStatus(false,null);
                Log.d("fuck","onerror");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
