package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.AddCommitBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;
import com.yf107.teamwork.lostandfound.model.UploadModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IUploadPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IUploadFormActivity;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadPresenter extends BasePresenter<IUploadFormActivity> implements IUploadPresenter {
    private UploadModel mUploadModel;

    public UploadPresenter(){
        this.mUploadModel =new UploadModel();
    }


    @Override
    public void postUpload(String session, TheLostBean bean) {
        if (isAttachActivity()) {
//            mUploadModel.postUpload(session, bean, new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    Log.e("UploadPresenter1","statusBean.getStatus()"+statusBean.getStatus());
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e("UploadPresenter1","wrong");
//                    getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });

            mUploadModel.postUpload(session, bean, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {

                    Log.e("UploadPresenter1","statusBean.getStatus()"+addCommitBean.getStatus());
                    if (addCommitBean == null || addCommitBean.getStatus() != 200) {
                        getV().showStatus(false,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(true,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("UploadPresenter1","wrong");
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void postUpload(String session, TheLostBean bean, List<File> fileList) {
        if (isAttachActivity()) {
//            mUploadModel.postUpload(session, bean, fileList, new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    Log.e("UploadPresenter2","statusBean.getStatus()"+statusBean.getStatus());
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e("UploadPresenter2","wrong");
//                 //   getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
            mUploadModel.postUpload(session, bean, fileList, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {

                    Log.e("UploadPresenter2","statusBean.getStatus()"+addCommitBean.getStatus());
                    if (addCommitBean == null || addCommitBean.getStatus() != 200) {
                        getV().showStatus(false,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(true,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("UploadPresenter2","wrong");
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void cardUpload(String stu, String session, TheLostBean bean) {
        if (isAttachActivity()) {
//            mUploadModel.cardUpload(stu,session, bean, new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    Log.e("UploadPresenter3","statusBean.getStatus()"+statusBean.getStatus());
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e("UploadPresenter1","wrong");
//                    getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
            mUploadModel.cardUpload(stu, session, bean, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {

                    Log.e("UploadPresenter3","statusBean.getStatus()"+addCommitBean.getStatus());
                    if (addCommitBean==null || addCommitBean.getStatus()!= 200) {
                        getV().showStatus(false,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(true,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("UploadPresenter1","wrong");
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void cardUpload(String stu, String session, TheLostBean bean, List<File> fileList) {
        if (isAttachActivity()) {
//            mUploadModel.cardUpload(stu,session, bean, fileList, new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    Log.e("UploadPresenter4","statusBean.getStatus()"+statusBean.getStatus());
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    Log.e("UploadPresenter2","wrong");
//                    getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
            Log.e("UploadPresenter","stu = "+stu+"session = "+session+"bean = "+bean+"fileList = "+fileList);
            mUploadModel.cardUpload(stu, session, bean, fileList, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {
                    Log.e("UploadPresenter4","statusBean.getStatus()"+addCommitBean.getStatus());
                    Log.e("UploadPresenter","addCommitBean.getStatus() = "+addCommitBean.getStatus());
                    if (addCommitBean.getStatus() == 200 || addCommitBean.getStatus()== 400) {
                        getV().showStatus(true,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(false,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("UploadPresenter2","wrong");
                    Log.d("UploadPresenter2",e.toString());
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void sendDataToWeb(String session, Integer id, int lostid, String time, String content) {
        if(isAttachActivity()){
            mUploadModel.publishcomment(session, id, lostid, time, content, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    Log.e("UploadPresenter5","statusBean.getStatus()"+response.body().getStatus());
                    if (response.body().getStatus() == null || response.body().getStatus() != 200) {
                        getV().isSuccess(false);
                    } else {
                        getV().isSuccess(true);
                    }
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {

                }
            });
        }
    }

}
