package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;

import retrofit2.Callback;
/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IMyHistoryModel {
    void getMyHistoryData(String jsessionid,int start,int end,Callback<MyHistoryItem> callback);
}
