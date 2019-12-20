package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.model.interfaces.ISignModel;
import com.yf107.teamwork.lostandfound.bean.LoginBean;
import com.yf107.teamwork.lostandfound.bean.SignInBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignModel extends BaseModel implements ISignModel {

    public SignModel(){
        super();
    }


    @Override
    public void signIn(String snumber, String password, Observer<SignInBean> observer) {
        LoginBean bean = new LoginBean();
        bean.setPassword(password);
        bean.setStu(snumber);
        Gson gson = new Gson();
        api.getSignIn(gson.toJson(bean))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("SignModel",""+snumber+password);
    }


}
