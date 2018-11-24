package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.CommentFeedBack;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import com.zhangqianyuan.teamwork.lostandfound.model.IThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.model.ThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

/**
 * Description: 失物/招领启事详情Presenter
 * Created at: 2018/11/13 13:28
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class ThingDetailPresenter extends AbstractBasePresenter<IThingDetailActivity> implements IThingDetailPresenter {


    private  final  String T = "ThingDetailPresenter";
    private ThingDetailModel ThingDetailModel;

    public ThingDetailPresenter(IThingDetailActivity iThingDetailActivity,ThingDetailModel model) {
        super(iThingDetailActivity);
        ThingDetailModel = model;
    }


    @Override
    public void sendDataToWeb(String session, Integer id, int lostid, String time, String content) {
        if (isAttachActivity()){
            ThingDetailModel.sendInternetData(session, id, lostid, time, content, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    v.sendDataToWeb(response.body().getStatus());
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {
                    Log.d(T,"onfailure"+t.toString());
                }
            });
        }
    }

    @Override
    public void getDataFromWeb(String session, int lostid, int start, int end) {
        if (isAttachActivity()){
            ThingDetailModel.getInternetData(session, lostid, start, end, new Callback<CommentFeedBack>() {
                @Override
                public void onResponse(Call<CommentFeedBack> call, Response<CommentFeedBack> response) {
                    if (response.body()==null||response.body().getStatus()==BAD_INTERNET_STATUS){

                    }else if(response.body().getStatus()==FINE_INTERNET_STATUS){
                        v.getDataFromWeb(response.body().getDynamics());
                        Log.d("ThingDetailPresenter","status is 400");
                    }
                }

                @Override
                public void onFailure(Call<CommentFeedBack> call, Throwable t) {
                    Log.d("ThingDetailPresenter","error");
                }
            });
        }
    }
}
