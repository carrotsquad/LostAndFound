package com.yf107.teamwork.lostandfound.presenter;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;
import com.yf107.teamwork.lostandfound.model.ReplaceModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IReplacePresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IReplceActivity;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReplacePresenter extends BasePresenter<IReplceActivity> implements IReplacePresenter {
    private ReplaceModel mReplaceModel;

    public ReplacePresenter(){
        this.mReplaceModel =new ReplaceModel();
    }


    @Override
    public void postReplace(String session, TheLostBean bean, int id) {
        if (isAttachActivity()) {
            mReplaceModel.postReplace(session, bean,id ,new Observer<StatusBean>() {
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
    public void postReplace(String session, TheLostBean bean, List<File> fileList,int id) {
        if (isAttachActivity()) {
            mReplaceModel.postReplace(session, bean, fileList,id, new Observer<StatusBean>() {
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

