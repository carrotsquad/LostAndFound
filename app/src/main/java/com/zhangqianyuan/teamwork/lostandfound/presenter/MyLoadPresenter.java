package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IMyLoadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

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
}


    @Override
    public void postDelete(String jsessionid,int id) {
            Log.e("Tag","Presenter完好");
            if (isAttachActivity()) {
                mMyLoadModel.postDelete(jsessionid,id, new Observer<StatusBean>() {
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
