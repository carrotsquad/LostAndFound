package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

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
    public void getMyHistoryData() {
        if (isAttachActivity()){
          mMyHistoryModel.getMyHistoryData(new Callback<List<MyHistoryItemBean>>() {
              @Override
              public void onResponse(Call<List<MyHistoryItemBean>> call, Response<List<MyHistoryItemBean>> response) {
                  if (!response.body().toString().equals("")){
                      getV().showData(response.body());
                  }
                  Log.d(T,"response is null");
              }

              @Override
              public void onFailure(Call<List<MyHistoryItemBean>> call, Throwable t) {
                  Log.d(T,"connection failure"+t.toString());
              }
          });
        }
    }
}
