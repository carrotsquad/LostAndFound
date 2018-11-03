package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.model.IVerifyListener;
import com.zhangqianyuan.teamwork.lostandfound.model.VerifyModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IVerifyActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class VerifyPresenter implements IVerifyPresenter {

    private IVerifyActivity iVerifyActivity;
    private VerifyModel verifyModel;

    public VerifyPresenter(IVerifyActivity iVerifyActivity) {
        this.iVerifyActivity = iVerifyActivity;
    }


    @Override
    public void getRegister(String checkcode, String email, String nickname, String password, String phonenumber, String sessionID) {
        verifyModel = new VerifyModel();
        verifyModel.checkCheckCode(checkcode, email, nickname, password, phonenumber, sessionID, new IVerifyListener() {
            @Override
            public void onStatus(Boolean status) {
                iVerifyActivity.showcheckcodestatus(status);
            }
        }, new Observer<RegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                if(registerBean == null){
                    iVerifyActivity.getregister(false);
                }else {
                if(registerBean.getStatus() == 400){
                    iVerifyActivity.getregister(false);
                }else {
                    iVerifyActivity.getregister(true);
                }
            }
            }

            @Override
            public void onError(Throwable e) {
                iVerifyActivity.getregister(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
