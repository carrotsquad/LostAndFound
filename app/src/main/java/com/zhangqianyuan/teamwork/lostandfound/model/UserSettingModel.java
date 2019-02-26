package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IUserSettingModel;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class UserSettingModel extends BaseModel implements IUserSettingModel {

    public UserSettingModel(){
        super();
    }


    @Override
    public void exitAccount(String jsessionId, Callback<StatusBean> callback) {
        api.exitAccount(jsessionId).enqueue(callback);
    }
}
