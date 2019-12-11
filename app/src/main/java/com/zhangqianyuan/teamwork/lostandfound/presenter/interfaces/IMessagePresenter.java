package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

public interface IMessagePresenter{
    void getMessageData(String usernickname, String userphoto, String username, String jsessionid, int i1, int i);
    void postdelet(int id,String jsessionid);
    void updateIsRead(String session,int lostid);
}
