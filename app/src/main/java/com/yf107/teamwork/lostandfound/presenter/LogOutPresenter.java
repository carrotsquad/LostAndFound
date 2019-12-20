package com.yf107.teamwork.lostandfound.presenter;

import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.model.LogOutModel;
import com.yf107.teamwork.lostandfound.model.interfaces.ILogOutModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.ILogOutPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.ILogOut;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.yf107.teamwork.lostandfound.view.activity.MainActivity.FINE_INTERNET_STATUS;

public class LogOutPresenter extends AbstractBasePresenter<ILogOut> implements ILogOutPresenter {

    private ILogOut iLogOut;
    private ILogOutModel iLogOutModel;

    public LogOutPresenter(ILogOut iLogOut){
        super(iLogOut);
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
                    v.logOut(false);
                }else {
                    v.logOut(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                v.logOut(false);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
