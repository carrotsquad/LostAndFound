package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.model.MyHistoryModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyHistoryActivity;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyHistoryPresenter extends BasePresenter<IMyHistoryActivity> implements IMyHistoryPresenter {
    private MyHistoryModel mMyHistoryModel;

    public MyHistoryPresenter(MyHistoryModel model){
        this.mMyHistoryModel = model;
    }


    @Override
    public void getMyHistoryData() {
        if (isAttachActivity()){
            mMyHistoryModel.getMyHistoryData();
        }
    }
}
