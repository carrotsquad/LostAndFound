package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IMyLoadPresenter  {
    void getMyloadData(String jsessionid,int start,int end);
    void postSuccess(String jessionid ,int id);
    void postDelete(String jessionid ,int id);
}
