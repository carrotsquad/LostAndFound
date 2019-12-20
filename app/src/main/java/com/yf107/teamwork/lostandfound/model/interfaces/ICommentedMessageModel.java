package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;

import io.reactivex.Observer;

public interface ICommentedMessageModel {
    void getMyCommentedData(String jsessionid, Observer<UpDateMessageBean> observer);
    void postdelete(String jsessionid,int id, Observer<StatusBean> observer);
    void getupdateIsRead(String session,int lostid,Observer<StatusBean> observer);
}
