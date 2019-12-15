package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.CommentFeedBack;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.model.UploadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IUploadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUploadFormActivity;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class UploadPresenter extends BasePresenter<IUploadFormActivity> implements IUploadPresenter {
    private UploadModel mUploadModel;

    public UploadPresenter(){
        this.mUploadModel =new UploadModel();
    }


    @Override
    public void postUpload(String session, TheLostBean bean) {
        if (isAttachActivity()) {
            mUploadModel.postUpload(session, bean, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(StatusBean statusBean) {
                    Log.e("UploadPresenter1","statusBean.getStatus()"+statusBean.getStatus());
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("UploadPresenter1","wrong");
                    getV().showStatus(false);
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
            mUploadModel.postUpload(session, bean, fileList, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    Log.e("UploadPresenter2","statusBean.getStatus()"+statusBean.getStatus());
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("UploadPresenter2","wrong");
                    getV().showStatus(false);
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
            mUploadModel.cardUpload(stu,session, bean, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(StatusBean statusBean) {
                    Log.e("UploadPresenter3","statusBean.getStatus()"+statusBean.getStatus());
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("UploadPresenter1","wrong");
                    getV().showStatus(false);
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
            mUploadModel.cardUpload(stu,session, bean, fileList, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    Log.e("UploadPresenter4","statusBean.getStatus()"+statusBean.getStatus());
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("UploadPresenter2","wrong");
                    getV().showStatus(false);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void sendDataToWeb(String session, Integer id, int lostid, String time, String content) {
        if (isAttachActivity()){
            mUploadModel.publishcomment(session, id, lostid, time, content, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    getV().showStatus(true);
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {

                }
            });
        }
    }
}
