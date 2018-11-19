package com.zhangqianyuan.teamwork.lostandfound.model;

import com.google.gson.Gson;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $zhangqianyuan$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/19 需要完善
public class UploadModel extends BaseModel implements IUploadModel {
    public UploadModel(){
        super();
    }

    @Override
    public void postUpload(String session, TheLostBean bean, List<File> fileList, Observer<StatusBean> observer) {
        Map<String, RequestBody> imgMap= new HashMap<>();
        for(int l = 0; l<fileList.size(); l++){
            imgMap.put(fileList.get(l).getName(),RequestBody.create(MediaType.parse("image/" + fileList.get(l).getName().substring(fileList.get(l).getName().lastIndexOf(".") + 1, fileList.get(l).getName().length())),fileList.get(l)));
        }
        Gson gson = new Gson();
        api.postUpload(createRequestbody(session),RequestBody.create(MediaType.parse("application/json; charset=utf-8"),gson.toJson(bean)),imgMap)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    //创建jsessionId 的 requestbody
    RequestBody createRequestbody(String jsessionId){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),jsessionId);
        return body;
    }
}
