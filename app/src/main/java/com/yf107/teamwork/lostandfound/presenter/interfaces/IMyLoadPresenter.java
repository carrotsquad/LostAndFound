package com.yf107.teamwork.lostandfound.presenter.interfaces;


public interface IMyLoadPresenter  {
    void getMyloadData(String jsessionid,int start,int end);
    void postSuccess(String jessionid ,int id);
    void postDelete(String jessionid ,int id);
}
