package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitActivity extends AppCompatActivity {

    @BindView(R.id.select_register)
    Button register;

    @BindView(R.id.select_signin)
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ButterKnife.bind(this);
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
