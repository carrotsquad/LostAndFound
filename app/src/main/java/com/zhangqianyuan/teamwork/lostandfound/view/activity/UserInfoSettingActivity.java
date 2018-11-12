package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description
 *我的界面 中的 设置 界面
 * @author  zhou
 */
public class UserInfoSettingActivity extends AppCompatActivity {
    @BindView(R.id.setting_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.setting_neckname_eidit)
    EditText neckName;                    //昵称  原本有数据 可直接点击修改

    @BindView(R.id.setting_phone_eidit)
    EditText   phone;                      //电话号码  同上

    @BindView(R.id.setting_autograph_eidit)
    EditText  autograph;                    //个性签名  同上

    @BindView(R.id.setting_mail_eidit)
    TextView  mail;                         //邮箱 展示作用 无法修改

    @BindView(R.id.setting_password_text)
    TextView  password;                      //修改密码按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_setting);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}
