package com.yf107.teamwork.lostandfound.presenter.interfaces;

import com.yf107.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

public interface IUploadPresenter {
    void postUpload(String session, TheLostBean bean);
    void postUpload(String session, TheLostBean bean, List<File> fileList);
    void cardUpload(String stu,String session, TheLostBean bean);
    void cardUpload(String stu,String session, TheLostBean bean, List<File> fileList);
    //发表评论
   // void sendDataToWeb(String session, Integer id,int lostid, String time, String content);
}
