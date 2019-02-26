package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IDynamicModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description
 * 动态  子Fragment model
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class DynamicModel extends BaseModel implements IDynamicModel {
    public DynamicModel (){
        super();
    }

    @Override
    public void getDynamicLostData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicLostData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getDynamicFindData(DynamicsRequestBean dynamicsRequestBean, String session, Observer<SearchBean> observer) {
        api.getDynamicFindData(new Gson().toJson(dynamicsRequestBean),session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
