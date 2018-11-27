package com.zhangqianyuan.teamwork.lostandfound.model;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import retrofit2.Callback;
/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IUploadModel {
    void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<StatusBean> observer);
    void postUpload(String session, TheLostBean bean, Observer<StatusBean> observer);
}
