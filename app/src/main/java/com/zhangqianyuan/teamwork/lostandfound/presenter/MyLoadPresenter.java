package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyLoadPresenter extends BasePresenter<IMyLoadActivity> implements IMyLoadPresenter {

    private MyLoadModel mMyLoadModel;

    public MyLoadPresenter(MyLoadModel model){
        this.mMyLoadModel=model;
    }


    @Override
    public void getMyloadData() {
        if (isAttachActivity()){
            mMyLoadModel.getMyLoadData();
        }
    }
}
