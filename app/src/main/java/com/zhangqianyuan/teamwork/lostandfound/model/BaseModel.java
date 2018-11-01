package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.network.Api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.BaseUrl;

public class BaseModel {

    Retrofit retrofit;
    Api api;
    OkHttpClient okHttpClient;

    BaseModel(){
        okHttpClient = new OkHttpClient.Builder().build();
        retrofit  = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .build();
        api = retrofit.create(Api.class);
    }
}
