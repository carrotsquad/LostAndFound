package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;

public interface IReplaceModel {
    void postReplace(String session, TheLostBean bean, List<File> fileList,int id, Observer<StatusBean> observer);
    void postReplace(String session, TheLostBean bean,int id, Observer<StatusBean> observer);
}
