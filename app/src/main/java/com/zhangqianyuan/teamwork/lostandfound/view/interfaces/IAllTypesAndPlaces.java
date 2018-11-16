package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.PlaceBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TypeBean;

import java.util.List;

public interface IAllTypesAndPlaces extends BaseView{
    void getIAllTypesAndPlaces(Boolean status, List<TypeBean> typeBeanList, List<PlaceBean> placeBeanList);
}
