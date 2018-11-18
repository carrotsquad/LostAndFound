package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $zhangqianyuan$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/19 需要完善
public class UploadModel extends BaseModel implements IUploadModel {
    public UploadModel(){
        super();
    }

    @Override
    public void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<StatusBean> observer) {

    }
}
