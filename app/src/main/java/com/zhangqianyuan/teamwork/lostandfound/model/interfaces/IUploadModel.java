package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUploadModel {
    void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<StatusBean> observer);
    void postUpload(String session, TheLostBean bean, Observer<StatusBean> observer);
}
