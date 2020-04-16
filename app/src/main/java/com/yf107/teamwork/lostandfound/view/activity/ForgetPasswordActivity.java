package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

    private SharedPreferences sharedPreferences;

    String mailbox111;

    String session;
    Boolean isright = false;

    String s;
    TimeCount timeCount;
    String email;
    static  String strPattern = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        EditUtil.EditAllClear(all_clear, new_password_confirm);
        Toolbar toolbar = findViewById(R.id.forgetpassword_tablayout);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("EMAIL",null);
        timeCount = new TimeCount(60000, 1000);
        new_password_wrong.setVisibility(View.INVISIBLE);
        statu_wrong.setVisibility(View.INVISIBLE);
        mailbox_wrong.setVisibility(View.INVISIBLE);
        reset_new_password_long.setVisibility(View.INVISIBLE);
        forgetPasswordPrensenter = new ForgetPasswordPrensenter(this);

    //   mailbox.setInputType(EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

//        if(new_password.getText().toString().equals(new_password_confirm.getText().toString())){
//            new_password_wrong.setVisibility(View.INVISIBLE);
//        }else{
//            new_password_wrong.setVisibility(View.VISIBLE);
//        }
//
        new_password.addTextChangedListener(new CheckOutText());
        new_password_confirm.addTextChangedListener(new CheckOutText());
        confirm.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        mailbox.setKeyListener(new NumberKeyListener() {
            @NonNull
            @Override
            protected char[] getAcceptedChars() {
                char[] c = {'.','@','a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','1','2','3','4','5','6','7','8','9','0'};
                return c;
            }

            @Override
            public int getInputType() {
                return 3;
            }
        });
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            statu_wrong.setVisibility(View.VISIBLE);
                        }
                    });
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

                email = mailbox.getText().toString();
                     if(isEmail(email)) {
                         Log.d("email is right", "email is right");
                         if (new_password.getText().toString().equals(new_password_confirm.getText().toString())) {
                             email = mailbox.getText().toString();
                             forgetPasswordPrensenter.getCode(email);
                             Log.d("wofole", "nmsl");
                             timeCount.start();
                         }
                    }else{
                        Log.d("email is not right","email is not right");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mailbox_wrong.setVisibility(View.VISIBLE);
                                Log.d("?","红字显示没");
                            }
                        });

                    }

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
              statu_wrong.setVisibility(View.VISIBLE);
        }

    }

    //验证邮箱格式
    public static boolean isEmail(String strEmail) {

        strPattern = "^[a-zA-Z]*[\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9]*[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
