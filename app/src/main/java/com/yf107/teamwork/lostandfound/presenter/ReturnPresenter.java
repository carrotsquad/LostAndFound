package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.ReturnModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IReturnPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IGiveBackView;
import com.yf107.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReturnPresenter extends BasePresenter<IGiveBackView> implements IReturnPresenter {

    private ReturnModel mReturnModel;

    public ReturnPresenter(){
        this.mReturnModel =new ReturnModel();
    }

    @Override
    public void sendMessage(String session,int id,String QQ,String phone) {
        Log.e("ReturnPresenter","完好"+id);
        if (isAttachActivity()) {
            mReturnModel.sendMessage(session,id,QQ,phone, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    Log.d("状态1", String.valueOf(statusBean.getStatus()));
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