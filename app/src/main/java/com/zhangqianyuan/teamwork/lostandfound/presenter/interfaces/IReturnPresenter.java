package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.ReturnBean;

public interface IReturnPresenter {
    void sendMessage(String session, ReturnBean bean);
}