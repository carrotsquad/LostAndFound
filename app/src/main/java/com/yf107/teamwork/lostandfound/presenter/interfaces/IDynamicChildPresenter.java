package com.yf107.teamwork.lostandfound.presenter.interfaces;

import com.yf107.teamwork.lostandfound.bean.DynamicsRequestBean;

/**
 * Description
 * 动态界面
 */
public interface IDynamicChildPresenter {
    void getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getphoto(int lostid);
}
