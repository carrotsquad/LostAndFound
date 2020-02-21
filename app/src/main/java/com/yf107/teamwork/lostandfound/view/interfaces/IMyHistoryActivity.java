package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.ImageBean;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;

import java.util.List;


public interface IMyHistoryActivity extends BaseView {
    void showData(List<TheLostBean> beans);
}
