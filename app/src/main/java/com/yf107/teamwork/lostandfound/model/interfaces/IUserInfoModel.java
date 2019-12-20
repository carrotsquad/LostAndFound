package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.UserImgBean;

import java.io.File;

import retrofit2.Callback;


public interface IUserInfoModel {

    /**
     * 修改头像
     */
    void changeHeadImg(String jecessionId, File imgFile,Callback<UserImgBean> callback );
}
