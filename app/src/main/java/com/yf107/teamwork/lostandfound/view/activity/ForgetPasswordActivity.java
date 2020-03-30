package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    @BindView(R.id.reset_password_back)
    Button back;

    @BindView(R.id.reset_new_password_long)
    TextView reset_new_password_long;


    private ForgetPasswordPrensenter forgetPasswordPrensenter;

    String mailbox111;

    String session;
    Boolean isright = false;

    String s;
    TimeCount timeCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        EditUtil.EditAllClear(all_clear, new_password_confirm);
        timeCount = new TimeCount(60000, 1000);
        new_password_wrong.setVisibility(View.INVISIBLE);
        statu_wrong.setVisibility(View.INVISIBLE);
        mailbox_wrong.setVisibility(View.INVISIBLE);
        reset_new_password_long.setVisibility(View.INVISIBLE);
        forgetPasswordPrensenter = new ForgetPasswordPrensenter(this);

//        if(new_password.getText().toString().equals(new_password_confirm.getText().toString())){
//            new_password_wrong.setVisibility(View.INVISIBLE);
//        }else{
//            new_password_wrong.setVisibility(View.VISIBLE);
//        }
//
        new_password.addTextChangedListener(new CheckOutText());
        new_password_confirm.addTextChangedListener(new CheckOutText());
    }


    @OnClick({R.id.reset_getmailbox, R.id.reset_submit, R.id.reset_all_clear1, R.id.reset_password_back})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_submit: {

                if (isright) {
                    forgetPasswordPrensenter.IsRight(session, confirm.getText().toString(), new_password.getText().toString(), email);
                    Intent intent = new Intent(ForgetPasswordActivity.this, SignInActivity.class);
                    intent.putExtra(PWD, new_password.getText().toString());
                    intent.putExtra("ForgetorLogin", "Foeget");
                    startActivity(intent);
                    finish();
                } else {
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

//                if (new_password != new_password_confirm) {
//                    new_password_wrong.setVisibility(View.VISIBLE);
//                }
                if (!isEmail(email)) {
                    mailbox_wrong.setVisibility(View.VISIBLE);
                }

                email = mailbox.getText().toString();
                forgetPasswordPrensenter.getCode(email);
                Log.d("wofole", "nmsl");
                timeCount.start();

                break;
            }

            case R.id.reset_password_back: {
                finish();
                break;
            }


        }

    }


    @Override
    public void showcheckcodestatus(Boolean status, String session) {

        Log.d("statusssss", String.valueOf(status));
        isright = status;
        if (status) {
            this.session = session;
            Log.d("session", session);
            Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkCodeIsRight(Boolean status) {

        Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT).show();
        if (!status) {
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


    //计时内部类
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            getmailbox.setBackgroundResource(R.drawable.wait_button_shape);
            getmailbox.setClickable(false);

            getmailbox.setText("(" + l / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {

            getmailbox.setBackgroundResource(R.drawable.button_shape);
            getmailbox.setText("获取验证码");
            getmailbox.setClickable(true);
        }
    }


    class CheckOutText implements TextWatcher {


        public CheckOutText() {
            super();
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            if(new_password.getText().length() <8||new_password.getText().length()>16){
                reset_new_password_long.setVisibility(View.VISIBLE);
            }else {
                reset_new_password_long.setVisibility(View.INVISIBLE);
            }

            //检查两次密码是否一致
            if (!new_password.getText().toString().equals("") && !new_password_confirm.getText().toString().equals("")){
                new_password_wrong.setVisibility(View.VISIBLE);
                if (new_password_confirm.getText().toString().equals(new_password.getText().toString())) {
                    new_password_wrong.setVisibility(View.INVISIBLE);
                }
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
