package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;

public interface ISearchModel {
    void getSearch(String keyword, int diushileixing, int place, int thingstypes, String session, Observer<SearchBean> searchBeanObserver);
}
