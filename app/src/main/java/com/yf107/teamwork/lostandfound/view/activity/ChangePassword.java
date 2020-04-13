package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.sip.SipSession;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.model.ChangePasswordModel;
import com.yf107.teamwork.lostandfound.model.interfaces.IChangePasswordModel;
import com.yf107.teamwork.lostandfound.presenter.ChangePasswordPresenter;
import com.yf107.teamwork.lostandfound.presenter.ForgetPasswordPrensenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IChangePasswordView;
import com.yf107.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.ISEXIT;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.PWD;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener,IForgetPasswordActivity {

    Toolbar toolbar;
    EditText change_new_password;
    TextView change_new_password_long;
    EditText change_new_password_confirm;
    Button change_submit;
    Button change_all_clear1;
    TextView change_new_password_wrong;
    Button getmailbox;
    EditText change_status;
    TextView statuswrong;
    private SharedPreferences sharedPreferences;
    private ForgetPasswordPrensenter forgetPasswordPrensenter;
    private TimeCount timeCount;
    String email;
    boolean isright = false;
    String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        toolbar = findViewById(R.id.changepassword_tablayout);
        getmailbox = findViewById(R.id.change_getmailbox);
        change_all_clear1 = findViewById(R.id.change_all_clear1);
        change_submit = findViewById(R.id.change_submit);
        change_status = findViewById(R.id.change_status);
        statuswrong = findViewById(R.id.change_status_wrong);
        change_new_password = findViewById(R.id.change_new_password);
        change_new_password_long = findViewById(R.id.change_new_password_long);
        change_new_password_confirm = findViewById(R.id.change_new_password_confirm);
        change_new_password_wrong = findViewById(R.id.change_new_password_wrong);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("EMAIL",null);
        forgetPasswordPrensenter = new ForgetPasswordPrensenter(this);

        timeCount = new TimeCount(60000, 1000);

        statuswrong.setVisibility(View.INVISIBLE);

        getmailbox.setOnClickListener(this);
        change_submit.setOnClickListener(this);
        change_all_clear1.setOnClickListener(this);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }

        change_new_password.addTextChangedListener(new CheckOutText());
        change_new_password_confirm.addTextChangedListener(new CheckOutText());

//        change_submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("change进入没","进入了");
//                String session =  sharedPreferences.getString(SESSION,null);
//                String email = sharedPreferences.getString(EMAIL,null);
//              //  changePasswordPresenter.uploadPassword(change_new_password.getText().toString(),session,email);
//            }
//        });
//
//        change_all_clear1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                change_new_password_confirm.setText("");
//                change_new_password_confirm.requestFocusFromTouch();
//            }
//        });
//
//        getmailbox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }


    @Override
    public void showcheckcodestatus(Boolean status, String session) {
        isright = status;
        this.session = session;

    }

    @Override
    public void checkCodeIsRight(Boolean status) {


        FancyToast.makeText(this,"修改成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();

        if(!status){
            statuswrong.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_submit: {

                if(isright) {
                    Log.d("change进入没", "进入了");
                    //      session = sharedPreferences.getString(SESSION, null);
                    String email = sharedPreferences.getString(EMAIL, null);
                    forgetPasswordPrensenter.IsRight(session,change_status.getText().toString(),change_new_password.getText().toString(),email);
                    sharedPreferences.edit().putString(ISEXIT,"false").commit();

//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(PWD, change_new_password.getText().toString());
                    finish();
//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.putExtra(PWD, change_new_password.getText().toString());
//                    intent.putExtra("ForgetorLogin", "Foeget");
//                    startActivity(intent);
//                    finish();
                    break;
                }
            }

            case R.id.change_all_clear1: {
                change_new_password_confirm.setText("");
                change_new_password_confirm.requestFocusFromTouch();
                break;
            }


            case R.id.change_getmailbox: {

//                if (new_password != new_password_confirm) {
//                    new_password_wrong.setVisibility(View.VISIBLE);
//                }
//                if (!isEmail(email)) {
//                    mailbox_wrong.setVisibility(View.VISIBLE);
//                }

                if(change_new_password.getText().toString() .equals(change_new_password_confirm.getText().toString())) {

                    forgetPasswordPrensenter.getCode(email);
                    Log.d("wofole", "nmsl");
                    timeCount.start();
                }

                break;
            }

//            case R.id.change_password_back: {
//                finish();
//                break;
//            }

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

            if(change_new_password.getText().length() <8||change_new_password.getText().length()>16){
                change_new_password_long.setVisibility(View.VISIBLE);
            }else {
                change_new_password_long.setVisibility(View.INVISIBLE);
            }

            //检查两次密码是否一致
            if (!change_new_password.getText().toString().equals("") && !change_new_password_confirm.getText().toString().equals("")){
                change_new_password_wrong.setVisibility(View.VISIBLE);
                if (change_new_password_confirm.getText().toString().equals(change_new_password.getText().toString())) {
                    change_new_password_wrong.setVisibility(View.INVISIBLE);
                }
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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
}
