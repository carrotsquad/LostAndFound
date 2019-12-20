package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.PlaceBean;
import com.yf107.teamwork.lostandfound.bean.TypeBean;

import java.util.List;

public interface IAllTypesAndPlaces extends BaseView{
    void getIAllTypesAndPlaces(Boolean status, List<TypeBean> typeBeanList, List<PlaceBean> placeBeanList);
}
