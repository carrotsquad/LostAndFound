package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.support.annotation.Nullable;

public interface ISearchPresenter {
    void getSearchResult(String keyword, @Nullable Integer qishileixing, @Nullable Integer place, @Nullable Integer thingstypes, String session);
}
