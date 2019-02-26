package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

import java.io.File;
/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUserInfoPresenter {
//    void getUserInfoData();
    void uploadHeadImg(String jsessionId, File imgFile);
}
