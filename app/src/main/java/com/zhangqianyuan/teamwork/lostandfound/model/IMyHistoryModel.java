package com.zhangqianyuan.teamwork.lostandfound.model;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;

import java.util.List;

import retrofit2.Callback;
/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IMyHistoryModel {
    void getMyHistoryData(String jsessionid,int start,int end,Callback<MyHistoryItem> callback);
}
