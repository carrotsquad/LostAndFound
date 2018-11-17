package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllPlacesBean;

import io.reactivex.Observer;

public interface IAllPlacesModel {
    void getAllPlaces(String session, Observer<AllPlacesBean> observer);
}
