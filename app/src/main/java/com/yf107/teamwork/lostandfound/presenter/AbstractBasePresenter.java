package com.yf107.teamwork.lostandfound.presenter;

import com.yf107.teamwork.lostandfound.view.interfaces.BaseView;

public abstract class AbstractBasePresenter <V extends BaseView>{
    protected V  v;

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
