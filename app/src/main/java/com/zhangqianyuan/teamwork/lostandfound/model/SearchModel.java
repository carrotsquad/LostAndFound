package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchRequestBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchModel extends BaseModel implements ISearchModel{

    public SearchModel(){
        super();
    }

    @Override
    public void getSearch(String keyword, int diushileixing, int place, int thingstypes, String session, Observer<SearchBean> searchBeanObserver) {
        SearchRequestBean searchRequestBean = new SearchRequestBean();
        searchRequestBean.setStart(0);
        searchRequestBean.setEnd(500);
        searchRequestBean.setKeyword(keyword);
        switch (diushileixing){
            case 0:{
                break;
            }
            case 1:{
                searchRequestBean.setLosttype(0);
                break;
            }
            case 2:{
                searchRequestBean.setLosttype(1);
                break;
            }
            default:{
                break;
            }
        }
        searchRequestBean.setTypeid(thingstypes);
        searchRequestBean.setPlaceid(place);
        api.getSearchItem(new Gson().toJson(searchRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchBeanObserver);
    }
}
