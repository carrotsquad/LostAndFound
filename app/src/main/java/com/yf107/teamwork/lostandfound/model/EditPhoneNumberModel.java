package com.yf107.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.bean.ChangePhoneNumberBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.interfaces.IEditPhoneNumberModel;

import retrofit2.Callback;

public class EditPhoneNumberModel extends BaseModel implements IEditPhoneNumberModel {
    @Override
    public void uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback) {
        ChangePhoneNumberBean bean = new ChangePhoneNumberBean();
        bean.setPhonenumber(phoneNumber);
        api.uploadPhoneNumber(jsessionId,new Gson().toJson(bean)).enqueue(callback);
    }
}
