package com.yf107.teamwork.lostandfound.network;

import com.yf107.teamwork.lostandfound.bean.AllPlacesBean;
import com.yf107.teamwork.lostandfound.bean.AllTypesBean;
import com.yf107.teamwork.lostandfound.bean.CheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.CommentFeedBack;
import com.yf107.teamwork.lostandfound.bean.ImageBean;
import com.yf107.teamwork.lostandfound.bean.MyHistoryItem;
import com.yf107.teamwork.lostandfound.bean.RegisterBean;
import com.yf107.teamwork.lostandfound.bean.SearchBean;
import com.yf107.teamwork.lostandfound.bean.SendCheckCodeBean;
import com.yf107.teamwork.lostandfound.bean.SignInBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.ThingDetailBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;
import com.yf107.teamwork.lostandfound.bean.UserImgBean;

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
    Observable<RegisterBean> getRegister(@Field("snumber")String snumber,@Field("password") String password,@Field("nickname") String nickname,@Field("phonenumber") String phonenumber,@Field("JSESSIONID") String sessionID);

    //登录
    @FormUrlEncoded
    @POST("/passlove/loginIn2")
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


    //获得所有地点
    @FormUrlEncoded
    @POST("/passlove/info/places")
    Observable<AllPlacesBean> getAllPlaces(@Field("JSESSIONID") String session);

    /*
    我的发布 包括丢/失
    向服务器传数据
     */
    @POST("/passlove/user/publishlost2")
    @Multipart
    Observable<StatusBean> postUpload(@Part("JSESSIONID") RequestBody session, @Part("thelost") RequestBody theLostBean, @Part MultipartBody.Part photos);


    @POST("/passlove/user/publishlost2")
    @FormUrlEncoded
    Observable<StatusBean> postUpload(@Field("JSESSIONID") String session, @Field("thelost") String theLostBean);


    @POST("/passlove/user/publishlost2/card")
    @Multipart
    Observable<StatusBean> cardUpload(@Field("cardid") String stu,@Part("JSESSIONID") RequestBody session, @Part("thelost") RequestBody theLostBean, @Part MultipartBody.Part photos);


    @POST("/passlove/user/publishlost2/card")
    @FormUrlEncoded
    Observable<StatusBean> cardUpload(@Field("cardid") String stu,@Field("JSESSIONID") String session, @Field("thelost") String theLostBean);

    /*
    递爱成功
     */
    @POST("/passlove/updateishandle/{id}")
    @FormUrlEncoded
    Observable<StatusBean> postSuccess(@Field("JSESSIONID")String jsessionid,@Path("id") int id);

    /*
    删除
     */
    @POST("/passlove/deletelostbyid/{id}")
    @FormUrlEncoded
    Observable<StatusBean> postDelete(@Field("JSESSIONID")String jsessionid,@Path("id") int id);

    /*
    编辑更新
     */
    @POST("/passlove/UpdateLostById")
    @Multipart
    Observable<StatusBean> postReplace(@Part("JSESSIONID") RequestBody session, @Part("thelost") RequestBody theLostBean, @Part MultipartBody.Part photos,@Field("lostid") int id);


    @POST("/passlove/UpdateLostById")
    @FormUrlEncoded
    Observable<StatusBean> postReplace(@Field("JSESSIONID") String session, @Field("thelost") String theLostBean,@Field("lostid") int id);


    //获取动态 失物昨天 信息
    @POST("/passlove/dynamics/0/1")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostTodayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 失物今天 信息
    @POST("/passlove/dynamics/0/0")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostYesterdayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 失物更早 信息
    @POST("/passlove/dynamics/0/2")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicLostAgoData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 招领今天 信息
    @POST("/passlove/dynamics/1/1")
    @FormUrlEncoded
    Observable<SearchBean> getDynamicFindTodayData(@Field("requestData") String info, @Field("JSESSIONID") String session);

    //获取动态 招领昨天 信息
    @POST("/passlove/dynamics/1/0")
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
    Observable<UpDateMessageBean> getMyCommentedData(@Field("JSESSIONID")String jsessionid);

    /*
    发消息给失/得主
     */
    @POST("/passlove/sendmail")
    @FormUrlEncoded
    Observable<StatusBean> sendMessage(@Field("JSESSIONID") String session, @Field("lostid") int id);

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
    Call<UserImgBean> updateUserPhoto(@Part("JSESSIONID") RequestBody JSESSIONID,
                                    @Part MultipartBody.Part photo);

    //发表评论
    @FormUrlEncoded
    @POST("passlove/comment/publish")
    Call<StatusBean> uploadComment(@Field("JSESSIONID") String jsessionid,@Field("requestData") String bean);

    //展示评论
    @FormUrlEncoded
    @POST("passlove/comment/get")
    Call<CommentFeedBack>  getComment(@Field("JSESSIONID") String jsessionid,@Field("lostid") int bean);

    //更新评论状态
    @FormUrlEncoded
    @POST("/passlove/updatemessageisread")
    Observable<StatusBean> updateMessageIsRead(@Field("JSESSIONID") String JSESSIONID,@Field("lostid") int lostid);
//
//    //获取评论状态
//    @FormUrlEncoded
//    @POST("/passlove/getmessageisread")
//    Observable<IsRead> getMessageIsRead(@Field("commentid") int commentid,@Field("JSESSIONID") String jsessionid,@Field("isread") int isread);


    //修改密码
    @FormUrlEncoded
    @POST("/passlove/updatepassword")
    Observable<CheckCodeBean> updatepassword(@Field("password") String password,@Field("JSESSIONID") String session,@Field("username") String email);

    //得到失物招领上传的图片
    @POST("passlove/img/user")
    Observable<ImageBean>  getTheLostPhoto(@Query("name") String name);
}
