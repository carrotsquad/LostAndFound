package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

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

    @BindView(R.id.aboutus_updatelayout)
    RelativeLayout update;

    @BindView(R.id.aboutus_applayout)
    RelativeLayout aboutapp;

    @BindView(R.id.aboutus_mainze)
    RelativeLayout mianze;

    @BindView(R.id.aboutus_web)
    RelativeLayout web;
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
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @OnClick({R.id.aboutus_applayout,R.id.aboutus_mainze,R.id.aboutus_updatelayout,R.id.aboutus_web})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.aboutus_updatelayout:
                break;
            case R.id.aboutus_applayout:
                break;
            case R.id.aboutus_mainze:
                break;
            case R.id.aboutus_web:
                break;
        }
    }

    //设置返回键点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return true;
}}
