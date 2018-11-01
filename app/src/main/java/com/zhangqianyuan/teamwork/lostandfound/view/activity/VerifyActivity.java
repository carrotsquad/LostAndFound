package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.tuo.customview.VerificationCodeView;
import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyActivity extends AppCompatActivity {

    @BindView(R.id.verify_icv)
    VerificationCodeView verificationCodeView;
    @BindView(R.id.verify_email)
    TextView email;
    @BindView(R.id.verify_sure)
    Button sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
    }

    private void initData(){
        Intent intent = getIntent();

    }
}
