package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IMyLoadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class MyLoadPresenter extends BasePresenter<IMyLoadActivity> implements IMyLoadPresenter {

    public static final  String T = "MyLoadPresenter";
    private MyLoadModel mMyLoadModel;

    public MyLoadPresenter(MyLoadModel model){
        this.mMyLoadModel=model;
    }


    @Override
    public void getMyloadData(String jsessionid,int start,int end) {
        if (isAttachActivity()){
            mMyLoadModel.getMyLoadData(jsessionid, start, end, new Callback<MyHistoryItem>() {
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
}}
