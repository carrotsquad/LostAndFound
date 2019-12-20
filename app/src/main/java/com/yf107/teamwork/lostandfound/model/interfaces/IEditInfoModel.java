package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

public interface IEditInfoModel {
    void  uploadNeckName(String jsessionId,String nickName,Callback<StatusBean> callback);
    void  uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback);
}
