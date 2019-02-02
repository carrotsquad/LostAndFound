package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyHistoryModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyHistoryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyHistoryPresenter extends BasePresenter<IMyHistoryActivity> implements IMyHistoryPresenter {
    public static final   String T="MyHistoryPresenter";
    private MyHistoryModel mMyHistoryModel;

    public MyHistoryPresenter(MyHistoryModel model){
        this.mMyHistoryModel = model;
    }

    @Override
    public void getMyHistoryData(String jsessionid,int start,int end) {
        if (isAttachActivity()){
          mMyHistoryModel.getMyHistoryData(jsessionid,start,end,new Callback<MyHistoryItem>() {
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
}
