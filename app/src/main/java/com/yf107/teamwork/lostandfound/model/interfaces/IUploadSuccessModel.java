package com.yf107.teamwork.lostandfound.model.interfaces;

import android.database.Observable;

import com.yf107.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

public interface IUploadSuccessModel {
    void uploadComment(String session, Integer id,int lostid,String time,String content, Callback<StatusBean> callback);
}
