package com.zhangqianyuan.teamwork.lostandfound.presenter;

public interface IThingDetailPresenter {
    void getDataFromWeb(String session, Integer id,int lostid, String time, String content);
}
