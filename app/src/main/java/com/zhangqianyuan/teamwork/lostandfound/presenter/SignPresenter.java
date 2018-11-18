package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ISignModel;
import com.zhangqianyuan.teamwork.lostandfound.model.SignModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISignInActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 登陆Presenter
 * Created at: 2018/11/13 10:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SignPresenter extends AbstractBasePresenter<ISignInActivity> implements ISignPresenter {

    private ISignModel signModel;

    public SignPresenter(ISignInActivity iSignInActivity){
        super(iSignInActivity);
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
