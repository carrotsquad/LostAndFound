package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.model.interfaces.IDynamicChildModel;
import com.yf107.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.yf107.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description
 * 动态  子Fragment model

 */
public class DynamicChildModel extends BaseModel implements IDynamicChildModel {
    public DynamicChildModel(){
        super();
    }

    @Override
    public void getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicLostTodayData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("LostTodayModel","LostTodayModel");
    }

    @Override
    public void getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicLostYesterdayData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("LostYesterdayModel","LostYesterdayModel");
    }

    @Override
    public void getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicLostAgoData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("LostAgoModel","LostAgoModel");
    }

    @Override
    public void getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicFindTodayData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("FindTodayModel","FindTodayModel");
    }

    @Override
    public void getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicFindYesterdayData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("FindYesterdayModel","FindYesterdayModel");
    }

    @Override
    public void getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicFindAgoData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("FindAgoModel","FindAgoModel");
    }
}
