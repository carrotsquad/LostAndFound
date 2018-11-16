package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LogOutModel extends BaseModel implements ILogOutModel{

    public LogOutModel(){
        super();
    }


    @Override
    public void logOut(String session, Observer<StatusBean> observer) {
//        api.getLogOut(session)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }
}
