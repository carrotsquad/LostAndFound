package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyHistoryItem {

    /**
     * status : 200
     * data : [{"id":8,"typeid":4,"losttype":0,"title":"失物发布测试6","description":"的JFK但是JFK速度加快分解速度快","placeid":10,"publishtime":"20181110221944","losttime":"2018111013","photo":"8.jpg","ishandled":1}]
     */

    private int status;
    private List<TheLostBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TheLostBean> getData() {
        return data;
    }

    public void setData(List<TheLostBean> data) {
        this.data = data;
    }
    
}
