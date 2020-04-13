package com.yf107.teamwork.lostandfound.model;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.model.interfaces.IUserAdviceModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserAdviceModel extends BaseModel implements IUserAdviceModel {
    @Override
    public void sendAdvide(String session, String advice, Observer<CheckCodeBean> observer) {
        api.userAdvice(session,advice)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
