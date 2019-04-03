package com.zhangqianyuan.teamwork.lostandfound.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.event.MessageEvent;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MessagePresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMessageFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.NICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * Description: 更新消息的后台服务，服务只能绑定在activity上
 * Created at: 2018/11/13 11:36
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class UpdateMessageService extends Service implements IMessageFragment {

    private MessagePresenter messagePresenter;

    private UpdateMessageListenser updateMessageListenser;
    private MsgBinder msgBinder=new MsgBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        messagePresenter=new MessagePresenter(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, START_STICKY_COMPATIBILITY, startId);
    }

    /**
     * 开始服务
     */
    public void startService() {
        //每隔30秒轮询
        Disposable disposable = Observable.interval(30,30, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        SharedPreferences sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
                        String username = sharedPreferences.getString(EMAIL,"null");
                        String userphoto = sharedPreferences.getString(USERPHOTO,"null");
                        String usernickname = sharedPreferences.getString(NICKNAME,"null");
                        String jsessionid = sharedPreferences.getString(SESSION,"null");
                        messagePresenter.getMessageData(usernickname,userphoto,username,jsessionid,0,500);
                    }
                });
    }

    //messagePresenter的数据回调
    @Override
    public void onDataBack(Boolean status, List<DynamicItemBean> dynamicItemBeanList) {
        if(updateMessageListenser!=null){
            updateMessageListenser.UpdateMessage(new MessageEvent(status,dynamicItemBeanList));
        }
    }

    public void setUpdateMessageListenser(UpdateMessageListenser listenser){
        this.updateMessageListenser=listenser;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return msgBinder;
    }

    public class MsgBinder extends Binder{
        public UpdateMessageService getService(){
            return UpdateMessageService.this;
        }
    }

    public interface UpdateMessageListenser{
        void UpdateMessage(MessageEvent messageEvent);
    }
}
