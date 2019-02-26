package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserImgBean;

import java.io.File;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUserInfoModel {

    /**
     * 修改头像
     */
    void changeHeadImg(String jecessionId, File imgFile,Callback<UserImgBean> callback );
}
