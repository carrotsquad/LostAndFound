package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ILogInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Description: 注册页面
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

    private String pwd;
    private String repwd;
    private String pnb;
    private String email;
    private String nickname;
    public static final String PWD = "PWD";
    public static final String PNB = "PNB";
    public static final String EMAIL = "EMAIL";
    public static final String NICKNAME = "NICKNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.login_sure})
    void OnClicked(View view){
        switch (view.getId()){
            case R.id.login_sure:{
                do {
                    pwd = loginPassword.getText().toString();
                    repwd = loginRepassword.getText().toString();
                    pnb = loginPhone.getText().toString();
                    email = loginEmail.getText().toString();
                    nickname = loginNickname.getText().toString();
                    if(!pwd.equals(repwd)){
                        FancyToast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                    }
                    Intent intent = new Intent(LogInActivity.this,VerifyActivity.class);

                }while (!pwd.equals(repwd));
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void showEmailStatus(Integer status) {
        switch (status){
            case 200:{
                FancyToast.makeText(this,"发送验证码成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
                Intent intent = new Intent(LogInActivity.this,VerifyActivity.class);
                intent.putExtra(PWD,pwd);
                intent.putExtra(PNB,pnb);
                intent.putExtra(EMAIL,email);
                intent.putExtra(NICKNAME,nickname);
                startActivity(intent);
                break;
            }
            case 201:{
                FancyToast.makeText(this,"邮箱已经存在",FancyToast.LENGTH_SHORT,FancyToast.WARNING,true).show();
                break;
            }
            case 400:{
                FancyToast.makeText(this,"发送验证码失败",FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                break;
            }
            default:{
                break;
            }
        }
    }
}
