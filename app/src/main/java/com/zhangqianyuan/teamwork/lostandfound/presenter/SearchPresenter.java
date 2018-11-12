package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ISearchModel;
import com.zhangqianyuan.teamwork.lostandfound.model.SearchModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISearchFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SearchPresenter implements ISearchPresenter{

    private ISearchModel iSearchModel;
    private ISearchFragment iSearchFragment;

    public SearchPresenter(ISearchFragment iSearchFragment){
        this.iSearchFragment = iSearchFragment;
    }


    @Override
    public void getSearchResult(String keyword, String qishileixing, String place, String thingstypes, String session) {
        iSearchModel = new SearchModel();
        iSearchModel.getSearch(keyword, qishileixing, place, thingstypes, session, new Observer<SearchBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBean searchBean) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
