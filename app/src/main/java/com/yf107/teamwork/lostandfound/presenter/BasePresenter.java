package com.yf107.teamwork.lostandfound.presenter;

import com.yf107.teamwork.lostandfound.view.interfaces.BaseView;

/**
 * Description
 * presenter基类 用于管理View
 */
public class BasePresenter<V extends BaseView> {
    private V  v;
    public void attachActivity(V  v1){
        this.v = v1;
    }

    public void dettachActivity(){
        this.v=null;
    }

    public boolean isAttachActivity(){
        return this.v!=null;
    }

    public V getV(){
        return  this.v;
    }
}
