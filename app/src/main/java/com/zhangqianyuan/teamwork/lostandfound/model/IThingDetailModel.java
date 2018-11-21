package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import retrofit2.Callback;

public interface IThingDetailModel {
    //发送评论
    void getInternetData(String session,Integer  id,int lostid, String  time, String content, Callback<StatusBean> callback);
}
