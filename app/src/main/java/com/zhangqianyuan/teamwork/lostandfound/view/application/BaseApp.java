package com.zhangqianyuan.teamwork.lostandfound.view.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Description: 用来测试内存泄漏
 * Created at: 2018/11/22 22:46
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        // TODO: 2018/11/22 用来检测内存泄漏,发布时删了
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
