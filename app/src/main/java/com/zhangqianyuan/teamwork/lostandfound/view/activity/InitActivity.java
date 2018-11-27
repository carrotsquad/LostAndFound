package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description: 初始化界面
 * Created at: 2018/11/19 0:10
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class InitActivity extends AppCompatActivity {

    @BindView(R.id.select_register)
    Button register;

    @BindView(R.id.select_signin)
    Button signin;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);

        SharedPreferences preferences = getSharedPreferences("guideActivity", Context.MODE_PRIVATE);

        // 判断是不是首次登录,若是
        if (preferences.getBoolean("firstStart", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstStart",false);
            editor.commit();
            startActivity(new Intent(InitActivity.this,GuideActivity.class));
//            finish();
        }


        //如果已经登录过，直接进入登陆界面
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        if(!"balabala".equals(sharedPreferences.getString(EMAIL, "balabala"))&&!"balabala".equals(sharedPreferences.getString(SESSION, "balabala"))){
            startActivity(new Intent(InitActivity.this,SignInActivity.class));
            finish();
        }
    }

    @OnClick({R.id.select_register,R.id.select_signin})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.select_register:{
                Intent intent = new Intent(InitActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.select_signin:{
                Intent intent = new Intent(InitActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default:{
                break;
            }
        }
    }
}
