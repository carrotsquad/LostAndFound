package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;

import io.reactivex.Observer;

public interface IThingDetailModel {
    void getInternetData(String ID, String session, Observer<ThingDetailBean> observer);
}
