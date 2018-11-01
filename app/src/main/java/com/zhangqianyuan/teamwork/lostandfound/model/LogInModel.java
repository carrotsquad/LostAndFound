package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;

import java.util.function.Consumer;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogInModel extends BaseModel implements ILogInModel{

    LogInModel(){
        super();
    }


    @Override
    public void getInfo(String email, Consumer<SendCheckCodeBean> consumer) {
        api.getSendCheckCode(email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<? super SendCheckCodeBean>) consumer);
    }
}
