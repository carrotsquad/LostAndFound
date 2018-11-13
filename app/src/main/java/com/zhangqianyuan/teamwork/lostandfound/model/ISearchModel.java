package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import java.util.List;

import io.reactivex.Observer;

public interface ISearchModel {
    void getSearch(String keyword, String diushileixing, String place, String thingstypes, String session, Observer<SearchBean> searchBeanObserver);
}
