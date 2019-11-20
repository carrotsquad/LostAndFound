package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
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


    private Integer qishileixing;

    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        setGradientStatusBarColor(this, statusBarView);
        ActivityManager.getActivityManager().add(this);
        Intent intent = getIntent();
        qishileixing = intent.getIntExtra(QISHILEIXING, 0);
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
