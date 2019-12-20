package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.MyLoadModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IMyLoadPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
}


    @Override
    public void postSuccess(String jsessionid,int id) {
        Log.e("successPresenter",""+id+"+"+jsessionid);
            if (isAttachActivity()) {
                mMyLoadModel.postsuccess(jsessionid,id, new Observer<StatusBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusBean statusBean) {
                        if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
                            getV().showStatus(false);
                        } else {
                            getV().showStatus(true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getV().showStatus(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
    }

    @Override
    public void postDelete(String jsessionid,int id) {
        Log.e("deletePresenter",""+id+"+"+jsessionid);
        if (isAttachActivity()) {
            mMyLoadModel.postdelete(jsessionid,id, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    getV().showStatus(false);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
