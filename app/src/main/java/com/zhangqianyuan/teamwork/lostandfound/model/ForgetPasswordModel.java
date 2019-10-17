package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IForgetPasswordModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgetPasswordModel extends BaseModel implements IForgetPasswordModel {

    @Override
    public void getInfo(String email, Observer<SendCheckCodeBean> observer) {

    }

    @Override
    public void aaaa(Observer<SendCheckCodeBean> observer, String checkcode, String session) {


    }
}
