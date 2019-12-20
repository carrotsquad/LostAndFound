package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.model.interfaces.IMyUploadModel;
import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.bean.SendMyHistoryBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Callback;

public class MyLoadModel extends  BaseModel implements IMyUploadModel {

    public MyLoadModel(){
        super();
    }


    @Override
    public void getMyLoadData(String jsessionid,int start,int end ,Callback<MyHistoryItem> callback) {
        SendMyHistoryBean bean = new SendMyHistoryBean();
        bean.setStart(start);
        bean.setEnd(end);
        api.getMyLoadData(jsessionid,new Gson().toJson(bean)).enqueue(callback);
    }

    @Override
    public void postsuccess(String jsessionid, int id, Observer<StatusBean> observer) {
        api.postSuccess(jsessionid,id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("postsuccess",""+id+"+"+jsessionid);
    }

    @Override
    public void postdelete(String jsessionid, int id, Observer<StatusBean> observer) {
        api.postDelete(jsessionid,id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("postdelete",""+id+"+"+jsessionid);
    }

}


