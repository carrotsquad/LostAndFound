package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.STU;

/**
 * Description: 初始化界面
 * Created at: 2018/11/19 0:10
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class InitActivity extends AppCompatActivity {

    @BindView(R.id.select_register)
    Button register;

    @BindView(R.id.select_signin)
    Button signin;

    private SharedPreferences sharedPreferences;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this, statusBarView);
        ActivityManager.getActivityManager().add(this);
        //如果已经登录过，直接进入登陆界面
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);

        // 判断是不是首次登录,若是
        if (sharedPreferences.getBoolean("firstStart", true)) {
            Intent intent = new Intent(InitActivity.this, GuideActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.e("initActivity",""+sharedPreferences.getString(STU,"")+"+"+sharedPreferences.getString(SESSION, ""));
            if (!"".equals(sharedPreferences.getString(STU, "")) && !"".equals(sharedPreferences.getString(SESSION, ""))) {
                Intent intent=new Intent(InitActivity.this, SignInActivity.class);
                Log.e("initActivity","跳转");
                startActivity(intent);
                finish();
            }
        }
    }

    @OnClick({R.id.select_register, R.id.select_signin})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.select_register: {
                Intent intent = new Intent(InitActivity.this, LogInActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.select_signin: {
                Intent intent = new Intent(InitActivity.this, SignInActivity.class);
                startActivity(intent);
                break;
            }
            default: {
                break;
            }
        }
    }
}
