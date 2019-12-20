package com.yf107.teamwork.lostandfound.presenter.interfaces;

/**
 * Description
 * 设置中修改电话号码 和昵称
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IEditInfoPresenter {
    void  uploadNeckName(String jsessionId,String nickName);
    void  uoloadPhoneNumber(String jsessionId,String phoneNumber);
}
