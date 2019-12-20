package com.yf107.teamwork.lostandfound.model.interfaces;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

public interface IUserSettingModel {
    //退出账号
    void  exitAccount(String jsessionId, Callback<StatusBean> callback);
}
