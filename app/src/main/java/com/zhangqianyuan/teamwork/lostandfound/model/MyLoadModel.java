package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendMyHistoryBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IMyUploadModel;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class MyLoadModel extends  BaseModel implements IMyUploadModel {

    public MyLoadModel(){
        super();
    }


    @Override
    public void getMyLoadData(String jsessionid,int start,int end ,Callback<MyHistoryItem> callback) {
        SendMyHistoryBean bean = new SendMyHistoryBean();
        bean.setStart(start);
        bean.setEnd(end);
        api.getMyLoadData(jsessionid,new Gson().toJson(bean)).enqueue(callback);
    }
}
