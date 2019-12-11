package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;

public interface ICommentedMessageModel {
    void getMyCommentedData(String jsessionid, Observer<SearchBean> observer);
    void postdelete(String jsessionid,int id, Observer<StatusBean> observer);
    void getupdateIsRead(String session,int lostid,Observer<StatusBean> observer);
}
