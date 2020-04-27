package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.tuo.customview.VerificationCodeView;
import com.yf107.teamwork.lostandfound.presenter.VerifyPresenter;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IVerifyActivity;
import com.yf107.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description: 验证activity
 */
public class VerifyActivity extends AppCompatActivity implements IVerifyActivity {

    public static final String SIGNIN = "SignIn";

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
    private String stu;
    private String session;

    private VerifyPresenter verifyPresenter;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);
        verifyPresenter = new VerifyPresenter(this);
        initData();
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
    }

    @Override
    protected void onDestroy() {
        Log.e("ff","onDestroy");
        verifyPresenter.dettachActivity();
        super.onDestroy();
    }

    private void initData() {
        Intent intent = getIntent();
        pwd = intent.getStringExtra(SignInActivity.PWD);
        eemail = intent.getStringExtra(SignInActivity.EMAIL);
        pnb = intent.getStringExtra(SignInActivity.PNB);
        stu = intent.getStringExtra(SignInActivity.STU);
        session = intent.getStringExtra(SignInActivity.SESSION);
        email.setText("验证码已发送到关联邮箱 " + eemail);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
    }

    @OnClick({R.id.verify_sure, R.id.verify_back})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.verify_sure: {
                String checkcode = verificationCodeView.getInputContent();
                Log.e("Verify", checkcode);
                Log.e("Verify", eemail + stu + pwd + pnb + session);
                verifyPresenter.getRegister(checkcode,stu,stu, pwd, pnb, session);
                break;
            }
            case R.id.verify_back: {
                onDestroy();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void getregister(int status,String msg) {
        if (status == 200) {
            FancyToast.makeText(VerifyActivity.this, "注册成功", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
            Intent intent = new Intent(VerifyActivity.this, SignInActivity.class);
            intent.putExtra(SignInActivity.PWD, pwd);
            intent.putExtra(SignInActivity.PNB, pnb);
            intent.putExtra(SignInActivity.EMAIL, eemail);
            intent.putExtra(SignInActivity.STU, stu);
            intent.putExtra(SignInActivity.SESSION, session);
            intent.putExtra(SIGNIN, 1);
            startActivity(intent);
            onPause();
        } else {
            FancyToast.makeText(VerifyActivity.this, msg, FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }

    @Override
    public void showcheckcodestatus(Boolean status) {
        if (status) {
//            FancyToast.makeText(VerifyActivity.this,"验证码一致",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
        } else {
            FancyToast.makeText(VerifyActivity.this, "验证码不一致", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
