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
public interface IDynamicPresenter {
    void getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session);
}
