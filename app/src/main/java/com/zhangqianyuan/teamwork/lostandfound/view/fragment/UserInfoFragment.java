package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;


import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.network.AllURI;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoAboutUsActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyHistory;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyUpload;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoSettingActivity;

import com.zhangqianyuan.teamwork.lostandfound.image.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUserInfoFragment;


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

import static android.content.Context.MODE_PRIVATE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.NICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description
 * 用户资料界面
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $zhangqianyuan$
 * @updateDes ${TODO}
 */

// TODO: 2018/11/15  图片尚未加载
public class UserInfoFragment extends Fragment implements IUserInfoFragment {

    private static final int REQUEST_CODE_GALLERY = 1;

    @BindView(R.id.userinfo_head_img)
    CircleImageView headImg;

    @BindView(R.id.userinfo_head_nick)
    TextView  headTxt;

    @BindView(R.id.userinfo_myupload_layout)
    RelativeLayout  myupload;

    @BindView(R.id.userinfo_myhistory_layout)
    RelativeLayout  myhistory;

    @BindView(R.id.userinfo_aboutus_layout)
    RelativeLayout aboutus;

    @BindView(R.id.userinfo_setting_layout)
    RelativeLayout setting;


    private  Context mContext;
    private boolean success;            //用于判断是否上传头像成功
    private String  photoPath;          //头像文件路径
    private String  nick;               //昵称
    private String  jsession;


    private UserInfoPresenter mPresenter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo,container,false);
        ButterKnife.bind(this,view);
        mContext = getContext();
        getSharePrefrence();
        initMVP();
        initView();
        return view;
    }


    public void initMVP(){
        mPresenter = new UserInfoPresenter(new UserInfoModel());
        mPresenter.attachActivity(this);
//        mPresenter.getUserInfoData();
    }


    public static Fragment newInstance(){
        UserInfoFragment fragment = new UserInfoFragment();
        return fragment;
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
        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageloader, theme)
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
            Log.e("ImgTest",photoPath);
            headImg.setImageBitmap(BitmapFactory.decodeFile(photoPath));
            FancyToast.makeText(mContext,"取得照片",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            //上传
            jsession = getActivity().getSharedPreferences("users",Context.MODE_PRIVATE).getString(SESSION,"null");
            mPresenter.uploadHeadImg(jsession,new File(photoPath));
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo",errorMsg);
            FancyToast.makeText(mContext,errorMsg,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }
    };

    @Override
    public void onSuccess(int status) {
        success = (status==200);
        if (success){
            Toast.makeText(mContext,"头像上传成功",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "头像上传失败", Toast.LENGTH_SHORT).show();
        }
    }


    public void getSharePrefrence(){
        SharedPreferences preferences =getActivity().getSharedPreferences("users",Context.MODE_PRIVATE);
        jsession=preferences.getString("SESSION","null");
        nick=preferences.getString("NICKNAME","null");
    }

    public void initView(){
        SharedPreferences preferences =getActivity().getSharedPreferences("users",Context.MODE_PRIVATE);
        Glide.with(this)
                .load(AllURI.getUserPhoto(preferences.getString("SESSION",null),preferences.getString("USERPHOTO",null)))
                .asBitmap()
                .into(headImg);
        headTxt.setText(nick);
    }


    @OnClick({R.id.userinfo_head_img,R.id.userinfo_head_nick,R.id.userinfo_myupload_layout,
            R.id.userinfo_myhistory_layout,R.id.userinfo_aboutus_layout,R.id.userinfo_setting_layout})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.userinfo_head_img:
            case R.id.userinfo_head_nick:
                initGallery();
                break;
            case R.id.userinfo_myupload_layout:
                startActivity(new Intent(mContext,UserInfoMyUpload.class));
                break;
            case R.id.userinfo_myhistory_layout:
                startActivity(new Intent(mContext,UserInfoMyHistory.class));
                break;
            case  R.id.userinfo_aboutus_layout:
                startActivity(new Intent(mContext,UserInfoAboutUsActivity.class));
                break;
            case R.id.userinfo_setting_layout:
                startActivity(new Intent(mContext,UserInfoSettingActivity.class));
                break;
        }
    }

    @Override
    public  void onResume() {
        headTxt.setText(mContext.getSharedPreferences("users",MODE_PRIVATE).getString("NICKNAME",null));
        super.onResume();
    }
}
