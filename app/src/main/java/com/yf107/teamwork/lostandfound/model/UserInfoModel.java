package com.yf107.teamwork.lostandfound.model;

import com.yf107.teamwork.lostandfound.model.interfaces.IUserInfoModel;
import com.yf107.teamwork.lostandfound.bean.UserImgBean;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class UserInfoModel extends BaseModel implements IUserInfoModel {
    public UserInfoModel(){
        super();
    }

    @Override
    public void changeHeadImg(String jsessionId, File imgFile, Callback<UserImgBean> callback) {
       api.uploadHeadImg(createRequestbody(jsessionId),createMultipartBody(imgFile)).enqueue(callback);
    }

    //创建jsessionId 的 requestbody
    RequestBody createRequestbody(String jsessionId){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),jsessionId);
        return body;
    }

    /*创建图片 的MultipartBody
      也可以用requestbody 但是这样就需要在参数里加入请求头中的name =  +filename =
      建议使用multipartbody
     */
    MultipartBody.Part createMultipartBody(File file){

        RequestBody body = RequestBody.create(MediaType.parse("image/" + file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length())),file);
        //image为name参数的值，file.getname为filename参数的名字，body为请求体
        MultipartBody.Part part = MultipartBody.Part.createFormData("photo",file.getName(),body);
        return part;
    }
}
