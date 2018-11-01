package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;

import java.util.function.Consumer;

public interface ILogInModel {
    void getInfo(String email, Consumer<SendCheckCodeBean> consumer);
}
