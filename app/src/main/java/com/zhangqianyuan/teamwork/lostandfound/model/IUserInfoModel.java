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
     * @param headImg 头像
     * @param neckname 昵称
     * @param phone  电话号码
     * @param emai   邮箱
     * @param callback
     */
    void getUserInfoData(int headImg, String neckname, String phone, String emai,Callback<UserInfoBean> callback);
}
