package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;

public interface ICommentedMessageModel {
    void getMyCommentedData(String jsessionid, Observer<SearchBean> observer);
}
