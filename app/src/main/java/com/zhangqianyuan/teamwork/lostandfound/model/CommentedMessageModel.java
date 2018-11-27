package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;

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
    public void getMyCommentedData(String jsessionid, Observer<SearchBean> observer) {
        api.getMyCommentedData(jsessionid)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

}
