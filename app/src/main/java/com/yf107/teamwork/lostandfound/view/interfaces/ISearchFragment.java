package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

public interface ISearchFragment extends BaseView{
    void showSearchResult(Boolean status, List<DynamicItemBean> searchItemBeanArrayList);
}
