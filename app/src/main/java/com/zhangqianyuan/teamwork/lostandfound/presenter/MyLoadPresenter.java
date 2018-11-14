package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyLoadPresenter extends BasePresenter<IMyLoadActivity> implements IMyLoadPresenter {

    public static final  String T = "MyLoadPresenter";
    private MyLoadModel mMyLoadModel;

    public MyLoadPresenter(MyLoadModel model){
        this.mMyLoadModel=model;
    }


    @Override
    public void getMyloadData() {
        if (isAttachActivity()){
            mMyLoadModel.getMyLoadData(new Callback<List<MyLoadItemBean>>() {
                @Override
                public void onResponse(Call<List<MyLoadItemBean>> call, Response<List<MyLoadItemBean>> response) {
                    if (!response.body().toString().equals("")){
                        getV().showData(response.body());
                    }
                    Log.d(T,"response is null");
                }

                @Override
                public void onFailure(Call<List<MyLoadItemBean>> call, Throwable t) {
                   Log.d(T,"connection failure"+t.toString());
                }
            });
        }
}}
