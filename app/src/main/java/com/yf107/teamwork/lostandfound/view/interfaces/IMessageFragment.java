package com.yf107.teamwork.lostandfound.view.interfaces;

import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;

import java.util.List;

public interface IMessageFragment extends BaseView{
    void onDataBack(Boolean status, List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList);
    void isRead(boolean status);
    void showStatus(Boolean seesion);
}
