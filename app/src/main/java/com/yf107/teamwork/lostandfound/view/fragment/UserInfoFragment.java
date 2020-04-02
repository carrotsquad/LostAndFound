package com.yf107.teamwork.lostandfound.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.image.GetImageFromWeb;
import com.yf107.teamwork.lostandfound.image.GlideImageLoader;
import com.yf107.teamwork.lostandfound.model.UserInfoModel;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.view.interfaces.IUserInfoFragment;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.UserInfoPresenter;
import com.yf107.teamwork.lostandfound.view.activity.UserInfoAboutUsActivity;
import com.yf107.teamwork.lostandfound.view.activity.UserInfoMyHistory;
import com.yf107.teamwork.lostandfound.view.activity.UserInfoMyUpload;
import com.yf107.teamwork.lostandfound.view.activity.UserInfoSettingActivity;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.NICKNAME;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;
import static com.yf107.teamwork.lostandfound.view.activity.UserInfoSettingActivity.changename1111;
import static com.yf107.teamwork.lostandfound.view.activity.UserInfoSettingActivity.headString;

/**
 * Description
 * 用户资料界面
 */
public class UserInfoFragment extends Fragment implements IUserInfoFragment {

    private static final int REQUEST_CODE_GALLERY = 1;

    @BindView(R.id.userinfo_head_img)
    CircleImageView headImg1;

    @BindView(R.id.userinfo_head_nick)
    TextView headTxt;

    @BindView(R.id.userinfo_myupload_layout)
    RelativeLayout myupload;

    @BindView(R.id.userinfo_myhistory_layout)
    RelativeLayout myhistory;

    @BindView(R.id.userinfo_feedback_layout)
    RelativeLayout feedback;

    @BindView(R.id.userinfo_aboutus_layout)
    RelativeLayout aboutus;

    @BindView(R.id.userinfo_setting_layout)
    RelativeLayout setting;


    private Context mContext;
    private boolean success;            //用于判断是否上传头像成功
    private String photoPath;          //头像文件路径
    private String nick;               //昵称
    private String jsession;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private UserInfoPresenter mPresenter;
    private Unbinder unbinder;
    TimeThread timeThread = new TimeThread();
    private boolean isGetData = false;
    String head111;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo, container, false);
        unbinder = ButterKnife.bind(this, view);
        headTxt = view.findViewById(R.id.userinfo_head_nick);
        mContext = getContext();
        ActivityManager.getActivityManager().addF(this);
        getSharePrefrence();
        initView();
        initMVP();
        timeThread.start();
        return view;
    }

    @Override
    public void onDestroyView() {
        ActivityManager.getActivityManager().removeFAll();
        mPresenter.dettachActivity();
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mPresenter.dettachActivity();
        unbinder.unbind();
        super.onDestroy();
    }

    public void initMVP() {
        mPresenter = new UserInfoPresenter(new UserInfoModel());
        mPresenter.attachActivity(this);
    }


    public static Fragment newInstance() {
        UserInfoFragment fragment = new UserInfoFragment();
        return fragment;
    }


    //选择图片
    private void initGallery() {
        //设置主题
        //ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(Color.rgb(0x78, 0x79, 0xFF))
                .setFabNornalColor(Color.rgb(0x78, 0x79, 0xFF))
                .setFabPressedColor(Color.rgb(0x78, 0x79, 0xFF))
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
        CoreConfig coreConfig = new CoreConfig.Builder(mContext.getApplicationContext(), imageloader, theme)
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
            FancyToast.makeText(mContext.getApplicationContext(), "取得照片", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
            jsession = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE).getString(SESSION, "null");
            mPresenter.uploadHeadImg(jsession, new File(photoPath));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo", errorMsg);
            FancyToast.makeText(mContext.getApplicationContext(), errorMsg, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    };


    public void getSharePrefrence() {
        preferences = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        jsession = preferences.getString("SESSION", null);
        nick = preferences.getString("NICKNAME", null);
        editor = preferences.edit();
        Log.d("nicknameaaaaaa", nick);
    }

    public void initView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("users", MODE_PRIVATE);
        Log.d("Tag",sharedPreferences.getString(USERPHOTO,null));


        if(preferences.getString(USERPHOTO,null) == null){
            headImg1.setImageResource(R.mipmap.user);
        }else {
            Glide.with(mContext.getApplicationContext())
                    .load(head111)
                    .asBitmap()
                    .into(headImg1);
        }

    }


    @OnClick({R.id.userinfo_head_img, R.id.userinfo_head_nick, R.id.userinfo_myupload_layout,
            R.id.userinfo_myhistory_layout, R.id.userinfo_aboutus_layout, R.id.userinfo_setting_layout,
            R.id.userinfo_feedback_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userinfo_head_img:
            case R.id.userinfo_head_nick:
                initGallery();
                break;
            case R.id.userinfo_myupload_layout:
                startActivity(new Intent(mContext.getApplicationContext(), UserInfoMyUpload.class));
                break;
            case R.id.userinfo_myhistory_layout:
                startActivity(new Intent(mContext.getApplicationContext(), UserInfoMyHistory.class));
                break;
            case R.id.userinfo_feedback_layout:
                feedBack();
                break;
            case R.id.userinfo_aboutus_layout:
                startActivity(new Intent(mContext.getApplicationContext(), UserInfoAboutUsActivity.class));
                break;
            case R.id.userinfo_setting_layout:
                startActivity(new Intent(mContext.getApplicationContext(), UserInfoSettingActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        Log.d("15486622", "heihiehie");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("users", MODE_PRIVATE);
//        GetImageFromWeb.httpSetImageView(AllURI.getUserPhoto(sharedPreferences.getString(SESSION, null), sharedPreferences.getString(USERPHOTO, null)),
//                headImg, getActivity());
//        Glide.with(mContext)
//                .load(AllURI.getUserPhoto(sharedPreferences.getString(SESSION,null),sharedPreferences.getString(USERPHOTO,null)))
//                .asBitmap()
//                .into(headImg);
        Log.d("15486622", "" + AllURI.getUserPhoto(sharedPreferences.getString(SESSION, null), sharedPreferences.getString(USERPHOTO, null)));
        headTxt.setText(mContext.getApplicationContext().getSharedPreferences("users", MODE_PRIVATE).getString(NICKNAME, null));


        Log.d("Tag",sharedPreferences.getString(USERPHOTO,null));
        head111=sharedPreferences.getString(USERPHOTO,null);


        if(preferences.getString(USERPHOTO,null) == null){
            headImg1.setImageResource(R.mipmap.user);
        }else {
            Glide.with(mContext.getApplicationContext())
                    .load(preferences.getString(USERPHOTO, null))
                    .asBitmap()
                    .into(headImg1);
        }
        super.onResume();
    }

    @Override
    public void onSuccess(int status, String userphoto) {
        success = (status == 200);
        if (success) {
            //更新头像
            editor.putString(USERPHOTO, userphoto);
            editor.commit();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    headImg1.setImageBitmap(BitmapFactory.decodeFile(userphoto));
//                    Glide.with(getContext())
//                    .load(userphoto)
//                    .asBitmap()
//                    .into(headImg);
                }
            });

//            Glide.with(this)
//                    .load(AllURI.getUserPhoto(preferences.getString(SESSION,null),preferences.getString(USERPHOTO,null)))
//                    .asBitmap()
//                    .into(headImg);

            Toast.makeText(mContext.getApplicationContext(), "头像上传成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext.getApplicationContext(), "头像上传失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 反馈信息
     * 2019/4/18
     * Boomerr
     */
    private void feedBack() {
        View view = View.inflate(getContext(), R.layout.feedback_popupwindow, null);
        PopupWindow mFeedBack = new PopupWindow(view);
        mFeedBack.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        mFeedBack.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        mFeedBack.setFocusable(true);
        setBackgroundAlpha(0.5f);
        mFeedBack.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mFeedBack.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
        EditText feedback_edt = (EditText) view.findViewById(R.id.feedback_edt);
        Button feedback_btn = (Button) view.findViewById(R.id.feedback_btn);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.feedback_btn:
                        String msg = feedback_edt.getText().toString();
                        if (msg.equals("")) {
                            FancyToast.makeText(getContext(), "填写为空",
                                    Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        } else {
                            FancyToast.makeText(getContext(),
                                    "感谢您衷心的意见，我们会尽快更改", Toast.LENGTH_SHORT, FancyToast.SUCCESS
                                    , false).show();
                        }
                        mFeedBack.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        feedback_btn.setOnClickListener(listener);
        mFeedBack.showAtLocation(view, Gravity.BOTTOM, 0, 0);


    }

    //设置屏幕的透明度
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (getActivity()).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        (getActivity()).getWindow().setAttributes(lp);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    if (changename1111 == " ") break;
                    else {
                        headTxt.setText(changename1111);
                    }
            }
        }
    };

    public class TimeThread extends Thread {
        @Override
        public void run() {
            super.run();
            do {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.arg1 = 0;
                    message.arg2 = 1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);


        }
    }

    @Override
    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        timeThread.interrupt();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(enter&&!isGetData){
            isGetData=true;

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("users", MODE_PRIVATE);
            Log.d("Tag",sharedPreferences.getString(USERPHOTO,null));
           head111=sharedPreferences.getString(USERPHOTO,null);


            if(preferences.getString(USERPHOTO,null) == null){
                headImg1.setImageResource(R.mipmap.user);
            }else {
                Glide.with(mContext.getApplicationContext())
                        .load(preferences.getString(USERPHOTO, null))
                        .asBitmap()
                        .into(headImg1);
            }

        }else{
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

}
