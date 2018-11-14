package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserInfoPresenter extends BasePresenter<IUserInfoFragment> implements IUserInfoPresenter {
    private UserInfoModel mUserInfoModel;

    public UserInfoPresenter(UserInfoModel model){
        this.mUserInfoModel = model;
    }


    @Override
    public void getUserInfoData() {
        if (isAttachActivity()){
          //  mUserInfoModel.getUserInfoData();
        }
    }
}
