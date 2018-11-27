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


public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.start_to_diai)
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.start_to_diai})
    void oClicked(View view){
        switch (view.getId()){
            case R.id.start_to_diai:{
//                startActivity(new Intent(GuideActivity.this,InitActivity.class));
                finish();
                break;
            }
            default:{
                break;
            }
        }
    }
}
