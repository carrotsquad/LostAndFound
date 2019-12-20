package com.yf107.teamwork.lostandfound.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.yf107.teamwork.lostandfound.model.EditInfoModel;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IEditInfoActivity;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.EditInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 改昵称
 */
public class ChangeNickNameActivity extends AppCompatActivity implements IEditInfoActivity {

    @BindView(R.id.edit_nickname_ok)
    Button ok;

    @BindView(R.id.edit_nicklayout_txt)
    EditText  nick;

    @BindView(R.id.changenick_back)
    ImageView back;

    private EditInfoPresenter mEditInfoPresenter;
    private SharedPreferences mSharedPreferences;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityManager.getActivityManager().add(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nick_name);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
        initMvp();
    }

    public void initMvp(){
        mSharedPreferences = getSharedPreferences("users",MODE_PRIVATE);
        mEditInfoPresenter = new EditInfoPresenter(new EditInfoModel());
        mEditInfoPresenter.attachActivity(this);
    }


    @OnClick({R.id.edit_nickname_ok,R.id.changenick_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.edit_nickname_ok:
                if (!nick.getText().toString().equals("")){
                    mEditInfoPresenter.uploadNeckName(mSharedPreferences.getString("SESSION",null),nick.getText().toString());
                }else{
                    Toast.makeText(ChangeNickNameActivity.this,"请输入昵称",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.changenick_back:
                finish();
                break;

        }

    }

    @Override
    public void onSuccess1(int status) {
        if (status==200){
            Toast.makeText(ChangeNickNameActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("NICKNAME",nick.getText().toString());
            editor.commit();
            Log.d("onChangeNickName",mSharedPreferences.getString("NICKNAME",null) );
            finish();
        }else if (status==400){
            Toast.makeText(ChangeNickNameActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess2(int status) {

    }
}
