package com.zhangqianyuan.teamwork.lostandfound.model.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.IsRead;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface IIsReadModel {
    void JudgeIsRead(int commentid, String jsessionid, int isRead, Observer<IsRead> observer);
}
