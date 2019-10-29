package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;

/**
 * Description
 * 动态界面
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IDynamicChildPresenter {
    void getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session);
}
