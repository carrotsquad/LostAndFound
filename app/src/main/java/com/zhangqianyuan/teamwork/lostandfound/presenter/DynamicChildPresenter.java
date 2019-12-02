package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.DynamicChildModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IDynamicChildPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IDynaicChildFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class DynamicChildPresenter extends BasePresenter<IDynaicChildFragment> implements IDynamicChildPresenter {
    private DynamicChildModel mDynamicModel;

    public DynamicChildPresenter(){
        this.mDynamicModel = new DynamicChildModel();
    }

    @Override
    public void getDynamicLostTodayData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicLostTodayData(dynamicsRequestBean, session, new Observer<SearchBean>() {
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
    public void getDynamicLostYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicLostYesterdayData(dynamicsRequestBean, session, new Observer<SearchBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(SearchBean searchBean) {
                    if(searchBean!=null&& !searchBean.getStatus().equals(BAD_INTERNET_STATUS)) {
                        getV().showData(true,searchBean.getDynamics());
                        Log.e("DynamicPresenter","Presenter完好");
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
    public void getDynamicLostAgoData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicLostAgoData(dynamicsRequestBean, session, new Observer<SearchBean>() {
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
    public void getDynamicFindTodayData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicFindTodayData(dynamicsRequestBean, session, new Observer<SearchBean>() {
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
    public void getDynamicFindYesterdayData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicFindYesterdayData(dynamicsRequestBean, session, new Observer<SearchBean>() {
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
    public void getDynamicFindAgoData(DynamicsRequestBean dynamicsRequestBean, String session) {
        if(isAttachActivity()){
            mDynamicModel.getDynamicFindAgoData(dynamicsRequestBean, session, new Observer<SearchBean>() {
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
