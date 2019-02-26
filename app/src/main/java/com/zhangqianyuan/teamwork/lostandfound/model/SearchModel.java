package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.ISearchModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel extends BaseModel implements ISearchModel {

    public SearchModel(){
        super();
    }

    @Override
    public void getSearch(String keyword, int diushileixing, int place, int thingstypes, String session, Observer<SearchBean> searchBeanObserver) {
        SearchRequestBean searchRequestBean = new SearchRequestBean();
        searchRequestBean.setStart(0);
        searchRequestBean.setEnd(1000000);
        searchRequestBean.setKeyword(keyword);
        searchRequestBean.setLosttype(diushileixing);
        searchRequestBean.setTypeid(thingstypes);
        searchRequestBean.setPlaceid(place);

        api.getSearchItem(new Gson().toJson(searchRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchBeanObserver);
    }
}
