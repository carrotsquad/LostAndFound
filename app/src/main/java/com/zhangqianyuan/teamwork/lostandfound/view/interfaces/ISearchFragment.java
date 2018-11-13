package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItemBean;

import java.util.List;

public interface ISearchFragment {
    void showSearchResult(Boolean status, List<SearchItemBean> searchItemBeanArrayList);
}
