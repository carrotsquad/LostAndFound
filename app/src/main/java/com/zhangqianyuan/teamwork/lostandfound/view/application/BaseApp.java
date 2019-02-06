package com.zhangqianyuan.teamwork.lostandfound.view.application;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created at: 2019/2/9 10:58
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version: 0
 * @Description: 用来测试内存泄漏
 * @updateAuthor:
 * @updateDes:
 */
public class BaseApp extends Application {

    //实现数据共享,共享Handler
    private Handler handler=null;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
//        // TODO: 2018/11/22 用来检测内存泄漏,发布时删了
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

}
