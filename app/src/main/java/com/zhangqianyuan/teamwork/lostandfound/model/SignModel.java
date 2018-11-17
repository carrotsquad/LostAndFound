package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.LoginBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SignModel extends BaseModel implements ISignModel{

    public SignModel(){
        super();
    }


    @Override
    public void signIn(String email, String password, Observer<SignInBean> observer) {
        LoginBean bean = new LoginBean();
        bean.setPassword(password);
        bean.setUsername(email);
        Gson gson = new Gson();
        api.getSignIn(gson.toJson(bean))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
