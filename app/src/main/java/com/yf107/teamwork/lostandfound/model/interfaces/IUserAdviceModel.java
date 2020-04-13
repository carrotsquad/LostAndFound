package com.yf107.teamwork.lostandfound.model.interfaces;

import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.model.BaseModel;

import java.util.Observable;

import io.reactivex.Observer;

public interface IUserAdviceModel {
    void sendAdvide(String session, String advice, Observer<CheckCodeBean> observer);
}
