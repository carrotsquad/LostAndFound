package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.yf107.teamwork.lostandfound.model.interfaces.IReturnModel;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReturnModel extends BaseModel implements IReturnModel {

    @Override
    public void sendMessage(String session,int id , Observer<StatusBean> observer) {
        api.sendMessage(session,id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("ReturnModel","完好"+session+"+"+id);
    }
}
