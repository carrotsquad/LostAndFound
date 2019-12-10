package com.zhangqianyuan.teamwork.lostandfound.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IReturnModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ReturnModel extends BaseModel implements IReturnModel {

    @Override
    public void sendMessage(String session,int id ,String qq,String phone, Observer<StatusBean> observer) {
        api.sendMessage(session,id,qq,phone)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("ReturnModel","完好"+id+qq+phone);
    }
}
