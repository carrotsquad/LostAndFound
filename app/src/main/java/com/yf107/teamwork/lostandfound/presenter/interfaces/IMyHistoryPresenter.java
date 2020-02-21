package com.yf107.teamwork.lostandfound.presenter.interfaces;


import com.yf107.teamwork.lostandfound.bean.ImageBean;

public interface IMyHistoryPresenter {
    void getMyHistoryData(String jsessionid, int start, int end);
}
