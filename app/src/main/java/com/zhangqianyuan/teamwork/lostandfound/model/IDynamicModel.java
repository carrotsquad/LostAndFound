package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IDynamicModel {

     void  getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);


     void  getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer);
}
