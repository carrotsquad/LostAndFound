package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllTypesBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllTypesModel extends BaseModel implements IAllTypesModel{

    public AllTypesModel(){
        super();
    }

    @Override
    public void getAllTypes(String session, Observer<AllTypesBean> observer) {
        api.getAllTypes(session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
