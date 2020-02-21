package com.yf107.teamwork.lostandfound.presenter;

import android.util.Log;

import com.yf107.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.yf107.teamwork.lostandfound.bean.SearchBean;
import com.yf107.teamwork.lostandfound.model.DynamicChildModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IDynamicChildPresenter;
import com.yf107.teamwork.lostandfound.view.activity.MainActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IDynaicChildFragment;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
                    if(searchBean!=null&& !searchBean.getStatus().equals(MainActivity.BAD_INTERNET_STATUS)) {
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
    public void getphoto(int lostid) {

    }
}
