package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.model.UserSettingModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserSettingPresenter;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Description
 *我的界面 中的 设置 界面
 * @author  zhou
 */
public class UserInfoSettingActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 1;

  @BindView(R.id.setting_headlayout)
    RelativeLayout headlayout;

  @BindView(R.id.setting_headlayout_img)
  CircleImageView  headImg;

  @BindView(R.id.setting_nicklayout)
  RelativeLayout  nicklayout;

  @BindView(R.id.setting_nicklayout_txt)
  TextView  nickname;

  @BindView(R.id.setting_emaillayout)
  RelativeLayout  emaillayout;

  @BindView(R.id.setting_emaillayout_txt)
  TextView  email;

  @BindView(R.id.setting_phonelayout)
  RelativeLayout  phonelayout;

  @BindView(R.id.setting_phonelayout_txt)
  TextView  phone;

  @BindView(R.id.setting_passwordlayout)
  RelativeLayout passwordlayout;

  @BindView(R.id.exit_account)
    CardView  exitaccount;



    private String  jsessionid;
    private UserSettingPresenter mUserSettingPresenter;
    private SharedPreferences sharedPreferences  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        ButterKnife.bind(this);
        initData();
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

        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHandlerResultCallback);
    }

    private GalleryFinal.OnHanlderResultCallback mOnHandlerResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            //进行图片上传与置换
            //置换
            String photoPath = resultList.get(0).getPhotoPath();
            headImg.setImageBitmap(BitmapFactory.decodeFile(photoPath));
            FancyToast.makeText(UserInfoSettingActivity.this,"取得照片",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            //上传
            //上传时记得压缩
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo",errorMsg);
            FancyToast.makeText(UserInfoSettingActivity.this,errorMsg,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }
    };


    @OnClick({R.id.setting_headlayout,R.id.setting_headlayout_img,R.id.setting_nicklayout
            ,R.id.setting_phonelayout,R.id.setting_passwordlayout,R.id.exit_account})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.setting_headlayout:
            case R.id.setting_headlayout_img:
                initGallery();
                break;
            case R.id.setting_nicklayout:
                break;
            case R.id.setting_phonelayout:
                break;
            case R.id.setting_passwordlayout:
                break;
            case R.id.exit_account:
                mUserSettingPresenter.exitAccount(jsessionid);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                break;
        }
    }

    public void initData(){
        mUserSettingPresenter = new UserSettingPresenter(new UserSettingModel());
        sharedPreferences = getSharedPreferences("users",MODE_PRIVATE);
        email.setText(sharedPreferences.getString("EMAIL",null));
        jsessionid=sharedPreferences.getString("SESSION",null);
        nickname.setText(sharedPreferences.getString("NICKNAME",null));
        phone.setText(sharedPreferences.getString("PNB",null));

    }
}
