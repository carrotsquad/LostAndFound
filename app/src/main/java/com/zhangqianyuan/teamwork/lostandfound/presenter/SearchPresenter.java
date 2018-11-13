package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ISearchModel;
import com.zhangqianyuan.teamwork.lostandfound.model.SearchModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISearchFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Description: 搜索Presenter
 * Created at: 2018/11/13 10:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
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
                if(searchBean!=null&&searchBean.getStatus()!=400) {
                    iSearchFragment.showSearchResult(true,searchBean.getSearchItemBeanList());
                }else {
                    iSearchFragment.showSearchResult(false,searchBean.getSearchItemBeanList());
                }
            }

            @Override
            public void onError(Throwable e) {
                iSearchFragment.showSearchResult(false,null);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
