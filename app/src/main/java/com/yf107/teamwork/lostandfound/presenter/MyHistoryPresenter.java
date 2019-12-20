package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.model.MyHistoryModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IMyHistoryPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IMyHistoryActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyHistoryPresenter extends BasePresenter<IMyHistoryActivity> implements IMyHistoryPresenter {
    public static final   String T="MyHistoryPresenter";
    private MyHistoryModel mMyHistoryModel;

    public MyHistoryPresenter(MyHistoryModel model){
        this.mMyHistoryModel = model;
    }

    @Override
    public void getMyHistoryData(String jsessionid,int start,int end) {
        if (isAttachActivity()){
          mMyHistoryModel.getMyHistoryData(jsessionid,start,end,new Callback<MyHistoryItem>() {
              @Override
              public void onResponse(Call<MyHistoryItem> call, Response<MyHistoryItem> response) {
                  getV().showData(response.body().getData());
              }

              @Override
              public void onFailure(Call<MyHistoryItem> call, Throwable t) {
                  Log.d(T,"connection failure"+t.toString());
              }
          });
        }
    }
}
