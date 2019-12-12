package com.zhangqianyuan.teamwork.lostandfound.event;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.NewDynamicsBeam;
import com.zhangqianyuan.teamwork.lostandfound.bean.UpDateMessageBean;

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
