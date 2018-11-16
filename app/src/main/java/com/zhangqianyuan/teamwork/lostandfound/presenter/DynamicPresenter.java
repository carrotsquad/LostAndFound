package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.model.DynamicModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IDynaicFragment;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes${TODO}
 */
public class DynamicPresenter extends BasePresenter<IDynaicFragment> implements IDynamicPresenter {
    private DynamicModel mDynamicModel;

    public DynamicPresenter(DynamicModel model){
        this.mDynamicModel = model;
    }


    @Override
    public void getDynamicLostData() {
        if (isAttachActivity()){
           // mDynamicModel.getDynamicLostData();
        }
    }


    @Override
    public void getDynamicFindData() {
        if (isAttachActivity()){
           // mDynamicModel.getDynamicFindData();
        }
    }
}
