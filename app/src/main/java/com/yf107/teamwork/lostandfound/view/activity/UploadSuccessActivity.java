package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description: 发布成功后跳转
 */
public class UploadSuccessActivity extends AppCompatActivity {
    public static final String QISHILEIXING = "QISHILEIXING";
    @BindView(R.id.publishsuccess_continue_newfind)
    TextView newfind;

    @BindView(R.id.publishsuccess_continue_newlost)
    TextView newlost;

    @BindView(R.id.publishsuccess_continue_returnmainactivity)
    TextView returnmain;

    @BindView(R.id.setting_bar)
    Toolbar mToolbar;

    private View statusBarView;
    String session;
    int lostid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_success);
        Intent intent = getIntent();
        session = intent.getStringExtra("SESSION");
        intent.getIntExtra("LOSTID",lostid);
        Log.d("LOSTID", String.valueOf(lostid));
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
        ActivityManager.getActivityManager().add(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
     //   commentMyselfPresenter = new CommentMyselfPresenter(this);

    }


    @OnClick({R.id.publishsuccess_continue_returnmainactivity,R.id.publishsuccess_continue_newlost,R.id.publishsuccess_continue_newfind})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.publishsuccess_continue_returnmainactivity:{
                startActivity(new Intent(UploadSuccessActivity.this,MainActivity.class));
                break;
            }
            case R.id.publishsuccess_continue_newfind:{

                Intent intent = new Intent(UploadSuccessActivity.this,UploadActivity.class);
                intent.putExtra(QISHILEIXING, 1);
                startActivity(intent);
                break;
            }
            case R.id.publishsuccess_continue_newlost:{

                Intent intent = new Intent(UploadSuccessActivity.this,UploadActivity.class);
                intent.putExtra(QISHILEIXING, 0);
                startActivity(intent);
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            startActivity(new Intent(UploadSuccessActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
