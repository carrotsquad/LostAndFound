package com.zhangqianyuan.teamwork.lostandfound.network;

import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

// TODO: 2018/11/12 需完善
/**
 * Description: Api
 * Created at: 2018/11/3 11:07
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public interface Api {

    //发送验证码
    @POST("/passlove/user/register/sendCheckCode")
    Observable<SendCheckCodeBean> getSendCheckCode(@Query("mail") String email);

    //核对验证码
    @POST("/passlove/user/register/checkCode")
    Observable<CheckCodeBean> getCheckCode(@Query("checkcode") String ckeckcode, @Query("JSESSIONID") String sessionID);

    //注册
    @POST("/passlove/user/register")
    Observable<RegisterBean> getRegister(@Query("username") String username,@Query("password") String password,@Query("nickname") String nickname,@Query("phonenumber") String phonenumber,@Query("JSESSIONID") String sessionID);

    //登录
    @POST("/passlove/user/loginIn")
    Observable<SignInBean> getSignIn(@Query("username") String email,@Query("password") String password);

    //登录
    @POST("/passlove/user/loginIn")
    Observable<SearchBean> getSearchItem(@Query("keyword") String keyword, @Query("diushileixing") String diushileixing, @Query("place") String place, @Query("thingtype") String thingtype , @Query("JSESSIONID") String sessionID);
}
