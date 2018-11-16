package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ILogOutModel;
import com.zhangqianyuan.teamwork.lostandfound.model.LogOutModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ILogOut;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

public class LogOutPresenter implements ILogOutPresenter{

    private ILogOut iLogOut;
    private ILogOutModel iLogOutModel;

    public LogOutPresenter(ILogOut iLogOut){
        this.iLogOut = iLogOut;
    }

    @Override
    public void getLogOut(String session) {
        iLogOutModel = new LogOutModel();
        iLogOutModel.logOut(session, new Observer<StatusBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatusBean statusBean) {
                if(statusBean ==null|| !statusBean.getStatus().equals(FINE_INTERNET_STATUS)){
                    iLogOut.logOut(false);
                }else {
                    iLogOut.logOut(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                iLogOut.logOut(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
