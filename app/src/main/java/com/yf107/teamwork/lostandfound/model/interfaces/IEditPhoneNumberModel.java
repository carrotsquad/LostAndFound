package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

public interface IEditPhoneNumberModel {
    void  uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback);
}
