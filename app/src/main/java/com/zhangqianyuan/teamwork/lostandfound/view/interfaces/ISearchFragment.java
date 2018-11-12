package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItem;

import java.util.ArrayList;

public interface ISearchFragment {
    void showSearchResult(Boolean status, ArrayList<SearchItem> searchItemArrayList);
}
