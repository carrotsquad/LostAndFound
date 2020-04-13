package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.model.UserAdviceModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IUserAdvicePresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IUserAdviceView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class UserAdvicePresenter extends BasePresenter<IUserAdviceView> implements IUserAdvicePresenter {
    UserAdviceModel userAdviceModel;
    @Override
    public void senAdvice(String session, String advice) {
        if(isAttachActivity()){
            userAdviceModel = new UserAdviceModel();
            userAdviceModel.sendAdvide(session, advice, new Observer<CheckCodeBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(CheckCodeBean checkCodeBean) {
                    getV().isSuccess(checkCodeBean.getStatus());
                }

                @Override
                public void onError(Throwable e) {

                    Log.d("useradvice",e.toString());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
