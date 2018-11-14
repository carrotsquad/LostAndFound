package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserInfoModel extends BaseModel implements IUserInfoModel {
    public UserInfoModel(){
        super();
    }

    @Override
    public void getUserInfoData(Callback<UserInfoBean> callback) {
        api.getUserInfoData().enqueue(callback);
    }
}
