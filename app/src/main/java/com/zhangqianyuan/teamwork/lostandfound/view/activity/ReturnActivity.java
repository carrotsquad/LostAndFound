package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.presenter.ReturnPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IReturnActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IUploadFormActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

public class ReturnActivity extends AppCompatActivity implements IReturnActivity {
    public static final String OTHERSID = "OTHERSID";

    //头部标题栏
    @BindView(R.id.thing_return_type)
    TextView qishiType;

    //确认栏
    @BindView(R.id.thing_type)
    Button thingType;

    @BindView(R.id.phone_edit)
    EditText phone_edit;

    @BindView(R.id.qq_edit)
    EditText qq_edit;

    @BindView(R.id.thing_return_back)
    ImageView back;

    @BindView(R.id.thing_return_pic)
    ImageView imageView;

    private Integer qishileixing;
    private String strThingsImgs;
    private SharedPreferences sharedPreferences;
    private String jsession;
    private String phone;
    private String qq;
    private int id;
    private ReturnPresenter returnPresenter;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        setGradientStatusBarColor(this, statusBarView);
        ActivityManager.getActivityManager().add(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        qishileixing = intent.getIntExtra("QISHILEIXING", 1);
        strThingsImgs = intent.getStringExtra("OTHERSIMGS");
        id= intent.getIntExtra("OTHERSID",-1);
        initView();
        returnPresenter = new ReturnPresenter();
        returnPresenter.attachActivity(this);
    }

    @Override
    protected void onDestroy() {
        returnPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.thing_type})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.thing_type:{
                if ("".equals(phone_edit.getText().toString())||"".equals(qq_edit.getText().toString())) {
                    FancyToast.makeText(ReturnActivity.this, "输入不能为空", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                } else {
                    phone = phone_edit.getText().toString();
                    qq = qq_edit.getText().toString();
                    jsession = sharedPreferences.getString(SESSION, "null");
                    returnPresenter.sendMessage(jsession,id,qq,phone);
                    Log.e("ReturnActivity","完好"+jsession+"+"+id+"+"+qq+phone);
                }

            }
        }
    }


    private void initView() {
        if (qishileixing == 0) {
            qishiType.setText("我要归还");
            thingType.setText("确认归还");
        } else {
            qishiType.setText("我要认领");
            thingType.setText("确认认领");
        }

    }

    @Override
    public void showStatus(Boolean status) {
        if (status) {
            finish();
            FancyToast.makeText(ReturnActivity.this, "成功", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
        } else {
            FancyToast.makeText(ReturnActivity.this, "出现了错误", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }
}
