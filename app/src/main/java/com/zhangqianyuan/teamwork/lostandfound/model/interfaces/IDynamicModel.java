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
public interface IDynamicModel {

     void  getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);


     void  getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
}
