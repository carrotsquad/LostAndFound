package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.TheLostBean;

import java.util.List;


public interface IMyLoadActivity extends BaseView {
    void showData(List<TheLostBean> bean);
    void showStatus(Boolean seesion);
}
