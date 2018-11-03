package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.tuo.customview.VerificationCodeView;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.presenter.VerifyPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IVerifyActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.LogInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.LogInActivity.NICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.LogInActivity.PNB;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.LogInActivity.PWD;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.LogInActivity.SESSION;


/**
 * Description: 验证activity
 * Created at: 2018/11/3 10:27
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class VerifyActivity extends AppCompatActivity implements IVerifyActivity {

    @BindView(R.id.verify_icv)
    VerificationCodeView verificationCodeView;
    @BindView(R.id.verify_email)
    TextView email;
    @BindView(R.id.verify_sure)
    Button sure;
    @BindView(R.id.verify_loginbar)
    Toolbar toolbar;
    @BindView(R.id.verify_back)
    ImageView back;

    private String pwd;
    private String pnb;
    private String eemail;
    private String nickname;
    private String session;

    private VerifyPresenter verifyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
        verifyPresenter = new VerifyPresenter(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initData(){
        Intent intent = getIntent();
        pwd = intent.getStringExtra(PWD);
        eemail = intent.getStringExtra(EMAIL);
        pnb = intent.getStringExtra(PNB);
        nickname = intent.getStringExtra(NICKNAME);
        session = intent.getStringExtra(SESSION);
        email.setText("验证码已发送到关联邮箱 "+eemail);
    }

    @OnClick({R.id.verify_sure,R.id.verify_back})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.verify_sure:{
                String checkcode = verificationCodeView.getInputContent();
                verifyPresenter.getRegister(checkcode,eemail,nickname,pwd,pnb,session);
                break;
            }
            case R.id.verify_back:{
                onDestroy();
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void getregister(Boolean status) {
        if(status){
            FancyToast.makeText(VerifyActivity.this,"注册成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        }else {
            FancyToast.makeText(VerifyActivity.this,"注册失败",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }

    @Override
    public void showcheckcodestatus(Boolean status) {
        if(status){
            FancyToast.makeText(VerifyActivity.this,"验证码一致",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        }else {
            FancyToast.makeText(VerifyActivity.this,"验证码不一致",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}
