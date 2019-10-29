package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IDynamicChildModel {

     void  getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
     void  getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
}
