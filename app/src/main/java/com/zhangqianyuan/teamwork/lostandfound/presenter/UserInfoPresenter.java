package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;

import java.io.File;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
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
            mUserInfoModel.changeHeadImg(jsessionId, imgFile, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    if(response.body()==null){
                        getV().onSuccess(400);
                    }else {
                        getV().onSuccess(response.body().getStatus());
                    }
                }
                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {
                     Log.d(T,"error"+t.toString());
                }
            });
        }
    }
}
