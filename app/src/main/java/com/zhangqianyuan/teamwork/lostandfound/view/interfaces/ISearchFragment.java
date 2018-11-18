package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

public interface ISearchFragment extends BaseView{
    void showSearchResult(Boolean status, List<DynamicItemBean> searchItemBeanArrayList);
}
