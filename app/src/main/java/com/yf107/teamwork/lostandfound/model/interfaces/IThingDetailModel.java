package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.CommentFeedBack;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;
import retrofit2.Callback;

public interface IThingDetailModel {
    //发送评论
    void sendInternetData(String session,Integer  id,int lostid,String time,String content, Callback<StatusBean> callback);

    //展示评论
    void getInternetData(String session, int lostid,  Callback<CommentFeedBack> callback);
    void sendMessage(String session,int id , Observer<StatusBean> observer);
}
