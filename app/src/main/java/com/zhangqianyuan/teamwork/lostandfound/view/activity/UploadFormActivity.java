package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.presenter.IUploadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UploadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUploadFormActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

import static cn.finalteam.toolsfinal.DateUtils.getTime;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description
 * 点击上传界面后用户填写表单的界面
 * @author  zhou
 */
public class UploadFormActivity extends AppCompatActivity implements IUploadFormActivity{

    @BindView(R.id.upload_lostorfind_back)
    ImageView btnBack;

    @BindView(R.id.upload_lostorfind_qishitype)
    TextView qishiType;

    @BindView(R.id.upload_lostorfind_time)
    TextView timeText;

    @BindView(R.id.upload_lostorfind_where)
    Spinner where;

    @BindView(R.id.upload_lostorfind_bounty)
    Spinner bounty;

    @BindView(R.id.upload_lostorfind_description_title)
    EditText titleEdit;

    @BindView(R.id.upload_lostorfind_description_text)
    EditText descEdit;

    @BindView(R.id.upload_lostorfind_description_img)
    ImageView img;

    @BindView(R.id.upload_lostorfind_description_upload)
    TextView upload;

    @BindView(R.id.upload_lostorfind_sure)
    Button btnSure;

    private static final int REQUEST_CODE_GALLERY = 1;
    //时间选择器
    private TimePickerView pvTime;

    private SharedPreferences sharedPreferences;

    private UploadPresenter uploadPresenter;

    private String jsession;

    private TheLostBean bean;

    private List<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_form);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        initView();
        uploadPresenter = new UploadPresenter();
        uploadPresenter.attachActivity(this);
    }

    @OnClick({R.id.upload_lostorfind_back,R.id.upload_lostorfind_time
                ,R.id.upload_lostorfind_description_img,R.id.upload_lostorfind_description_upload
                ,R.id.upload_lostorfind_sure})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.upload_lostorfind_back:{
                finish();
                break;
            }
            case R.id.upload_lostorfind_time:{
                pvTime.show();
                break;
            }
            //选择图片
            case R.id.upload_lostorfind_description_img:
            case R.id.upload_lostorfind_description_upload:{
                initGallery();
                break;
            }
            //点击确认
            case R.id.upload_lostorfind_sure:{
                jsession = sharedPreferences.getString(SESSION,"null");
                uploadPresenter.postUpload(jsession,bean,fileList);
            }
            default:{
                break;
            }
        }
    }



    private void initView(){

        List<String> isNeedBountyArray = new ArrayList<>();
        isNeedBountyArray.add("是");
        isNeedBountyArray.add("否");
        ArrayAdapter<String> placeArrayAdapter = new ArrayAdapter<>(this,R.layout.spin_item, R.id.spin_text,allPlaceBeanList);
        ArrayAdapter<String> isNeedBountyArrayAdapter = new ArrayAdapter<>(this,R.layout.spin_item, R.id.spin_text,isNeedBountyArray);
        where.setAdapter(placeArrayAdapter);
        bounty.setAdapter(isNeedBountyArrayAdapter);

        //时间选择器
        pvTime = new TimePickerBuilder(UploadFormActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(UploadFormActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            }
        }).build();

    }

    //选择图片
    private void initGallery(){
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder().build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        GlideImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);

        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,20 ,mOnHandlerResultCallback);
    }

    private GalleryFinal.OnHanlderResultCallback mOnHandlerResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            //进行图片上传与置换
            //置换
            String photoPath = resultList.get(0).getPhotoPath();
            img.setImageBitmap(BitmapFactory.decodeFile(photoPath));
            FancyToast.makeText(UploadFormActivity.this,"取得照片",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            //上传
            bean = new TheLostBean(1,1,"1","da",1,"2018283281","201828","nick.jpg",1);
            fileList=new ArrayList<>();
            for(int i =0 ; i<resultList.size();i++){
                fileList.add(new File(resultList.get(i).getPhotoPath()));
                Log.e("ImgTest",resultList.get(i).getPhotoPath());
            }

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo",errorMsg);
            FancyToast.makeText(UploadFormActivity.this,errorMsg,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }
    };

    @Override
    public void showStatus(Boolean status) {
        if(status){
            startActivity(new Intent(UploadFormActivity.this,UploadSuccessActivity.class));
            FancyToast.makeText(UploadFormActivity.this,"发布成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        }else {
            FancyToast.makeText(UploadFormActivity.this,"出现了错误",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}
