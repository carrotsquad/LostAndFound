package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.yf107.teamwork.lostandfound.model.interfaces.IVerifyModel;
import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description: 注册
 */
public class VerifyModel extends BaseModel implements IVerifyModel {

    public VerifyModel(){
        super();
    }

    @Override
    public void checkCheckCode(String checkcode, String session,Observer<CheckCodeBean> observer) {
        api.getCheckCode(checkcode,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void register(String nickname,String stu, String password, String phonenumber, String session, Observer<RegisterBean> observer) {
        Log.e("registermodel",""+stu+password+"+"+nickname+"+"+phonenumber+session);
        api.getRegister(stu,password,nickname,phonenumber,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("VerifyModel",""+stu+"+"+password+"+"+nickname+"+"+phonenumber);
    }

}
