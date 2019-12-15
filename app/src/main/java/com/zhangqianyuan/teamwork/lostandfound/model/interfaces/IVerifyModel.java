package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observer;

public interface IVerifyModel {
    void checkCheckCode(String checkcode, String session, Observer<CheckCodeBean> observer);
    void register(String nickname,String stu, String password, String phonenumber, String session, Observer<RegisterBean> observer);
}
