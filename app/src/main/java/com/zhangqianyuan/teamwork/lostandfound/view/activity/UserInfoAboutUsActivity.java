package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description
 * 我的板块 中 关于我们的界面
 * @author  zhou
 */
// TODO: 2018/11/13  关于我们中的各个子界面 以及点击事件 
public class UserInfoAboutUsActivity extends AppCompatActivity {

    @BindView(R.id.userinfo_aboutus_toolbar)
    Toolbar mToolbar;
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
}
