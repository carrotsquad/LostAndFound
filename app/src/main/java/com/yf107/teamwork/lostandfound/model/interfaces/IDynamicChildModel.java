package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.yf107.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;

public interface IDynamicChildModel {

     void  getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
}
