package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.model.UserSettingModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserSettingPresenter;

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
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Description
 *我的界面 中的 设置 界面
 * @author  zhou
 */
public class UserInfoSettingActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_GALLERY = 1;

    @BindView(R.id.setting_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.userinfo_head_img)
    CircleImageView headImg;               //头像

    @BindView(R.id.setting_neckname_eidit)
    TextView neckName;                    //昵称  原本有数据 可直接点击修改

    @BindView(R.id.setting_phone_eidit)
    TextView   phone;                      //电话号码  同上

    @BindView(R.id.setting_mail_eidit)
    TextView  mail;                         //邮箱 展示作用 无法修改

    @BindView(R.id.setting_password_text)
    TextView  password;                      //修改密码按钮

    @BindView(R.id.setting_editinfo)
    TextView  eidtInfo;

    @BindView(R.id.exit_account)
    TextView  exitAccount;

    private UserSettingPresenter mUserSettingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        mUserSettingPresenter = new UserSettingPresenter(new UserSettingModel());
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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

    /**
     * 设置点击事件
     * @param v
     */
    @OnClick({R.id.userinfo_head_img,R.id.setting_password_text,R.id.setting_editinfo,R.id.exit_account})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userinfo_head_img:
                initGallery();
                break;
                case R.id.setting_password_text:
                    /*
                    进入修改密码界面
                     */
                    break;
            case R.id.setting_editinfo:
                    break;
            case R.id.exit_account:
              //  mUserSettingPresenter.exitAccount();
            default:{
                break;
            }
        }
    }
}
