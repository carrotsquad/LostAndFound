package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.presenter.SignPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements ISignInActivity {

    @BindView(R.id.signin_signin)
    Button signin;

    @BindView(R.id.signin_pwd)
    EditText pwd;

    @BindView(R.id.signin_username)
    EditText email;

    @BindView(R.id.signin_tologin)
    TextView register;

    private SignPresenter signPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        register.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        signPresenter = new SignPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.signin_tologin,R.id.signin_signin})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.signin_signin:{
                if("".equals(pwd.getText().toString())||"".equals(email.getText().toString())){
                    FancyToast.makeText(SignInActivity.this,"输入有问题",FancyToast.LENGTH_SHORT,FancyToast.WARNING,true).show();
                }else {
                    String eemail = email.getText().toString();
                    String password = pwd.getText().toString();
                    signPresenter.getSignIn(eemail,password);
                    Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            }
            case R.id.signin_tologin:{
                Intent intent = new Intent(SignInActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default:{
                break;
            }
        }
    }


    @Override
    public void showSignInStatus(Boolean status, SignInBean signInBean) {
        if(status){
            FancyToast.makeText(SignInActivity.this,"登录失败",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }else {
            FancyToast.makeText(SignInActivity.this,"登录失败",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}
