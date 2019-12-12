package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IForgetPasswordModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordModel extends BaseModel implements IForgetPasswordModel {

    public ForgetPasswordModel(){
        super();
    }


    @Override
    public void sendCode(String email, Observer<SendCheckCodeBean> observer) {
        api.getSendCheckCode(email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void checkEmail(String session, String checkcode, Observer<CheckCodeBean> observer) {

        api.getCheckCode(checkcode,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getNewPassword(String password, String email, String session, Observer<CheckCodeBean> observer) {
        api.updatepassword(password,session,email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
