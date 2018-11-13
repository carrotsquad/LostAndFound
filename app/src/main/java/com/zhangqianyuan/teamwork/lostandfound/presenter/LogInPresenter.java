package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.annotation.SuppressLint;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.ILogInModel;
import com.zhangqianyuan.teamwork.lostandfound.model.LogInModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ILogInActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 注册Presenter
 * Created at: 2018/11/13 10:53
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class LogInPresenter implements ILogInPresenter{

    private ILogInModel iLogInModel;
    private ILogInActivity iLogInActivity;

    public LogInPresenter(ILogInActivity iLogInActivity){
        this.iLogInActivity = iLogInActivity;
    }

    @SuppressLint("NewApi")
    @Override
    public void getCodeStatus(String email) {
        iLogInModel = new LogInModel();
        iLogInModel.getInfo(email, new Observer<SendCheckCodeBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SendCheckCodeBean sendCheckCodeBean) {
                iLogInActivity.showEmailStatus(sendCheckCodeBean.getStatus(),sendCheckCodeBean.getJSESSIONID());
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
