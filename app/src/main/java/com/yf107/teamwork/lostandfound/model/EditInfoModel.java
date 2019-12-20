package com.yf107.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.model.interfaces.IEditInfoModel;
import com.yf107.teamwork.lostandfound.bean.ChangePhoneNumberBean;
import com.yf107.teamwork.lostandfound.bean.ChangeUserNickNameBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;



public class EditInfoModel extends BaseModel  implements IEditInfoModel {
    public EditInfoModel(){
        super();
    }

    @Override
    public void uploadNeckName(String jsessionId, String nickName, Callback<StatusBean> callback) {
        ChangeUserNickNameBean bean = new ChangeUserNickNameBean();
        bean.setNickname(nickName);
        api.uploadNickName(jsessionId,new Gson().toJson(bean)).enqueue(callback);
    }

    @Override
    public void uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback) {
        ChangePhoneNumberBean bean = new ChangePhoneNumberBean();
        bean.setPhonenumber(phoneNumber);
        api.uploadPhoneNumber(jsessionId,new Gson().toJson(bean)).enqueue(callback);
    }
}
