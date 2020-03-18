package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.CommentFeedBack;

import java.util.List;

public interface IThingDetailActivity extends BaseView{
    //发送信息
    void sendDataToWeb(int status);

    //展示消息
    void getDataFromWeb(List<CommentFeedBack.Comments> list);
    void showStatus(Boolean status);

}
