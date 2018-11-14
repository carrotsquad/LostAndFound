package com.zhangqianyuan.teamwork.lostandfound.model;

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
public class MyHistoryModel extends BaseModel implements IMyHistoryModel {

    public MyHistoryModel(){
        super();
    }


    @Override
    public void getMyHistoryData(Callback<List<MyHistoryItemBean>> callback) {
        api.getMyHistoryData().enqueue(callback);
    }
}
