package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.FormFile;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UploadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
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

import static cn.finalteam.toolsfinal.DateUtils.getDay;
import static cn.finalteam.toolsfinal.DateUtils.getMonth;
import static cn.finalteam.toolsfinal.DateUtils.getYear;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.QISHILEIXING;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.TYPEID;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description
 * 点击上传界面后用户填写表单的界面
 * @author  zhou
 */
public class UploadFormActivity extends AppCompatActivity implements IUploadFormActivity{

    //返回按键
    @BindView(R.id.upload_lostorfind_back)
    ImageView btnBack;

    //头部标题栏
    @BindView(R.id.upload_lostorfind_qishitype)
    TextView qishiType;

    //选择时间
    @BindView(R.id.upload_lostorfind_time)
    Button timeText;

    //选择地点
    @BindView(R.id.upload_lostorfind_place)
    Button textPlace;

    //选择赏金
    @BindView(R.id.upload_lostorfind_bounty)
    Button textBounty;

    //编辑标题
    @BindView(R.id.upload_lostorfind_description_title)
    EditText titleEdit;

    //编辑内容
    @BindView(R.id.upload_lostorfind_description_text)
    EditText descEdit;

    //图片
    @BindView(R.id.upload_lostorfind_description_img)
    ImageView img;

    //选择图片
    @BindView(R.id.upload_lostorfind_description_upload)
    TextView upload;

    //确认发布按键
    @BindView(R.id.upload_lostorfind_sure)
    Button btnSure;

    private static final int REQUEST_CODE_GALLERY = 1;
    //时间选择器
    private TimePickerView pvTime;

    //赏金选择器
    private OptionsPickerView pvOptionsBounty;

    //地点选择器
    private OptionsPickerView pvOptionsPlace;

    private SharedPreferences sharedPreferences;

    private UploadPresenter uploadPresenter;

    private String jsession;

    private TheLostBean bean;

    private Integer needBounty = -1;

    private List<FormFile> files = new ArrayList<>();

    private List<File> fileList = new ArrayList<>();

    private Integer placeid = -1;

    private String strphoto = "";

    private String strLostDate = "";
    private Integer qishileixing;
    private Integer typeid;
    private String strtitle;
    private String strdescri;
    private View statusBarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_form);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        setGradientStatusBarColor(this,statusBarView);
        ActivityManager.getActivityManager().add(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        qishileixing =intent.getIntExtra(QISHILEIXING,0);
        typeid =intent.getIntExtra(TYPEID,1);
        strtitle = titleEdit.getText().toString();

        strdescri = descEdit.getText().toString();


        initView();
        uploadPresenter = new UploadPresenter();
        uploadPresenter.attachActivity(this);

    }

    @Override
    protected void onDestroy() {
        uploadPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.upload_lostorfind_back,R.id.upload_lostorfind_time
                ,R.id.upload_lostorfind_place,R.id.upload_lostorfind_bounty
                ,R.id.upload_lostorfind_description_img,R.id.upload_lostorfind_description_upload
                ,R.id.upload_lostorfind_sure})
    void onClicked(View view) {
        switch (view.getId()){
            //返回
            case R.id.upload_lostorfind_back:{
                finish();
                break;
            }
            //选择时间
            case R.id.upload_lostorfind_time:{
                pvTime.show();
                break;
            }
            //选择地点
            case R.id.upload_lostorfind_place:{
                pvOptionsPlace.show();
                break;
            }
            //选择赏金
            case R.id.upload_lostorfind_bounty:{
                pvOptionsBounty.show();
                break;
            }
            //选择图片
            case R.id.upload_lostorfind_description_img:
            case R.id.upload_lostorfind_description_upload:{
                initGallery();
                break;
            }
            //确认发布
            case R.id.upload_lostorfind_sure: {
                strdescri = descEdit.getText().toString();
                strtitle = titleEdit.getText().toString();
                if ("".equals(strdescri) || "".equals(strtitle) || "".equals(strLostDate) || -1 == placeid) {
                    FancyToast.makeText(UploadFormActivity.this, "填写不规范", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                } else {
                    jsession = sharedPreferences.getString(SESSION, "null");
                    if("".equals(strphoto)){
                        Log.e("UploadFromActivity","strLostDate="+strLostDate);
                        bean = new TheLostBean(typeid+1,qishileixing,strtitle,strdescri,placeid+1,"00000000",strLostDate,"default.jpg",0);
                        Log.e("THELOSTBEAN",bean.toString());
                        uploadPresenter.postUpload(jsession,bean);
                    }else {
                        bean = new TheLostBean(typeid+1,qishileixing,strtitle,strdescri,placeid+1,"00000000",strLostDate,strphoto,0);
                        Log.e("THELOSTBEAN",bean.toString());
                        uploadPresenter.postUpload(jsession, bean, fileList);
                    }

                }
                break;
            }
            default:{
                break;
            }
        }
    }



    private void initView(){
        String strthingtype = allTypeBeanList.get(typeid);
        if(qishileixing==0){
            qishiType.setText("发布失物—"+strthingtype);
        }else {
            qishiType.setText("发布招领—"+strthingtype);
        }

        List<String> isNeedBountyArray = new ArrayList<>();
        isNeedBountyArray.add("是");
        isNeedBountyArray.add("否");

        //赏金选择
        pvOptionsBounty = new OptionsPickerBuilder(UploadFormActivity.this,(int options1, int options2, int options3, View v)->{
                runOnUiThread(()->{
                        needBounty = options1;
                        textBounty.setText(isNeedBountyArray.get(options1));
                    });
            })
                //取消按钮文字
                .setCancelText("取消")
                //确认按钮文字
                .setSubmitText("确定")
                //是否显示为对话框样式
                .isDialog(true)
                //切换时是否还原，设置默认选中第一项。
                .isRestoreItem(false)
                .build();

        pvOptionsBounty.setPicker(isNeedBountyArray);

        //地点选择
        pvOptionsPlace= new OptionsPickerBuilder(UploadFormActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        placeid = options1;
                        textPlace.setText(allPlaceBeanList.get(options1));
                    }
                });
            }
        })
                //取消按钮文字
                .setCancelText("取消")
                //确认按钮文字
                .setSubmitText("确定")
                //是否显示为对话框样式
                .isDialog(true)
                //切换时是否还原，设置默认选中第一项。
                .isRestoreItem(false)
                .build();
        pvOptionsPlace.setPicker(allPlaceBeanList);

        //时间选择器
        pvTime = new TimePickerBuilder(UploadFormActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                strLostDate = "";
                String month = "";
                String day = "";
                if(getMonth(date)<=9){
                    month = "0" + String.valueOf(getMonth(date));
                }else {
                    month = String.valueOf(getMonth(date));
                }
                if(getDay(date)<=9){
                    Log.e("add0","");
                    day = "0" + String.valueOf(getDay(date));
                }else {
                    Log.e("no0","");
                    day = String.valueOf(getDay(date));
                }
                strLostDate = String.valueOf(getYear(date))+month+day;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timeText.setText(String.valueOf(getYear(date))+"年"+String.valueOf(getMonth(date))+"月"+String.valueOf(getDay(date))+"日");
                    }
                });
            }

        })
                //取消按钮文字
                .setCancelText("取消")
                //确认按钮文字
                .setSubmitText("确定")
                //是否显示为对话框样式
                .isDialog(true)
                .build();

    }


    /**
     * 选择图片
     */
    private void initGallery(){
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0xF4, 0x7C, 0x00))
                .setFabNornalColor(Color.rgb(0xF4, 0x7C, 0x00))
                .setFabPressedColor(Color.rgb(0xF4, 0x7C, 0x00))
                .setCropControlColor(Color.rgb(0xFF, 0xFF, 0xFF))
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(false)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(false)
                .build();

        //配置imageloader
        GlideImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(UploadFormActivity.this, imageloader, theme)
                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);

        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,1 ,mOnHandlerResultCallback);
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
            for(int i =0 ; i<resultList.size();i++){
                if(i>0){
                    strphoto = strphoto+",";
                }
                fileList.add(new File(resultList.get(i).getPhotoPath()));
                strphoto = strphoto + resultList.get(i).getPhotoPath();
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
            finish();
            FancyToast.makeText(UploadFormActivity.this,"发布成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        }else {
            FancyToast.makeText(UploadFormActivity.this,"出现了错误",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}
