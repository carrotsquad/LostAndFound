package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.CommentFeedBack;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

public interface IThingDetailModel {
    //发送评论
    void sendInternetData(String session,Integer  id,int lostid,String time,String content, Callback<StatusBean> callback);

    //展示评论
    void getInternetData(String session, int lostid,  Callback<CommentFeedBack> callback);
}
