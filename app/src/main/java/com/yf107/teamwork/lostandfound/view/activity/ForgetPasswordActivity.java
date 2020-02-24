package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yf107.teamwork.lostandfound.utils.EditUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.ForgetPasswordPrensenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.PWD;

public class ForgetPasswordActivity extends AppCompatActivity implements IForgetPasswordActivity {
    String email = new String();

    @BindView(R.id.reset_new_password)
    EditText new_password;

    @BindView(R.id.reset_new_password_confirm)
    EditText new_password_confirm;

    @BindView(R.id.reset_mailbox)
    EditText mailbox;

    @BindView(R.id.reset_getmailbox)
    Button getmailbox;

    @BindView(R.id.reset_confirm)
    EditText confirm;

    @BindView(R.id.reset_new_password_wrong)
    TextView new_password_wrong;

    @BindView(R.id.reset_all_clear1)
    Button all_clear;

    @BindView(R.id.reset_submit)
    Button submit;

    @BindView(R.id.reset_mailbox_wrong)
    TextView mailbox_wrong;

    @BindView(R.id.status_wrong)
    TextView statu_wrong;


    private ForgetPasswordPrensenter forgetPasswordPrensenter;

    String mailbox111;

    String session;
    Boolean isright;

    String s;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        EditUtil.EditAllClear(all_clear, new_password_confirm);
        new_password_wrong.setVisibility(View.INVISIBLE);
        statu_wrong.setVisibility(View.INVISIBLE);
        mailbox_wrong.setVisibility(View.INVISIBLE);
        forgetPasswordPrensenter = new ForgetPasswordPrensenter(this);
    }


    @OnClick({R.id.reset_getmailbox, R.id.reset_submit, R.id.reset_all_clear1})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_submit: {

                if(isright) {
                    forgetPasswordPrensenter.IsRight(session, confirm.getText().toString(), new_password.getText().toString(), email);
                    Intent intent = new Intent(ForgetPasswordActivity.this, SignInActivity.class);
                    intent.putExtra(PWD, new_password.getText().toString());
                    intent.putExtra("ForgetorLogin", "Foeget");
                    startActivity(intent);
                    finish();
                }else{
                    statu_wrong.setVisibility(View.VISIBLE);
                }


                break;
            }

            case R.id.reset_all_clear1: {
                new_password_confirm.setText("");
                new_password_confirm.requestFocusFromTouch();
                break;
            }

            case R.id.reset_getmailbox: {

                if(new_password != new_password_confirm){
                    new_password_wrong.setVisibility(View.VISIBLE);
                }
                if(!isEmail(email)){
                    mailbox_wrong.setVisibility(View.VISIBLE);
                }

                email = mailbox.getText().toString();
                forgetPasswordPrensenter.getCode(email);
            }
                break;

        }

    }


    @Override
    public void showcheckcodestatus(Boolean status, String session) {

        isright = status;
        if (status) {
            this.session = session;
            Log.d("session",session);
            Toast.makeText(this,"发送成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkCodeIsRight(Boolean status) {

        Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();
        if(!status){
          //  statu_wrong.setText("验证码错误！");
        }

    }

    //验证邮箱格式
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z]*[\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9]*[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }



}