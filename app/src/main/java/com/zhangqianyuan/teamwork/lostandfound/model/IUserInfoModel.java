package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IUserInfoModel {
    /**
     * 获取我的基本信息
     */
    void getUserInfoData(Callback<UserInfoBean> callback);
}
