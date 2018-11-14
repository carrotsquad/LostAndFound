package com.zhangqianyuan.teamwork.lostandfound.presenter;

import com.zhangqianyuan.teamwork.lostandfound.model.BaseModel;
import com.zhangqianyuan.teamwork.lostandfound.model.UploadModel;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUploadActivity;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UploadPresenter extends BasePresenter<IUploadActivity> implements  IUploadPresenter {
    private UploadModel mUploadModel;

    public UploadPresenter(UploadModel model){
        this.mUploadModel =model;
    }


    @Override
    public void postUpload() {
        mUploadModel.postUpload();
    }
}
