package com.yf107.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.bean.SearchRequestBean1;
import com.yf107.teamwork.lostandfound.model.interfaces.ISearchModel;
import com.yf107.teamwork.lostandfound.bean.SearchBean;
import com.yf107.teamwork.lostandfound.bean.SearchRequestBean;

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

    @Override
    public void getSearch1(String keyword, int place, int thingstypes, String session, Observer<SearchBean> searchBeanObserver) {
        SearchRequestBean1 searchRequestBean = new SearchRequestBean1();
        searchRequestBean.setStart(0);
        searchRequestBean.setEnd(1000000);
        searchRequestBean.setKeyword(keyword);
        searchRequestBean.setTypeid(thingstypes);
        searchRequestBean.setPlaceid(place);

        api.getSearchItem1(new Gson().toJson(searchRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchBeanObserver);
    }
}
