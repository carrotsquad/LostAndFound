package com.zhangqianyuan.teamwork.lostandfound.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Description: 更新消息的后台服务
 * Created at: 2018/11/13 11:36
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class UpdateMessageService extends Service {
    public UpdateMessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
