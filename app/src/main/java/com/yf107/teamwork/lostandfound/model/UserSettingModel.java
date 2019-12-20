package com.yf107.teamwork.lostandfound.model;

import com.yf107.teamwork.lostandfound.model.interfaces.IUserSettingModel;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

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
