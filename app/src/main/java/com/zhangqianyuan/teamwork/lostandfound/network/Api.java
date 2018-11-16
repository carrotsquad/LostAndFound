package com.zhangqianyuan.teamwork.lostandfound.network;

import com.zhangqianyuan.teamwork.lostandfound.bean.ChangePhoneNumberBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ChangeUserNickNameBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UploadBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyUpload;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
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

    //搜索
    @POST("/passlove/user/loginIn")
    Observable<SearchBean> getSearchItem(@Query("keyword") String keyword, @Query("diushileixing") String diushileixing, @Query("place") String place, @Query("thingtype") String thingtype , @Query("JSESSIONID") String sessionID);

    //启事详情
    @POST("/passlove/user/loginIn")
    Observable<ThingDetailBean> getThingDetail(@Query("ID") String ID, @Query("JSESSIONID") String session);

    //获取动态 失物 信息
    @POST("")
    @FormUrlEncoded
    Call<DynamicItemBean> getDynamicLostData();

    //获取动态 招领 信息
    @POST("")
    @FormUrlEncoded
    Call<DynamicItemBean> getDynamicFindData();

    //获取我的界面 信息
    @POST("")
    @FormUrlEncoded
    Call<UserInfoBean>    getUserInfoData();

    //获取我的发布 信息
    @POST()
    @FormUrlEncoded
    Call<List<MyLoadItemBean>>  getMyLoadData();

    //获取我的历史 信息
    @POST()
    @FormUrlEncoded
    Call<List<MyHistoryItemBean>> getMyHistoryData();

    /*
    我的发布 包括丢/失
    向服务器传数据
     */
    @POST()
    @FormUrlEncoded
    Call<UploadBean>        postUploadBean(int  lostOrFind,int  thingType, String  byWho,String  uploadTime
    ,String  happendTime,String  where,String  description,List<Integer> thingImg);


    //退出登录
    @POST("passlove/user/loginOut")
    Call<StatusBean>  exitAccount(String jsessionId);

    //修改用户昵称
    @POST("passlove/user/update/nickname")
    Call<StatusBean> uploadNickName(@Body  ChangeUserNickNameBean bean);

    // 修改用户手机号码
    @POST("passlove/user/update/phonenumber")
    Call<StatusBean> uploadPhoneNumber(@Body ChangePhoneNumberBean bean);
}
