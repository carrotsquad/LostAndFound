package com.yf107.teamwork.lostandfound.event;

import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;

import java.util.List;

/**
 * @Description: eventbus更新消息
 * */

public class MessageEvent {

    private Boolean status;
    private List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList;

    public MessageEvent(Boolean status,List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList){
        this.status=status;
        this.dynamicItemBeanList=dynamicItemBeanList;
    }

    public void setDynamicItemBeanList(List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList) {
        this.dynamicItemBeanList = dynamicItemBeanList;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public List<UpDateMessageBean.DynamicsBeanX> getDynamicItemBeanList() {
        return dynamicItemBeanList;
    }
}
