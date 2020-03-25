package com.yf107.teamwork.lostandfound.model;

import android.util.Log;

import com.google.gson.Gson;
import com.yf107.teamwork.lostandfound.bean.AddCommitBean;
import com.yf107.teamwork.lostandfound.bean.ThingDetailBean;
import com.yf107.teamwork.lostandfound.model.interfaces.IUploadModel;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;

public class UploadModel extends BaseModel implements IUploadModel {

    public UploadModel(){
        super();
    }

    @Override
    public void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<AddCommitBean> observer) {
        Log.e("ReplaceModel","session = "+session+",bean = "+bean+",fileList = "+fileList);
        Gson gson = new Gson();
        //传图片时
        if(fileList.size()!=0){
            Log.e("UploadFormActivity","createRequestbody(session) = "+createRequestbody(session)+"RequestBody.create(MediaType.parse(\"application/json; charset=utf-8\"),gson.toJson(bean) = "+
                    RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean))+"createMultipartBody(fileList.get(0)) = "+createMultipartBody(fileList.get(0)));
            api.postUpload(createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),createMultipartBody(fileList.get(0)))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
            Log.e("UploadModel1","createRequestbody(session)"+createRequestbody(session)+"gson.toJson(bean)"+gson.toJson(bean)+"createMultipartBody(fileList.get(0))"+createMultipartBody(fileList.get(0)));
        }else {
            RequestBody body = RequestBody.create(MediaType.parse("image/"+" "),"");
            //image为name参数的值，file.getname为filename参数的名字，body为请求体
            MultipartBody.Part part = MultipartBody.Part.createFormData("photos","",body);
            api.postUpload(createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),part)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
            Log.e("UploadModel2","createRequestbody(session)"+createRequestbody(session)+"gson.toJson(bean)"+gson.toJson(bean)+"part"+part);
        }

    }

    @Override
    public void postUpload(String session, TheLostBean bean, Observer<AddCommitBean> observer) {
        api.postUpload(session,new Gson().toJson(bean))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("UploadModel3","session"+session+"new Gson().toJson(bean)"+new Gson().toJson(bean));
    }

    @Override
    public void cardUpload(String stu, String session, TheLostBean bean, List<File> fileList, Observer<AddCommitBean> observer) {
        Gson gson = new Gson();
        //传图片时
        if(fileList.size()!=0){
            Log.e("UploadFormActivity","stu = "+stu+"createRequestbody(session) = "+createRequestbody(session)+"RequestBody.create(MediaType.parse(\"application/json; charset=utf-8\"),gson.toJson(bean) = "+
                    RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean))+"createMultipartBody(fileList.get(0)) = "+createMultipartBody(fileList.get(0)));
            api.cardUpload(stu,createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),createMultipartBody(fileList.get(0)))
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
            Log.e("UploadModel3","createRequestbody(session)"+createRequestbody(session)+"gson.toJson(bean)"+gson.toJson(bean)+"createMultipartBody(fileList.get(0))"+createMultipartBody(fileList.get(0)));
        }else {
            RequestBody body = RequestBody.create(MediaType.parse("image/"+" "),"");
            //image为name参数的值，file.getname为filename参数的名字，body为请求体
            MultipartBody.Part part = MultipartBody.Part.createFormData("card","",body);
            api.cardUpload(stu,createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),part)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
            Log.e("UploadModel4","createRequestbody(session)"+createRequestbody(session)+"gson.toJson(bean)"+gson.toJson(bean)+"part"+part);
        }
    }

    @Override
    public void cardUpload(String stu, String session, TheLostBean bean, Observer<AddCommitBean> observer) {
        api.cardUpload(stu,session,new Gson().toJson(bean))
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e("UploadModel5","stu"+stu+"session"+session+"new Gson().toJson(bean)"+new Gson().toJson(bean));
    }

    @Override
    public void publishcomment(String session, Integer id, int lostid, String time, String content, Callback<StatusBean> callback) {
        ThingDetailBean bean = new ThingDetailBean();
        ThingDetailBean.Comment con = new ThingDetailBean.Comment();
        con.setContent(content);
        con.setId(id);
        con.setTime(time);
        bean.setLostid(lostid);
        bean.setComment(con);
        api.uploadComment(session,new Gson().toJson(bean)).enqueue(callback);
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
