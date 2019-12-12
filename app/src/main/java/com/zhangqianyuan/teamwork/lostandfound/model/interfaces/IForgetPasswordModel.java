package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.model.BaseModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface IForgetPasswordModel{
    void sendCode(String email,Observer<SendCheckCodeBean> observer);
    void checkEmail(String session,String checkcode,Observer<CheckCodeBean> observer);
    void getNewPassword(String password, String email,String session, Observer<CheckCodeBean> observer);
}
