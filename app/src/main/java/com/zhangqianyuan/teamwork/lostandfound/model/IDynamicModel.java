package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IDynamicModel {
     /**
      * 获取失物动态
      * @param headImg  用户头像
      * @param thingtype  失物类型
      * @param time      丢失时间
      * @param where     丢失地点
      * @param description  描述
      */
     void  getDynamicLostData(int headImg, String thingtype, String time, String where, String description, Callback<DynamicItemBean> callback);

     /**
      * 获取招领动态
      * @param headImg
      * @param thingtype
      * @param time
      * @param where
      * @param description
      */
     void  getDynamicFindData(int headImg,String thingtype,String time,String where,String description ,Callback<DynamicItemBean> callback);
}
