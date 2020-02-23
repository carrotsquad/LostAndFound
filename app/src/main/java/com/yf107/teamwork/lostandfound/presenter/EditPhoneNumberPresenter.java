package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.EditInfoModel;
import com.yf107.teamwork.lostandfound.model.EditPhoneNumberModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IEditPhoneNumberPresenter;
import com.yf107.teamwork.lostandfound.view.activity.RevisePhoneNumber;
import com.yf107.teamwork.lostandfound.view.interfaces.IRevisePhoneNumberActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPhoneNumberPresenter extends BasePresenter<IRevisePhoneNumberActivity> implements IEditPhoneNumberPresenter {
    private EditPhoneNumberModel editPhoneNumberModel;
    @Override
    public void uoloadPhoneNumber(String jsessionId, String phoneNumber) {
        if(isAttachActivity()){
            editPhoneNumberModel = new EditPhoneNumberModel();
            editPhoneNumberModel.uploadPhoneNumber(jsessionId, phoneNumber, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {

                    Log.d("?!!!!!","!!!!");
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {

                    Log.d("?????",t.toString());
                }
            });
        }
    }
}
