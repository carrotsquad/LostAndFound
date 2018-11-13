package com.zhangqianyuan.teamwork.lostandfound.model;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThingDetailModel extends BaseModel implements IThingDetailModel{

    public ThingDetailModel(){
        super();
    }

    @Override
    public void getInternetData(String ID, String session, Observer observer) {
        api.getThingDetail(ID, session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
