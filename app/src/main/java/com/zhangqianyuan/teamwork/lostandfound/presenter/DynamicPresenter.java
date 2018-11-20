package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.DynamicModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IDynaicFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes${TODO}
 */
public class DynamicPresenter extends BasePresenter<IDynaicFragment> implements IDynamicPresenter {
    private DynamicModel mDynamicModel;

    public DynamicPresenter(){
        this.mDynamicModel = new DynamicModel();
    }

    @Override
    public void getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicLostData(dynamicsRequestBean, session, new Observer<SearchBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SearchBean searchBean) {
                    if(searchBean!=null&& !searchBean.getStatus().equals(BAD_INTERNET_STATUS)) {
                        getV().showData(true,searchBean.getDynamics());
                    }else {
                        getV().showData(false,searchBean.getDynamics());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    getV().showData(false,null);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }

    @Override
    public void getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicFindData(dynamicsRequestBean, session, new Observer<SearchBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SearchBean searchBean) {
                    if(searchBean!=null&& !searchBean.getStatus().equals(BAD_INTERNET_STATUS)) {
                        getV().showData(true,searchBean.getDynamics());
                    }else {
                        getV().showData(false,searchBean.getDynamics());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    getV().showData(false,null);
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
