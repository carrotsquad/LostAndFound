package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.NewDynamicsBeam;
import com.zhangqianyuan.teamwork.lostandfound.bean.UpDateMessageBean;

import java.util.List;

public interface IMessageFragment extends BaseView{
    void onDataBack(Boolean status, List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList);
    void isRead(boolean status);
    void showStatus(Boolean seesion);
}
