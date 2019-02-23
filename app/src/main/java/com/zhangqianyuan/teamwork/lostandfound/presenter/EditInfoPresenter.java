package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.EditInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IEditInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IEditInfoActivity;

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
public class EditInfoPresenter extends BasePresenter<IEditInfoActivity> implements IEditInfoPresenter {

    public static final String T = "EditInfoPresenter";
    private  EditInfoModel mEditInfoModel;

    public EditInfoPresenter(EditInfoModel model){
        mEditInfoModel = model;
    }


    @Override
    public void uploadNeckName(String jsessionId, String nickName) {
        if (isAttachActivity()){
            mEditInfoModel.uploadNeckName(jsessionId, nickName, new Callback<StatusBean>() {
                @Override
                public void onResponse(Call<StatusBean> call, Response<StatusBean> response) {
                    getV().onSuccess(response.body().getStatus());
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
                    getV().onSuccess(response.body().getStatus());
                }

                @Override
                public void onFailure(Call<StatusBean> call, Throwable t) {
                    Log.d(T,"connect failure"+t.toString());
                }
            });
        }
    }

}
