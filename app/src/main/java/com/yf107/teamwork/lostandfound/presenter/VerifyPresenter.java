package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.RegisterBean;
import com.yf107.teamwork.lostandfound.model.VerifyModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IVerifyPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IVerifyActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 验证Presenter

 */
public class VerifyPresenter extends AbstractBasePresenter<IVerifyActivity> implements IVerifyPresenter {

    private VerifyModel verifyModel;

    public VerifyPresenter(IVerifyActivity iVerifyActivity) {
        super(iVerifyActivity);
    }


    @Override
    public void getRegister(String checkcode,String stu, String nickname, String password, String phonenumber, String sessionID) {
        verifyModel = new VerifyModel();

        verifyModel.checkCheckCode(checkcode, sessionID, new Observer<CheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CheckCodeBean checkCodeBean) {
                if(checkCodeBean==null||checkCodeBean.getStatus()==400){
                    v.showcheckcodestatus(false);
                }else {
                    v.showcheckcodestatus(true);
                    Log.e("verifyPresenter",""+stu+password+"+"+nickname+"+"+phonenumber);
                    verifyModel.register(stu, nickname, password, phonenumber, sessionID, new Observer<RegisterBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RegisterBean registerBean) {
                            if(registerBean==null||registerBean.getStatus()==400){
                                v.getregister(false);
                            }else {
                                v.getregister(true);
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
