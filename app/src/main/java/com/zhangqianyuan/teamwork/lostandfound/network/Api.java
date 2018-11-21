package com.zhangqianyuan.teamwork.lostandfound.network;

import com.zhangqianyuan.teamwork.lostandfound.bean.AllPlacesBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.AllTypesBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ChangePhoneNumberBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ChangeUserNickNameBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.CheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.LoginBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.RegisterBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ThingDetailBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UploadBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyUpload;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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
    Observable<StatusBean> postUpload(@Part("JSESSIONID") RequestBody session, @Part("thelost") RequestBody theLostBean, @PartMap Map<String,RequestBody> photos);

    //获取动态 失物 信息
    @POST("/passlove/dynamics/0")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 招领 信息
    @POST("/passlove/dynamics/1")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicFindData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取我的发布 信息
    @POST("passlove/user/mypublish")
    @FormUrlEncoded
    Call<MyHistoryItem>  getMyLoadData(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);

    //获取我的历史 信息
    @POST("passlove/user/myhistory")
    @FormUrlEncoded
    Call<MyHistoryItem> getMyHistoryData(@Field("JSESSIONID")String jsessionid,@Field("requestData") String bean);




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
    Call<StatusBean> uploadHeadImg(@Part("JSESSIONID") RequestBody JSESSIONID,
                                   @Part MultipartBody.Part photo);

    //发送评论
    @FormUrlEncoded
    @POST("passlove/comment/publish")
    Call<StatusBean> uploadComment(@Field("JSESSIONID") String jsessionid,@Field("requestData") String bean);
}
