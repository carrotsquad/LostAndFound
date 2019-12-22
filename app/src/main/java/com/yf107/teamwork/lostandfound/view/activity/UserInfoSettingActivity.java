package com.yf107.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.image.GetImageFromWeb;
import com.yf107.teamwork.lostandfound.image.GlideImageLoader;
import com.yf107.teamwork.lostandfound.model.EditInfoModel;
import com.yf107.teamwork.lostandfound.model.UserInfoModel;
import com.yf107.teamwork.lostandfound.model.UserSettingModel;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IEditInfoActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IUserInfoFragment;
import com.yf107.teamwork.lostandfound.view.interfaces.IUserSettingActivity;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.EditInfoPresenter;
import com.yf107.teamwork.lostandfound.presenter.UserInfoPresenter;
import com.yf107.teamwork.lostandfound.presenter.UserSettingPresenter;

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

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;


/**
 * Description
 * 我的界面 中的 设置 界面
 *
**/
public class UserInfoSettingActivity extends AppCompatActivity implements IUserInfoFragment, IUserSettingActivity, IEditInfoActivity {
    private static final int REQUEST_CODE_GALLERY = 1;
    public static String action = "jason.broadcast.action";

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

    private EditInfoPresenter mEditInfoPresenter;

    private String change_nick = null;
    private String change_phone = null;
    public static String changename1111 = " ";


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
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
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
                .setEnableCamera(false)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(false)
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
                //startActivity(new Intent(UserInfoSettingActivity.this, ChangeNickNameActivity.class));
                changeNickName();
                break;
            case R.id.setting_phonelayout:
                //startActivity(new Intent(UserInfoSettingActivity.this, ChangePhoneActivty.class));
                changePhone();
                break;
            case R.id.setting_passwordlayout:
                // TODO: 2019/2/12 待添加
                Intent intent1 = new Intent(this,ForgetPasswordActivity.class);
                startActivity(intent1);
                break;
            case R.id.exit_account:
                Log.d("1549", "click this");
                mUserSettingPresenter.exitAccount(jsessionid);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                Intent intent = new Intent(UserInfoSettingActivity.this, SignInActivity.class);
                intent.putExtra("isExit1", true);
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
        mEditInfoPresenter = new EditInfoPresenter(new EditInfoModel());
        mEditInfoPresenter.attachActivity(this);
    }

    @Override
    protected void onResume() {//2019.10.19由NICKNAME改成STU，否则点击设置时闪退
//        Log.d("onResume", sharedPreferences.getString("STU", null));
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


    /**
     * 修改昵称和电话 popupwindow
     * 之前用的是Activity
     * Boomerr
     * 2019/4/18
     */
    //修改昵称
    private void changeNickName() {

        View view = View.inflate(UserInfoSettingActivity.this,R.layout.change_popupwindow,null);
        PopupWindow mChange = new PopupWindow(view);
        mChange.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        mChange.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        mChange.setFocusable(true);
        setBackgroundAlpha(0.5f);
        mChange.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mChange.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        EditText change_edt_nick = (EditText) view.findViewById(R.id.change_edt);
        Button change_btn = (Button) view.findViewById(R.id.change_btn);
        View.OnClickListener listener1 = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.change_btn:
                        change_nick = change_edt_nick.getText().toString();
                        changename1111 = change_nick;
                        if (!change_edt_nick.getText().toString().equals("")){
                            mEditInfoPresenter.uploadNeckName(sharedPreferences.getString("SESSION",null),change_edt_nick
                                    .getText().toString());
                        }else{
                            FancyToast.makeText(UserInfoSettingActivity.this,"请输入",FancyToast.WARNING).show();
                        }
                        mChange.dismiss();
                        break;
                    default:
                        break;
                }
            }};

        change_btn.setOnClickListener(listener1);
        mChange.showAtLocation(view, Gravity.CENTER, 0, 0);
    }
    //修改联系电话
    private void changePhone() {

        View view = View.inflate(UserInfoSettingActivity.this,R.layout.change_popupwindow,null);
        PopupWindow mChange = new PopupWindow(view);
        mChange.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        mChange.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        mChange.setFocusable(true);
        setBackgroundAlpha(0.5f);
        mChange.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mChange.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        EditText change_edt_phone = (EditText) view.findViewById(R.id.change_edt);
        Button change_btn = (Button) view.findViewById(R.id.change_btn);
        View.OnClickListener listener2 = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.change_btn:
                        change_phone = change_edt_phone.getText().toString();
                        if (!change_edt_phone.getText().toString().equals("")){
                            mEditInfoPresenter.uoloadPhoneNumber(sharedPreferences.getString("SESSION",null),change_edt_phone
                                    .getText().toString());
                        }else{
                            FancyToast.makeText(UserInfoSettingActivity.this,"请输入",FancyToast.WARNING).show();
                        }
                        mChange.dismiss();
                        break;
                    default:
                        break;
                }
            }};

        change_btn.setOnClickListener(listener2);
        mChange.showAtLocation(view, Gravity.CENTER, 0, 0);

    }

    //设置屏幕的透明度
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) UserInfoSettingActivity.this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity)UserInfoSettingActivity.this).getWindow().setAttributes(lp);
    }

    //昵称 修改结果回调
    @Override
    public void onSuccess1(int status) {

        if (status==200){
            FancyToast.makeText(UserInfoSettingActivity.this,"修改成功",FancyToast.SUCCESS).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("NICKNAME",change_nick);
            editor.commit();
            Log.d("onChangeNickName",sharedPreferences.getString("NICKNAME",null) );
            finish();
        }else if (status==400){
            FancyToast.makeText(UserInfoSettingActivity.this,"修改失败",FancyToast.ERROR).show();
        }

    }
    //电话号码 修改结果回调
    @Override
    public void onSuccess2(int status) {

        if (status==200){
            FancyToast.makeText(UserInfoSettingActivity.this,"修改成功",FancyToast.SUCCESS).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("PNB",change_phone);
            editor.commit();
            Log.d("onChangeNickName",sharedPreferences.getString("PNB",null) );
            finish();
        }else if (status==400){
            FancyToast.makeText(UserInfoSettingActivity.this,"修改失败",FancyToast.ERROR).show();
        }

    }
}
