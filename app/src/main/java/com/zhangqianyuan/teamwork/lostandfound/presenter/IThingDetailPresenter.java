package com.zhangqianyuan.teamwork.lostandfound.presenter;

public interface IThingDetailPresenter {
    //发表评论
    void sendDataToWeb(String session, Integer id,int lostid, String time, String content);

    //得到评论
    void getDataFromWeb(String session,int lostid,int start,int end);

}
