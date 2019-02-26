package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUserInfoFragment extends BaseView {
//    void showData(int headImg, String neckname, String phone, String emai);
    void  onSuccess(int status, String userphoto);
}
