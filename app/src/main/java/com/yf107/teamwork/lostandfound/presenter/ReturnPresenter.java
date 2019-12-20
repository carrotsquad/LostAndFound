package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.ReturnModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IReturnPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReturnPresenter extends BasePresenter<IThingDetailActivity> implements IReturnPresenter {

    private ReturnModel mReturnModel;

    public ReturnPresenter(){
        this.mReturnModel =new ReturnModel();
    }

    @Override
    public void sendMessage(String session,int id) {
        Log.e("ReturnPresenter","完好"+id);
        if (isAttachActivity()) {
            mReturnModel.sendMessage(session,id, new Observer<StatusBean>() {
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