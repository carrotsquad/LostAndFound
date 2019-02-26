package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.interfaces.IUploadModel;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $zhangqianyuan$
 * @updateDes
 */
public class UploadModel extends BaseModel implements IUploadModel {

    public UploadModel(){
        super();
    }

    @Override
    public void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<StatusBean> observer) {
        Gson gson = new Gson();
        //传图片时
        if(fileList.size()!=0){
            api.postUpload(createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),createMultipartBody(fileList.get(0)))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }else {
            RequestBody body = RequestBody.create(MediaType.parse("image/"+" "),"");
            //image为name参数的值，file.getname为filename参数的名字，body为请求体
            MultipartBody.Part part = MultipartBody.Part.createFormData("photos","",body);
            api.postUpload(createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),part)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }

    }

    @Override
    public void postUpload(String session, TheLostBean bean, Observer<StatusBean> observer) {
        api.postUpload(session,new Gson().toJson(bean))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    /*创建图片 的MultipartBody
      也可以用requestbody 但是这样就需要在参数里加入请求头中的name =  +filename =
      建议使用multipartbody
     */
    MultipartBody.Part createMultipartBody(File file){

        RequestBody body = RequestBody.create(MediaType.parse("image/" + file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length())),file);
        //image为name参数的值，file.getname为filename参数的名字，body为请求体
        MultipartBody.Part part = MultipartBody.Part.createFormData("photos",file.getName(),body);
        return part;
    }

    //创建jsessionId 的 requestbody
    RequestBody createRequestbody(String jsessionId){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),jsessionId);
        return body;
    }



}
