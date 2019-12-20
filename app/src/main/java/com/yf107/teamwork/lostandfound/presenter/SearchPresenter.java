package com.yf107.teamwork.lostandfound.presenter;

import android.support.annotation.Nullable;

import com.yf107.teamwork.lostandfound.bean.SearchBean;
import com.yf107.teamwork.lostandfound.model.SearchModel;
import com.yf107.teamwork.lostandfound.model.interfaces.ISearchModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.ISearchPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.ISearchFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Description: 搜索Presenter

 */
public class SearchPresenter extends AbstractBasePresenter<ISearchFragment> implements ISearchPresenter {

    private ISearchModel iSearchModel;

    public SearchPresenter(ISearchFragment iSearchFragment){
        super(iSearchFragment);
    }

    @Override
    public void getSearchResult(String keyword, @Nullable Integer qishileixing, @Nullable Integer place, @Nullable Integer thingstypes, String session) {
        iSearchModel = new SearchModel();
        iSearchModel.getSearch(keyword, qishileixing, place, thingstypes, session, new Observer<SearchBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBean searchBean) {
                if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
                    v.showSearchResult(true,searchBean.getDynamics());
                }else {
                    v.showSearchResult(false,searchBean.getDynamics());
                }
            }

            @Override
            public void onError(Throwable e) {
                v.showSearchResult(false,null);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
