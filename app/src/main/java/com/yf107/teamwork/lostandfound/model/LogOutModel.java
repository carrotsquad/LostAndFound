package com.yf107.teamwork.lostandfound.model;

import com.yf107.teamwork.lostandfound.model.interfaces.ILogOutModel;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;

public class LogOutModel extends BaseModel implements ILogOutModel {

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
