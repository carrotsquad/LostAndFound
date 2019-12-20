package com.yf107.teamwork.lostandfound.presenter;

import android.annotation.SuppressLint;

import com.yf107.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.yf107.teamwork.lostandfound.model.LogInModel;
import com.yf107.teamwork.lostandfound.model.interfaces.ILogInModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.ILogInPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.ILogInActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 注册Presenter
 */
public class LogInPresenter extends AbstractBasePresenter<ILogInActivity> implements ILogInPresenter {

    private ILogInModel iLogInModel;

    public LogInPresenter(ILogInActivity iLogInActivity){
        super(iLogInActivity);
    }

    @SuppressLint("NewApi")
    @Override
    public void getCodeStatus(String email) {
        iLogInModel = new LogInModel();
        iLogInModel.getInfo(email, new Observer<SendCheckCodeBean>() {
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
