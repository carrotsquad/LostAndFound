package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.IsRead;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IIsReadModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class IsReadModel extends BaseModel implements IIsReadModel {

    public IsReadModel(){
        super();
    }

    @Override
    public void JudgeIsRead(int commentid, String jsessionid, int isRead, Observer<IsRead> observer) {
        api.updateMessageIsRead(commentid,jsessionid,isRead)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
