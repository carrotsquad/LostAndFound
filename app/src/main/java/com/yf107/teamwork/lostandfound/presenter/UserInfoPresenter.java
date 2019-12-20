package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.UserImgBean;
import com.yf107.teamwork.lostandfound.model.UserInfoModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IUserInfoPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IUserInfoFragment;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoPresenter extends BasePresenter<IUserInfoFragment> implements IUserInfoPresenter {

    public static final   String  T = "UserInfoPresenter";
    private UserInfoModel mUserInfoModel;

    public UserInfoPresenter(UserInfoModel model){
        this.mUserInfoModel = model;
    }


    @Override
    public void uploadHeadImg(String jsessionId, File imgFile) {
        if (isAttachActivity()){
            mUserInfoModel.changeHeadImg(jsessionId, imgFile, new Callback<UserImgBean>() {
                @Override
                public void onResponse(Call<UserImgBean> call, Response<UserImgBean> response) {
                    if(response.body()==null){
                        getV().onSuccess(400,"");
                    }else {
                        UserImgBean userImgBean = response.body();
                        getV().onSuccess(userImgBean.getStatus(),userImgBean.getPhoto());
                    }
                }

                @Override
                public void onFailure(Call<UserImgBean> call, Throwable t) {
                    Log.d(T,"error"+t.toString());
                }
            });
        }
    }

}
