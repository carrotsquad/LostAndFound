package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import java.util.List;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyLoadModel extends  BaseModel implements IMyUploadModel{

    public MyLoadModel(){
        super();
    }


    @Override
    public void getMyLoadData(Callback<List<MyLoadItemBean>> callback) {
        api.getMyLoadData().enqueue(callback);
    }
}
