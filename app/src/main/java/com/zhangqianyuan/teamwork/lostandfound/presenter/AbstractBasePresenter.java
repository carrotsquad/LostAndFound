package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.app.Activity;

import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.BaseView;

public abstract class AbstractBasePresenter <V extends BaseView>{
    V  v;

    AbstractBasePresenter(V v){
        this.v = v;
    }

    public void dettachActivity(){
        this.v=null;
    }

    public boolean isAttachActivity(){
        return this.v!=null;
    }

}
