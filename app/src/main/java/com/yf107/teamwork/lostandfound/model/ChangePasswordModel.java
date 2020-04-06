package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.model.interfaces.IChangePasswordModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordModel extends BaseModel implements IChangePasswordModel {
    @Override
    public void uploadPassword(String password, String session, String email, Observer<CheckCodeBean> observer) {
        api.updatepasswordUncheck(password,session,email)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
