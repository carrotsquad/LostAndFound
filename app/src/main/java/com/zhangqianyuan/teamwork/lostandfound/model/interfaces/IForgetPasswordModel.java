package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.BaseModel;

import io.reactivex.Observer;

public interface IForgetPasswordModel{
    void getInfo(String email, Observer<SendCheckCodeBean> observer);
    void CheckCode(Observer<CheckCodeBean> observer, String checkcode, String session);
}
