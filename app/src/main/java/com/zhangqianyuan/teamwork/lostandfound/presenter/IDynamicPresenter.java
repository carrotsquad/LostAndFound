package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;

/**
 * Description
 * 动态界面
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IDynamicPresenter {
    void getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session);
    void getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session);
}
