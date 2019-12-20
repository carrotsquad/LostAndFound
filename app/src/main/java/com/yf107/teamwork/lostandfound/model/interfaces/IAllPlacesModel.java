package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.AllPlacesBean;

import io.reactivex.Observer;

public interface IAllPlacesModel {
    void getAllPlaces(String session, Observer<AllPlacesBean> observer);
}
