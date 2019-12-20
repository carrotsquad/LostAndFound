package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.EditInfoModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IEditInfoPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IEditInfoActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditInfoPresenter extends BasePresenter<IEditInfoActivity> implements IEditInfoPresenter {
    //public class EditInfoPresenter extends BasePresenter<IEditInfoActivity> implements IEditInfoPresenter {
    public static final String T = "EditInfoPresenter";
    private EditInfoModel mEditInfoModel;

    public EditInfoPresenter(EditInfoModel model){
        mEditInfoModel = model;
    }


    @Override
    public void uploadNeckName(String jsessionId, String nickName) {
        if (isAttachActivity()){
            mEditInfoModel.uploadNeckName(jsessionId, nickName, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    getV().onSuccess1(response.body().getStatus());
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {
                   Log.d(T,"connect failure"+t.toString());
                }
            });
        }
    }

    @Override
    public void uoloadPhoneNumber(String jsessionId, String phoneNumber) {
        if (isAttachActivity()){
            mEditInfoModel.uploadPhoneNumber(jsessionId, phoneNumber, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    getV().onSuccess2(response.body().getStatus());
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {
                    Log.d(T,"connect failure"+t.toString());
                }
            });
        }
    }

}
