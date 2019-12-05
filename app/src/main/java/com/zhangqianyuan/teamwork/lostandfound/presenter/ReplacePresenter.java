package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ReplaceModel;
import com.zhangqianyuan.teamwork.lostandfound.model.UploadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IReplacePresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IUploadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IReplceActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUploadFormActivity;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

public class ReplacePresenter extends BasePresenter<IReplceActivity> implements IReplacePresenter {
    private ReplaceModel mReplaceModel;

    public ReplacePresenter(){
        this.mReplaceModel =new ReplaceModel();
    }


    @Override
    public void postReplace(String session, TheLostBean bean,int id) {
        if (isAttachActivity()) {
            mReplaceModel.postReplace(session, bean,id ,new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
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
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
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

