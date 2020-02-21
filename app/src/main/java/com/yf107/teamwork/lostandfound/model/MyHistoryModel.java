package com.yf107.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.bean.ImageBean;
import com.yf107.teamwork.lostandfound.model.interfaces.IMyHistoryModel;
import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.bean.SendMyHistoryBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Callback;


public class MyHistoryModel extends BaseModel implements IMyHistoryModel {

    public MyHistoryModel(){
        super();
    }


    @Override
    public void getMyHistoryData(String jsessionid,int start,int end,Callback<MyHistoryItem> callback) {
        SendMyHistoryBean bean = new SendMyHistoryBean();
        bean.setStart(start);
        bean.setEnd(end);
        api.getMyHistoryData(jsessionid,new Gson().toJson(bean)).enqueue(callback);
    }


}
