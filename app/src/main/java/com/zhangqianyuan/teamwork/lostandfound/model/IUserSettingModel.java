package com.zhangqianyuan.teamwork.lostandfound.model;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;
/**
 * Description
 * 设置界面 的所有操作model
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/16 一些其他功能需要加上去
public interface IUserSettingModel {
    //退出账号
    void  exitAccount(String jsessionId, Callback<StatusBean> callback);
}
