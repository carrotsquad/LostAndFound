package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import com.zhangqianyuan.teamwork.lostandfound.model.IThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.model.ThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 失物/招领启事详情Presenter
 * Created at: 2018/11/13 13:28
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class ThingDetailPresenter extends AbstractBasePresenter<IThingDetailActivity> implements IThingDetailPresenter {

    private IThingDetailModel iThingDetailModel;

    public ThingDetailPresenter(IThingDetailActivity iThingDetailActivity){
        super(iThingDetailActivity);
    }


    // TODO: 2018/11/13 需完善逻辑
    @Override
    public void getDataFromWeb(String ID, String session) {
        iThingDetailModel = new ThingDetailModel();
        iThingDetailModel.getInternetData(ID, session, new Observer<ThingDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ThingDetailBean thingDetailBean) {

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
