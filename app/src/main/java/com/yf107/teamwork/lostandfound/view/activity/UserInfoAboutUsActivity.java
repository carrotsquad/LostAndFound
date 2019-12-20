package com.yf107.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description
 * 我的板块 中 关于我们的界面
 *
 */
public class UserInfoAboutUsActivity extends AppCompatActivity {

    @BindView(R.id.userinfo_aboutus_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.aboutus_updatelayout)
    RelativeLayout update;

    @BindView(R.id.aboutus_applayout)
    RelativeLayout aboutapp;

    @BindView(R.id.aboutus_mainze)
    RelativeLayout mianze;

    @BindView(R.id.aboutus_web)
    RelativeLayout web;
    private View statusBarView;

    @BindView(R.id.aboutus_feedback)
    RelativeLayout feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_about_us);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
        initView();
    }

    public void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @OnClick({R.id.aboutus_applayout, R.id.aboutus_mainze, R.id.aboutus_updatelayout, R.id.aboutus_web,R.id.aboutus_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            //点击更新
            case R.id.aboutus_updatelayout:
                FancyToast.makeText(UserInfoAboutUsActivity.this,"已是最新版本",
                        Toast.LENGTH_SHORT,FancyToast.INFO,false).show();
                break;
            //关于我们
            case R.id.aboutus_applayout:
                startActivity(new Intent(UserInfoAboutUsActivity.this, AboutDiAi.class));
                break;
            //点击免责声明
            case R.id.aboutus_mainze:
                startActivity(new Intent(UserInfoAboutUsActivity.this, AboutUsDetailActivity.class));
                break;
            //点击官网
            case R.id.aboutus_web:
                Uri uri = Uri.parse(AllURI.OurWebsite);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //    Intent intent = new Intent(UserInfoAboutUsActivity.this, OurWebActivity.class);
                startActivity(intent);
                break;
            case R.id.aboutus_feedback:
                feedBack();
                break;
            default:
                break;
        }
    }

    //设置返回键点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return true;
    }


    /**
     *
     * 反馈信息
     * 2019/4/18
     * Boomerr
     */
    private void feedBack() {
        View view = View.inflate(UserInfoAboutUsActivity.this,R.layout.feedback_popupwindow,null);
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
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.feedback_btn:
                        String msg = feedback_edt.getText().toString();
                        if(msg.equals("")){
                            FancyToast.makeText(UserInfoAboutUsActivity.this,"填写为空",
                                    Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }else{
                            FancyToast.makeText(UserInfoAboutUsActivity.this,
                                    "感谢您衷心的意见，我们会尽快更改",Toast.LENGTH_SHORT,FancyToast.SUCCESS
                                    ,false).show();
                        }
                        mFeedBack.dismiss();
                        break;
                    default:
                        break;
                }
            }};
        feedback_btn.setOnClickListener(listener);
        mFeedBack.showAtLocation(view, Gravity.BOTTOM, 0, 0);


    }
    //设置屏幕的透明度
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) UserInfoAboutUsActivity.this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity)UserInfoAboutUsActivity.this).getWindow().setAttributes(lp);
    }
}
