package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.yf107.teamwork.lostandfound.view.interfaces.IChangePasswordView;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.PWD;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener , IChangePasswordView {

    Toolbar toolbar;
    EditText change_new_password;
    TextView change_new_password_long;
    EditText change_new_password_confirm;
    Button change_submit;
    Button change_all_clear1;
    TextView change_new_password_wrong;
    private SharedPreferences sharedPreferences;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        toolbar = findViewById(R.id.changepassword_tablayout);
        change_all_clear1 = findViewById(R.id.change_all_clear1);
        change_submit = findViewById(R.id.change_submit);
        change_new_password = findViewById(R.id.change_new_password);
        change_new_password_long = findViewById(R.id.change_new_password_long);
        change_new_password_confirm = findViewById(R.id.change_new_password_confirm);
        change_new_password_wrong = findViewById(R.id.change_new_password_wrong);
        changePasswordPresenter = new ChangePasswordPresenter(new ChangePasswordModel());
        changePasswordPresenter.attachActivity(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);


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

        change_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("change进入没","进入了");
                String session =  sharedPreferences.getString(SESSION,null);
                String email = sharedPreferences.getString(EMAIL,null);
                changePasswordPresenter.uploadPassword(change_new_password.getText().toString(),session,email);
            }
        });

        change_all_clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_new_password_confirm.setText("");
                change_new_password_confirm.requestFocusFromTouch();
            }
        });

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.change_submit: {
//
//                Log.d("change进入没","进入了");
//                   String session =  sharedPreferences.getString(SESSION,null);
//                   String email = sharedPreferences.getString(EMAIL,null);
//                    changePasswordPresenter.uploadPassword(change_new_password.getText().toString(),session,email);
////                    Intent intent = new Intent(this, MainActivity.class);
////                    intent.putExtra(PWD, change_new_password.getText().toString());
////                    intent.putExtra("ForgetorLogin", "Foeget");
////                    startActivity(intent);
////                    finish();
//                    break;
//            }
//
//            case R.id.change_all_clear1: {
//                change_new_password_confirm.setText("");
//                change_new_password_confirm.requestFocusFromTouch();
//                break;
//            }

//
//            case R.id.reset_getmailbox: {
//
////                if (new_password != new_password_confirm) {
////                    new_password_wrong.setVisibility(View.VISIBLE);
////                }
////                if (!isEmail(email)) {
////                    mailbox_wrong.setVisibility(View.VISIBLE);
////                }
//
//                forgetPasswordPrensenter.getCode(email);
//                Log.d("wofole", "nmsl");
//                timeCount.start();
//
//                break;
//            }

//            case R.id.reset_password_back: {
//                finish();
//                break;
//            }


        }

    @Override
    public void getStatus(int status) {
        Log.d("changepass", String.valueOf(status));

        if(status == 200){
            FancyToast.makeText(this, "修改密码成功",Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            finish();
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
}
