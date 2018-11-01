package com.zhangqianyuan.teamwork.lostandfound.network;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api {

    @POST("/")
    Observable<SendCheckCodeBean> getSendCheckCode(@Field("mail") String email);
}
