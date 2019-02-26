package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllTypesBean;

import io.reactivex.Observer;

public interface IAllTypesModel {
    void getAllTypes(String session, Observer<AllTypesBean> observer);
}
