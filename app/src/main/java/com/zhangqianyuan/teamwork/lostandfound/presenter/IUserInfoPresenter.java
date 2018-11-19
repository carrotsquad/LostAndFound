package com.zhangqianyuan.teamwork.lostandfound.presenter;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;

import java.io.File;

import retrofit2.Callback;
/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IUserInfoPresenter {
//    void getUserInfoData();
    void uploadHeadImg(String jsessionId, File imgFile);
}
