package com.zhangqianyuan.teamwork.lostandfound.model;

import android.util.Log;
import android.widget.RelativeLayout;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IVerifyModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description: 注册
 * Created at: 2018/11/3 10:12
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class VerifyModel extends BaseModel implements IVerifyModel {

    public VerifyModel(){
        super();
    }

    @Override
    public void checkCheckCode(String checkcode, String session,Observer<CheckCodeBean> observer) {
        api.getCheckCode(checkcode,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void register(String nickname,String stu, String password, String phonenumber, String session, Observer<RegisterBean> observer) {
        Log.e("registermodel",""+stu+password+"+"+nickname+"+"+phonenumber+session);
        api.getRegister(stu,password,nickname,phonenumber,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
