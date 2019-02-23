package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserImgBean;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IUserInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;

import java.io.File;

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
