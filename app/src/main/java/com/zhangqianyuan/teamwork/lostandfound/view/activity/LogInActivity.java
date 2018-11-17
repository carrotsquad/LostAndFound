package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.presenter.LogInPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ILogInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.NICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PNB;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PWD;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;


/**
 * Description: 注册页面,英文单词好像弄错了
 * Created at: 2018/10/31 22:06
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class LogInActivity extends AppCompatActivity implements ILogInActivity {

    @BindView(R.id.login_photo)
    CircleImageView loginPhoto;

    @BindView(R.id.login_email)
    EditText loginEmail;

    @BindView(R.id.login_password)
    EditText loginPassword;

    @BindView(R.id.login_repassword)
    EditText loginRepassword;

    @BindView(R.id.login_nickname)
    EditText loginNickname;

    @BindView(R.id.login_phonenumber)
    EditText loginPhone;

    @BindView(R.id.login_sure)
    Button button;

    @BindView(R.id.login_tosignin)
    TextView tosignin;

    private String pwd;
    private String repwd;
    private String pnb;
    private String email;
    private String nickname;


    private LogInPresenter iLogInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        //加下划线
        tosignin.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        iLogInPresenter = new LogInPresenter(this);
    }

    @Override
    protected void onDestroy() {
        iLogInPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.login_sure,R.id.login_tosignin})
    void onClicked(View view){
        switch (view.getId()){
            case R.id.login_sure:{
                pwd = loginPassword.getText().toString();
                repwd = loginRepassword.getText().toString();
                pnb = loginPhone.getText().toString();
                email = loginEmail.getText().toString();
                nickname = loginNickname.getText().toString();
                if(!pwd.equals(repwd)&&!"".equals(pwd)&&!"".equals(repwd)&&!"".equals(pnb)&&!"".equals(email)&&!"".equals(nickname)){
                    FancyToast.makeText(this,"密码不一致或者其他错误",Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                }else {
                    iLogInPresenter.getCodeStatus(email);
                }
                break;
            }
            case R.id.login_tosignin:{
                Intent intent = new Intent(LogInActivity.this,SignInActivity.class);
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
    public void showEmailStatus(Integer status,String session) {
        switch (status){
            case 200:{
                FancyToast.makeText(this,"发送验证码成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                Intent intent = new Intent(LogInActivity.this,VerifyActivity.class);
                intent.putExtra(PWD,pwd);
                intent.putExtra(PNB,pnb);
                intent.putExtra(EMAIL,email);
                intent.putExtra(NICKNAME,nickname);
                intent.putExtra(SESSION,session);
                startActivity(intent);
                break;
            }
            case 201:{
                FancyToast.makeText(this,"邮箱已经存在",FancyToast.LENGTH_SHORT,FancyToast.WARNING,false).show();
                break;
            }
            case 400:{
                FancyToast.makeText(this,"发送验证码失败",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                break;
            }
            default:{
                break;
            }
        }
    }
}
