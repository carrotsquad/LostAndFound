package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;

import io.reactivex.annotations.NonNull;
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
    public void getUserInfoData() {
        if (isAttachActivity()){
          mUserInfoModel.getUserInfoData(new Callback<UserInfoBean>() {
              @Override
              public void onResponse( Call<UserInfoBean> call, Response<UserInfoBean> response) throws NullPointerException{
                  if (!response.body().toString().equals("")){
                      Log.d(T,"response is not null");
                      assert response.body() != null;
                      getV().showData(response.body().getHeadImg(),response.body().getNeckname(),response.body().getPhone(),response.body().getEmai());
                  }
              }

              @Override
              public void onFailure(Call<UserInfoBean> call, Throwable t) {
                   Log.d(T,"error"+t.toString());
              }
          });
        }
    }
}
