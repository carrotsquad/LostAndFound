package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 发布成功后跳转
 * Created at: 2018/11/19 0:09
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class UploadSuccessActivity extends AppCompatActivity {

    @BindView(R.id.publishsuccess_back)
    ImageView back;

    @BindView(R.id.publishsuccess_continue_newfind)
    TextView newfind;

    @BindView(R.id.publishsuccess_continue_newlost)
    TextView newlost;

    @BindView(R.id.publishsuccess_continue_returnmainactivity)
    TextView returnmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_success);
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
    }


    @OnClick({R.id.publishsuccess_back,R.id.publishsuccess_continue_returnmainactivity,R.id.publishsuccess_continue_newlost,R.id.publishsuccess_continue_newfind})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.publishsuccess_continue_returnmainactivity:
            case R.id.publishsuccess_back:{
                startActivity(new Intent(UploadSuccessActivity.this,MainActivity.class));
                break;
            }
            case R.id.publishsuccess_continue_newfind:{
                Intent intent = new Intent(UploadSuccessActivity.this,UploadActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.publishsuccess_continue_newlost:{
                Intent intent = new Intent(UploadSuccessActivity.this,UploadActivity.class);
                startActivity(intent);
                break;
            }
            default:{
                break;
            }
        }
    }
}
