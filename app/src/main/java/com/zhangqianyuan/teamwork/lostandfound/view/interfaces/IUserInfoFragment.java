package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IUserInfoFragment extends BaseView {
//    void showData(int headImg, String neckname, String phone, String emai);
    void  onSuccess(int status, String userphoto);
}
