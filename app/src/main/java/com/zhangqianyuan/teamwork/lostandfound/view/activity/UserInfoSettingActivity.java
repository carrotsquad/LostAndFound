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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.image.GetImageFromWeb;
import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.model.UserSettingModel;
import com.zhangqianyuan.teamwork.lostandfound.network.AllURI;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserSettingPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserSettingActivity;

import java.io.File;
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

import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;


/**
 * Description
 * 我的界面 中的 设置 界面
 *
 * @author zhou
 */
// TODO: 2018/11/27   询问后端为什么第二次退出登录后没有返回结果
// TODO: 2019/2/12     把修改昵称和密码换成poupwindow
public class UserInfoSettingActivity extends AppCompatActivity implements IUserInfoFragment, IUserSettingActivity {
    private static final int REQUEST_CODE_GALLERY = 1;

    @BindView(R.id.setting_headlayout)
    RelativeLayout headlayout;

    @BindView(R.id.setting_headlayout_img)
    CircleImageView headImg;

    @BindView(R.id.setting_nicklayout)
    RelativeLayout nicklayout;

    @BindView(R.id.setting_nicklayout_txt)
    TextView nickname;

    @BindView(R.id.setting_emaillayout)
    RelativeLayout emaillayout;

    @BindView(R.id.setting_emaillayout_txt)
    TextView email;

    @BindView(R.id.setting_phonelayout)
    RelativeLayout phonelayout;

    @BindView(R.id.setting_phonelayout_txt)
    TextView phone;

    @BindView(R.id.setting_passwordlayout)
    RelativeLayout passwordlayout;

    @BindView(R.id.exit_account)
    Button exitaccount;

    @BindView(R.id.setting_back)
    ImageView back;


    private String photoPath;
    private String jsessionid;
    private UserSettingPresenter mUserSettingPresenter;
    private SharedPreferences sharedPreferences;
    private UserInfoPresenter mPresenter;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        initData();
        initView();
        //实现渐变式状态栏
        setGradientStatusBarColor(this,statusBarView);
    }

    //选择图片
    private void initGallery() {
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
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        GlideImageLoader imageloader = new GlideImageLoader();
        CoreConfig coreConfig = new CoreConfig.Builder(UserInfoSettingActivity.this, imageloader, theme)
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
            photoPath = resultList.get(0).getPhotoPath();
            Log.e("ImgTest", photoPath);
            String jsession = getSharedPreferences("users", Context.MODE_PRIVATE).getString(SESSION, "null");
            mPresenter.uploadHeadImg(jsession, new File(photoPath));
            FancyToast.makeText(UserInfoSettingActivity.this, "取得照片", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
            //上传
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo", errorMsg);
            FancyToast.makeText(UserInfoSettingActivity.this, errorMsg, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    };


    @OnClick({R.id.setting_headlayout, R.id.setting_headlayout_img, R.id.setting_nicklayout
            , R.id.setting_phonelayout, R.id.setting_passwordlayout, R.id.exit_account, R.id.setting_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_headlayout:
            case R.id.setting_headlayout_img:
                initGallery();
                break;
            case R.id.setting_nicklayout:
                startActivity(new Intent(UserInfoSettingActivity.this, ChangeNickNameActivity.class));
                break;
            case R.id.setting_phonelayout:
                startActivity(new Intent(UserInfoSettingActivity.this, ChangePhoneActivty.class));
                break;
            case R.id.setting_passwordlayout:
                // TODO: 2019/2/12 待添加
                break;
            case R.id.exit_account:
                Log.d("1549", "click this");
                mUserSettingPresenter.exitAccount(jsessionid);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                Intent intent = new Intent(UserInfoSettingActivity.this, SignInActivity.class);
                intent.putExtra("isExit", true);
                startActivity(intent);
                ActivityManager.getActivityManager().removeAll();
                ActivityManager.getActivityManager().removeFAll();
                //跳转到 登录界面 并且不自动进入 清空输入栏

                finish();
                break;
            case R.id.setting_back:
                finish();
                break;
            default:
                break;
        }
    }

    public void initData() {
        mUserSettingPresenter = new UserSettingPresenter(new UserSettingModel());
        mUserSettingPresenter.attachActivity(this);
        mPresenter = new UserInfoPresenter(new UserInfoModel());
        mPresenter.attachActivity(this);
        email.setText(sharedPreferences.getString("EMAIL", null));
        jsessionid = sharedPreferences.getString("SESSION", null);
        nickname.setText(sharedPreferences.getString("NICKNAME", null));
        phone.setText(sharedPreferences.getString("PNB", null));

    }

    @Override
    protected void onResume() {
        Log.d("onResume", sharedPreferences.getString("NICKNAME", null));
        nickname.setText(sharedPreferences.getString("NICKNAME", null));
        phone.setText(sharedPreferences.getString("PNB", null));
        GetImageFromWeb.httpSetImageView(AllURI.getUserPhoto(sharedPreferences.getString(SESSION, null), sharedPreferences.getString(USERPHOTO, null)),
                headImg, this);
//        Glide.with(this)
//                .load(AllURI.getUserPhoto(sharedPreferences.getString(SESSION,null),sharedPreferences.getString(USERPHOTO,null)))
//                .asBitmap()
//                .into(headImg);
        super.onResume();
    }

    public void initView() {
//        GetImageFromWeb.httpSetImageView(AllURI.getUserPhoto(sharedPreferences.getString(SESSION,null),sharedPreferences.getString(USERPHOTO,null)),
//                headImg,this);
        Glide.with(this)
                .load(AllURI.getUserPhoto(sharedPreferences.getString(SESSION, null), sharedPreferences.getString(USERPHOTO, null)))
                .asBitmap()
                .into(headImg);
    }


    @Override
    public void settingOnSuccess(int status) {
        if (status == 200) {
            Toast.makeText(UserInfoSettingActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
        } else if (status == 400) {
            Toast.makeText(UserInfoSettingActivity.this, "退出失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(int status, String userphoto) {
        Boolean success = (status == 200);
        if (success) {
            //更新头像
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(USERPHOTO, userphoto);
            editor.commit();
            headImg.setImageBitmap(BitmapFactory.decodeFile(photoPath));
//            Glide.with(this)
//                    .load(AllURI.getUserPhoto(sharedPreferences.getString(SESSION,null),sharedPreferences.getString(USERPHOTO,null)))
//                    .asBitmap()
//                    .into(headImg);
            Toast.makeText(UserInfoSettingActivity.this, "头像上传成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UserInfoSettingActivity.this, "头像上传失败", Toast.LENGTH_SHORT).show();
        }
    }
}
