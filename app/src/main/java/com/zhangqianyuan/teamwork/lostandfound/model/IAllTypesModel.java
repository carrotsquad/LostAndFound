package com.zhangqianyuan.teamwork.lostandfound.model;

import android.database.Observable;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllTypesBean;

import io.reactivex.Observer;

public interface IAllTypesModel {
    void getAllTypes(String session, Observer<AllTypesBean> observer);
}
