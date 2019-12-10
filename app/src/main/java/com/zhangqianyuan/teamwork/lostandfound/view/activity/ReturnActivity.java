package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.ReturnBean;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.QISHILEIXING;

public class ReturnActivity extends Activity {

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

    private Integer qishileixing;
    private SharedPreferences sharedPreferences;
    private String phone;
    private String qq;
    private View statusBarView;
    private ReturnBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        setGradientStatusBarColor(this, statusBarView);
        ActivityManager.getActivityManager().add(this);
        phone = phone_edit.getText().toString();
        qq = qq_edit.getText().toString();
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        qishileixing = intent.getIntExtra("QISHILEIXING", 1);
        Log.e("ReturnActivity","qishileixing="+qishileixing);
        initView();
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
}
