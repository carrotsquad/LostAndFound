package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMessageFragment;

public interface IMessagePresenter{
    void getMessageData(String nickname, String userphoto,String username,String jsessionid,int start,int end);
}
