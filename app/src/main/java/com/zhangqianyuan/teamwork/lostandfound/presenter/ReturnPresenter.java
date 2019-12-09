package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.ReturnBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ReturnModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IReturnPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IReturnActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

public class ReturnPresenter extends BasePresenter<IReturnActivity> implements IReturnPresenter {

    private ReturnModel mReturnModel;

    public ReturnPresenter(){
        this.mReturnModel =new ReturnModel();
    }

    @Override
    public void sendMessage(String session, ReturnBean bean) {
        if (isAttachActivity()) {
            mReturnModel.sendMessage(session, bean, new Observer<StatusBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(StatusBean statusBean) {
                    if (statusBean == null || !statusBean.getStatus().equals(FINE_INTERNET_STATUS)) {
                        getV().showStatus(false);
                    } else {
                        getV().showStatus(true);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    getV().showStatus(false);
                }

                @Override
                public void onComplete() {

                }
            });
        }

    }
}