package com.zhangqianyuan.teamwork.lostandfound.event;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

/**
 * @Description: eventbus更新消息
 * Created at: 2019/2/22 12:19
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */
public class MessageEvent {

    private Boolean status;
    private List<DynamicItemBean> dynamicItemBeanList;

    public MessageEvent(Boolean status,List<DynamicItemBean> dynamicItemBeanList){
        this.status=status;
        this.dynamicItemBeanList=dynamicItemBeanList;
    }

    public void setDynamicItemBeanList(List<DynamicItemBean> dynamicItemBeanList) {
        this.dynamicItemBeanList = dynamicItemBeanList;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public List<DynamicItemBean> getDynamicItemBeanList() {
        return dynamicItemBeanList;
    }
}
