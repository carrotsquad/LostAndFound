package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.NewDynamicsBeam;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UpDateMessageBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.ICommentedMessageModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description: 获取评论过的消息
 * Created at: 2018/11/24 16:13
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class CommentedMessageModel extends BaseModel implements ICommentedMessageModel {

    public CommentedMessageModel() {
        super();
    }


    @Override
    public void getMyCommentedData(String jsessionid, Observer<UpDateMessageBean> observer) {
        api.getMyCommentedData(jsessionid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    public void postdelete(String jsessionid, int id, Observer<StatusBean> observer) {
        api.postDelete(jsessionid,id)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void getupdateIsRead(String session, int lostid, Observer<StatusBean> observer) {
        api.updateMessageIsRead(session,lostid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
