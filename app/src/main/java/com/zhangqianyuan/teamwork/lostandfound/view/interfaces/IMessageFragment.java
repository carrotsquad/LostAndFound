package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

public interface IMessageFragment extends BaseView{
    void onDataBack(Boolean status, List<DynamicItemBean> dynamicItemBeanList);
    void isRead(Boolean status, int isread);
}
