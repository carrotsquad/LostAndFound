package com.yf107.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDiAi extends AppCompatActivity {

    @BindView(R.id.aboutdiail_back)
    ImageView back;

    @BindView(R.id.aboutdiai)
    TextView   info;
    private View statusBarView;

    @BindView(R.id.setting_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abou_di_ai);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
        back.setOnClickListener(v->finish());

        info.setText(" “递爱”是目前暂针对于重庆邮电大学校内人员寻找丢失物品的公益互助平台。\n" +
                " “递爱”是我们为了解决重庆邮电大学学生失物多而召回率低的问题而创作的。它是一个能加快校内人员找回丢失物品的速度的帮助失物者平台，更是一个提供拾物者机会的平台，让捡到失物的人更好更快更有途径地寻找失物者，在潜意识中提高大学生的素质。\n" +
                "\n" +
                " “递爱”的目标：\n" +
                "      ①帮助你发布寻物启事，方便拾物者联系找到你；\n" +
                "      ②帮助你随手做公益，体验助人为乐的快乐善举；\n" +
                "      ③提高整体校园的拾物招领意识，丰富大学生的精神文明世界，创造高素质的大学校园。");

        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
    }
}
