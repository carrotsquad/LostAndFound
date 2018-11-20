package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import retrofit2.Callback;

/**
 * Description
 * 动态  子Fragment model
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DynamicModel extends BaseModel implements IDynamicModel {
    public DynamicModel (){
        super();
    }

    @Override
    public void getDynamicLostData(int headImg, String thingtype, String time, String where, String description, Callback<DynamicItemBean> callback) {
     //   api.getDynamicLostData().enqueue(callback);
    }

    @Override
    public void getDynamicFindData(int headImg, String thingtype, String time, String where, String description, Callback<DynamicItemBean> callback) {
     //   api.getDynamicFindData().enqueue(callback);
    }


}
