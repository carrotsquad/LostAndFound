package com.zhangqianyuan.teamwork.lostandfound.model;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyHistoryModel extends BaseModel implements IMyHistoryModel {

    public MyHistoryModel(){
        super();
    }

    @Override
    public void getMyHistoryData() {
        api.getMyHistoryData();
    }
}
