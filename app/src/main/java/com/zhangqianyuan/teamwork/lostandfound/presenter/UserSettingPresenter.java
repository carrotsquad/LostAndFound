package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.UserSettingModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IUserSettingPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserSettingActivity;

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
public class UserSettingPresenter extends BasePresenter<IUserSettingActivity>  implements IUserSettingPresenter {
    private UserSettingModel mUserSettingModel;
    public static final String T = "UserSettingPresenter";

    public UserSettingPresenter(UserSettingModel model){
        this.mUserSettingModel = model;
    }


    @Override
    public void exitAccount(String jsessionId) {
        mUserSettingModel.exitAccount(jsessionId, new Callback<StatusBean>() {
            @Override
            public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                getV().settingOnSuccess(response.body().getStatus());
                Log.d(T,"response=="+response.body().toString());
            }

            @Override
            public void onFailure(Call<StatusBean> call, Throwable t) {
               Log.d(T,"connect failure"+t.toString());
            }
        });
    }
}
