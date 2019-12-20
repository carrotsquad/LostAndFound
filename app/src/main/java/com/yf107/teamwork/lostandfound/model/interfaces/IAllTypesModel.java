package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.AllTypesBean;

import io.reactivex.Observer;

public interface IAllTypesModel {
    void getAllTypes(String session, Observer<AllTypesBean> observer);
}
