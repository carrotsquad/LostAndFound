package com.zhangqianyuan.teamwork.lostandfound.presenter;

/**
 * Description
 * 设置中修改电话号码 和昵称
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IEditInfoPresenter {
    void  uploadNeckName(String jsessionId,String nickName);
    void  uoloadPhoneNumber(String jsessionId,String phoneNumber);
}
