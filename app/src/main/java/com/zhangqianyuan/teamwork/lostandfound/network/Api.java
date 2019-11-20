package com.zhangqianyuan.teamwork.lostandfound.network;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllPlacesBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.AllTypesBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.CommentFeedBack;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserImgBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Description: Api
 * Created at: 2018/11/3 11:07
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public interface Api {

    //发送验证码
    @FormUrlEncoded
    @POST("passlove/register/sendCheckCode")
    Observable<SendCheckCodeBean> getSendCheckCode(@Field("mail") String email);

    //核对验证码
    @FormUrlEncoded
    @POST("passlove/register/checkCode")
    Observable<CheckCodeBean> getCheckCode(@Field("checkcode") String ckeckcode, @Field("JSESSIONID") String sessionID);

    //注册
    @FormUrlEncoded
    @POST("passlove/register")
    Observable<RegisterBean> getRegister(@Field("username") String username,@Field("password") String password,@Field("nickname") String nickname,@Field("phonenumber") String phonenumber,@Field("JSESSIONID") String sessionID);

    //登录
    @FormUrlEncoded
    @POST("/passlove/loginIn")
    Observable<SignInBean> getSignIn(@Field("requestData") String info);

    //搜索
    @FormUrlEncoded
    @POST("/passlove/dynamics/search")
    Observable<SearchBean> getSearchItem(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //启事详情
    @FormUrlEncoded
    @POST("/passlove/user/loginIn")
    Observable<ThingDetailBean> getThingDetail(@Field("ID") String ID, @Field("JSESSIONID") String session);


    //获得所有类型
    @FormUrlEncoded
    @POST("/passlove/info/types")
    Observable<AllTypesBean> getAllTypes(@Field("JSESSIONID") String session);

    //获得所有丢失地点
    @FormUrlEncoded
    @POST("/passlove/info/places")
    Observable<AllPlacesBean> getAllPlaces(@Field("JSESSIONID") String session);

    /*
    我的发布 包括丢/失
    向服务器传数据
     */
    @POST("/passlove/user/publishlost")
    @Multipart
    Observable<StatusBean> postUpload(@Part("JSESSIONID") RequestBody session, @Part("thelost") RequestBody theLostBean, @Part MultipartBody.Part photos);


    @POST("/passlove/user/publishlost")
    @FormUrlEncoded
    Observable<StatusBean> postUpload(@Field("JSESSIONID") String session, @Field("thelost") String theLostBean);

    /*
    删除
     */
    @POST("/passlove/updateishandle/{id}")
    @FormUrlEncoded
    Observable<StatusBean> postSuccess(@Field("JSESSIONID")String jsessionid,@Path("id") int id);

    //获取动态 失物今天 信息
    @POST("/passlove/dynamics/0/0")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostTodayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 失物昨天 信息
    @POST("/passlove/dynamics/0/1")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostYesterdayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 失物更早 信息
    @POST("/passlove/dynamics/0/2")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostAgoData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 招领今天 信息
    @POST("/passlove/dynamics/1/0")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicFindTodayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 招领昨天 信息
    @POST("/passlove/dynamics/1/1")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicFindYesterdayData(@Field("requestData") String info, @Field("JSESSIONID") String session);


    //获取动态 招领更早 信息
    @POST("/passlove/dynamics/1/2")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicFindAgoData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取我的发布 信息
    @POST("passlove/user/mypublish")
    @FormUrlEncoded
    Call<MyHistoryItem>  getMyLoadData(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);

    //获取我的历史 信息
    @POST("passlove/user/myhistory")
    @FormUrlEncoded
    Call<MyHistoryItem> getMyHistoryData(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);

    //获取评论过的消息
    @POST("/passlove/dynamics/commented")
    @FormUrlEncoded
    Observable<SearchBean> getMyCommentedData(@Field("JSESSIONID")String jsessionid);


    //退出登录
    @FormUrlEncoded
    @POST("passlove/user/loginOut")
    Call<StatusBean>  exitAccount(@Field("JSESSIONID") String jsessiond);

    //修改用户昵称
    @FormUrlEncoded
    @POST("passlove/user/update/nickname")
    Call<StatusBean> uploadNickName(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);

    // 修改用户手机号码
    @FormUrlEncoded
    @POST("passlove/user/update/phonenumber")
    Call<StatusBean> uploadPhoneNumber(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);

    //修改头像
    @Multipart
    @POST("/passlove/user/update/photo")
    Call<UserImgBean> uploadHeadImg(@Part("JSESSIONID") RequestBody JSESSIONID,
                                    @Part MultipartBody.Part photo);

    //发表评论
    @FormUrlEncoded
    @POST("passlove/comment/publish")
    Call<StatusBean> uploadComment(@Field("JSESSIONID") String jsessionid,@Field("requestData") String bean);

    //展示评论
    @FormUrlEncoded
    @POST("passlove/comment/get")
    Call<CommentFeedBack>  getComment(@Field("JSESSIONID") String jsessionid,@Field("lostid") int bean);

}
