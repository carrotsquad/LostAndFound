package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel extends BaseModel implements ISearchModel{

    public SearchModel(){
        super();
    }

    @Override
    public void getSearch(String keyword, String diushileixing, String place, String thingstypes, String session, Observer<SearchBean> searchBeanObserver) {
        api.getSearchItem(keyword,diushileixing,place,thingstypes,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchBeanObserver);
    }
}
