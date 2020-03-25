package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.AddCommitBean;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReplacePresenter extends BasePresenter<IReplceActivity> implements IReplacePresenter {
    private ReplaceModel mReplaceModel;

    public ReplacePresenter(){
        this.mReplaceModel =new ReplaceModel();
    }


    @Override
    public void postReplace(String session, TheLostBean bean,int id) {
        if (isAttachActivity()) {
            mReplaceModel.postReplace(session, bean,id, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {

                    Log.e("ReplacePresenter1","statusBean.getStatus()"+addCommitBean.getStatus());
                    if (addCommitBean == null || addCommitBean.getStatus() != 200) {
                        getV().showStatus(false,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(true,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("ReplacePresenter1","wrong");
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
            mReplaceModel.postReplace(session, bean, fileList,id, new Observer<AddCommitBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(AddCommitBean addCommitBean) {

                    Log.e("ReplacePresenter2","statusBean.getStatus()"+addCommitBean.getStatus());
                    if (addCommitBean == null || addCommitBean.getStatus() != 200) {
                        getV().showStatus(false,addCommitBean.getLostid());
                    } else {
                        getV().showStatus(true,addCommitBean.getLostid());
                    }
                }

                @Override
                public void onError(Throwable e) {

                    Log.e("ReplacePresenter2","wrong");
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
            mReplaceModel.publishcomment(session, id, lostid, time, content, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    Log.e("ReplacePresenter5","statusBean.getStatus()"+response.body().getStatus());
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


//    @Override
//    public void postReplace(String session, TheLostBean bean, int id) {
//        if (isAttachActivity()) {
//            mReplaceModel.postReplace(session, bean,id ,new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//        }
//    }
//
//    @Override
//    public void postReplace(String session, TheLostBean bean, List<File> fileList,int id) {
//        if (isAttachActivity()) {
//            mReplaceModel.postReplace(session, bean, fileList,id, new Observer<StatusBean>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//
//                }
//
//                @Override
//                public void onNext(StatusBean statusBean) {
//                    if (statusBean == null || !statusBean.getStatus().equals(MainActivity.FINE_INTERNET_STATUS)) {
//                        getV().showStatus(false);
//                    } else {
//                        getV().showStatus(true);
//                    }
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    getV().showStatus(false);
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//        }
//    }
//}
//
