package com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IUploadPresenter {
    void postUpload(String session, TheLostBean bean);
    void postUpload(String session, TheLostBean bean, List<File> fileList);
    void cardUpload(String stu,String session, TheLostBean bean);
    void cardUpload(String stu,String session, TheLostBean bean, List<File> fileList);
    //发表评论
    void sendDataToWeb(String session, Integer id,int lostid, String time, String content);
}
