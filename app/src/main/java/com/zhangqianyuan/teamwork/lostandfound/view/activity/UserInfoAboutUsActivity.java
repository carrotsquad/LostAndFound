package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description
 * 我的板块 中 关于我们的界面
 * @author  zhou
 */
// TODO: 2018/11/13  关于我们中的各个子界面 以及点击事件 
public class UserInfoAboutUsActivity extends AppCompatActivity {

    @BindView(R.id.userinfo_aboutus_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.about_diai)
    CardView  about_diai;

    @BindView(R.id.about_we)
    CardView  about_we;

    @BindView(R.id.Disclaimer)
    CardView    disclaimer;

    @BindView(R.id.our_web)
    CardView   our_web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_about_us);
        initView();
    }

    public void initView(){
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick({R.id.about_diai,R.id.about_we,R.id.Disclaimer,R.id.our_web})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.about_diai:
                break;
            case R.id.about_we:
                break;
            case R.id.Disclaimer:
                break;
            case R.id.our_web:
                break;
        }
    }
}
