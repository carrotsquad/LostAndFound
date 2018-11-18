package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ISearchModel;
import com.zhangqianyuan.teamwork.lostandfound.model.SearchModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISearchFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;


/**
 * Description: 搜索Presenter
 * Created at: 2018/11/13 10:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchPresenter extends AbstractBasePresenter<ISearchFragment> implements ISearchPresenter{

    private ISearchModel iSearchModel;

    public SearchPresenter(ISearchFragment iSearchFragment){
        super(iSearchFragment);
    }


    @Override
    public void getSearchResult(String keyword, int qishileixing, int place, int thingstypes, String session) {
        iSearchModel = new SearchModel();
        iSearchModel.getSearch(keyword, qishileixing, place, thingstypes, session, new Observer<SearchBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBean searchBean) {
                if(searchBean!=null&& !searchBean.getStatus().equals(BAD_INTERNET_STATUS)) {
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
