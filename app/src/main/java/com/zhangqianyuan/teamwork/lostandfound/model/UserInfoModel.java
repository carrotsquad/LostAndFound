package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.UserInfoBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UserInfoModel extends BaseModel implements IUserInfoModel {
    public UserInfoModel(){
        super();
    }

    @Override
    public void getUserInfoData(Callback<UserInfoBean> callback) {
        api.getUserInfoData().enqueue(callback);
    }

    @Override
    public void changeHeadImg(String jecessionId, File imgFile, Callback<StatusBean> callback) {
       // api.uploadHeadImg(jecessionId,createResponseBody(imgFile)).enqueue(callback);
    }


    public MultipartBody.Part createResponseBody(File file){
        //创建  RequestBody  用于封装构建RequestBody
       RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        //构建MultiPartBody.part
        MultipartBody.Part body=MultipartBody.Part.createFormData("photo",file.getName(),requestBody);
        return body;
    }
}
