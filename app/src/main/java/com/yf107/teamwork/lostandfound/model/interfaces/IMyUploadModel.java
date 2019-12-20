package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.bean.StatusBean;

import io.reactivex.Observer;
import retrofit2.Callback;

public interface IMyUploadModel {
    /**
     * 需要获得的数据
     * littleicon_type_lost/失  图象
     * 物品类型图片
     * 地点
     * 时间
     * 描述
     * 物品具体图片
     */
    void getMyLoadData(String jsessionid,int start,int end,Callback<MyHistoryItem> callback);
    void postsuccess(String jsessionid,int id, Observer<StatusBean> observer);
    void postdelete(String jsessionid,int id, Observer<StatusBean> observer);
}