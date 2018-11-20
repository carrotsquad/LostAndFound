package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.model.EditInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.EditInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IEditInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 改号码
 */
public class ChangePhoneActivty extends AppCompatActivity implements IEditInfoActivity {


    @BindView(R.id.edit_phonelayout_txt)
    EditText  phone;

    @BindView(R.id.edit_phonename_ok)
    Button  ok;

    private EditInfoPresenter mEditInfoPresenter;
    private SharedPreferences  mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_activty);
        ButterKnife.bind(this);
        initMvp();
    }

    public void initMvp(){
        mSharedPreferences = getSharedPreferences("users",MODE_PRIVATE);
        mEditInfoPresenter = new EditInfoPresenter(new EditInfoModel());
        mEditInfoPresenter.attachActivity(this);
    }

    @OnClick({R.id.edit_phonename_ok})
    public void onClick(View view){
        if (!phone.getText().toString().equals("")){
            mEditInfoPresenter.uoloadPhoneNumber(mSharedPreferences.getString("SESSION",null),phone.getText().toString());
        }else{
            Toast.makeText(ChangePhoneActivty.this,"请输入号码",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(int status) {
        if (status==200){
            Toast.makeText(ChangePhoneActivty.this,"修改成功",Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString("PNB",phone.getText().toString());
            editor.commit();
            finish();
        }else if (status==400){
            Toast.makeText(ChangePhoneActivty.this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }
}
