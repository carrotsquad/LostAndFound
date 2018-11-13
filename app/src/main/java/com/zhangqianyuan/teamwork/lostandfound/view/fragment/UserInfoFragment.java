package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.system.GlideImageLoader;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoAboutUsActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyHistory;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoMyUpload;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UserInfoSettingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.BuildConfig;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description
 * 用户资料界面
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/8  给item加点击事件 
public class UserInfoFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final int REQUEST_CODE_GALLERY = 1;

    @BindView(R.id.nav_userinfo)
    NavigationView navigationView;

    private CircleImageView img;
    private TextView nickname;
    private View headview;

    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userinfo,container,false);
        ButterKnife.bind(this,view);
        mContext = getContext();
        headview=navigationView.inflateHeaderView(R.layout.userinfo_fragment_headlayout);
        img = headview.findViewById(R.id.userinfo_head_img);
        nickname = headview.findViewById(R.id.userinfo_head_nickname);
        navigationView.setNavigationItemSelectedListener(this);
        img.setOnClickListener(this);
        nickname.setOnClickListener(this);
        return view;
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
            String photoPath = resultList.get(0).getPhotoPath();
            img.setImageBitmap(BitmapFactory.decodeFile(photoPath));
            FancyToast.makeText(mContext,"取得照片",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
            //上传
            //上传时记得压缩
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Log.e("editinfo",errorMsg);
            FancyToast.makeText(mContext,errorMsg,FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
        }
    };


    /**
     * 导航条点击
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.my_upload:{
                startActivity(new Intent(getContext(),UserInfoMyUpload.class));
                return true;
            }
            case R.id.my_history:{
                startActivity(new Intent(getContext(),UserInfoMyHistory.class));
                return true;
            }
            case R.id.setting:{
                startActivity(new Intent(getContext(),UserInfoSettingActivity.class));
                return true;
            }
            case R.id.about_us:{
                startActivity(new Intent(getContext(),UserInfoAboutUsActivity.class));
                return true;
            }
            default:{
                return false;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userinfo_head_img:{
                initGallery();
                break;
            }
            default:{
                break;
            }
        }
    }
}
