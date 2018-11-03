package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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

    /**
     * Description: 嵌套注册
     */
    @Override
    public void checkCheckCode(String checkcode, String email, String nickname, String password, String phonenumber, String session, IVerifyListener iVerifyListener,Observer<RegisterBean> observer) {
        api.getCheckCode(checkcode,session)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<CheckCodeBean, Observable<RegisterBean>>() {
                    @Override
                    public Observable<RegisterBean> apply(CheckCodeBean checkCodeBean) throws Exception {
                        Integer status = checkCodeBean.getStatus();
                        if(status==200){
                            iVerifyListener.onStatus(true);
                            return api.getRegister(email,password,nickname,phonenumber,session)
                                    .subscribeOn(Schedulers.io())
                                    .unsubscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread());
                        }else {
                            iVerifyListener.onStatus(false);
                            return null;
                        }
                    }
                })
                .subscribe(observer);
    }

//    @Override
//    public void register(, String session, Observer<RegisterBean> observer) {
//        api.getRegister(email,password,nickname,phonenumber,session)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
}
