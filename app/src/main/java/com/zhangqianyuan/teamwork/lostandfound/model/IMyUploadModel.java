package com.zhangqianyuan.teamwork.lostandfound.model;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;

import java.util.List;

import retrofit2.Callback;
/**
 * Description
 * 我的 我的发布model
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
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
    void getMyLoadData(Callback<List<MyLoadItemBean>> callback);
}
