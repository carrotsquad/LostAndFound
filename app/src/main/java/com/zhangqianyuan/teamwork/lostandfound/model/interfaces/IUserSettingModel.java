package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;
/**
 * Description
 * 设置界面 的所有操作model
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUserSettingModel {
    //退出账号
    void  exitAccount(String jsessionId, Callback<StatusBean> callback);
}
