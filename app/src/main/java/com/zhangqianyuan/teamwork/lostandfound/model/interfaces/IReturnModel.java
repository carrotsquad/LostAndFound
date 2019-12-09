package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.ReturnBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;

public interface IReturnModel {
    void sendMessage(String session, ReturnBean bean, Observer<StatusBean> observer);
}
