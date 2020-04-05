package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.model.ChangePasswordModel;
import com.yf107.teamwork.lostandfound.model.interfaces.IChangePasswordModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IChangePasswordPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IChangePasswordView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ChangePasswordPresenter extends BasePresenter<IChangePasswordView> implements IChangePasswordPresenter {
    ChangePasswordModel ChangePasswordModel;

    public ChangePasswordPresenter(ChangePasswordModel changePasswordModel){
        this.ChangePasswordModel = changePasswordModel;
    }
    @Override
    public void uploadPassword(String password, String session, String email) {
        if(isAttachActivity()){
            ChangePasswordModel.uploadPassword(password, session, email, new Observer<CheckCodeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CheckCodeBean checkCodeBean) {

                   getV().getStatus(checkCodeBean.getStatus());
                   Log.d("Changesuccessful","成功"+password+session+email+checkCodeBean.getStatus());

                }

                @Override
                public void onError(Throwable e) {

                    Log.d("Change111111",e.toString());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
