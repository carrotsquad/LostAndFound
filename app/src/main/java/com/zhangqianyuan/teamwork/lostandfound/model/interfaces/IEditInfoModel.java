package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;
/**
 * Description
 * 设置 界面中 修改昵称和电话号码
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IEditInfoModel {
    void  uploadNeckName(String jsessionId,String nickName,Callback<StatusBean> callback);
    void  uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback);
}
