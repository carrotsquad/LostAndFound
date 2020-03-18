package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.utils.EditUtil;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.utils.ViewUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.ILogInActivity;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.LogInPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.STU;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.PNB;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.PWD;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description: 这是注册页面！！！！！！
 */
public class LogInActivity extends AppCompatActivity implements ILogInActivity {

    @BindView(R.id.login_student)
    EditText loginStudent;

    @BindView(R.id.all_clear)
    Button  all_clear;

    @BindView(R.id.all_clear2)
    Button all_clear2;

    @BindView(R.id.login_password)
    EditText loginPassword;

    @BindView(R.id.login_repassword)
    EditText loginRepassword;

    @BindView(R.id.checkout)
    TextView checkOut;

    @BindView(R.id.login_email)
    EditText loginEmail;

    @BindView(R.id.login_phonenumber)
    EditText loginPhone;

    @BindView(R.id.login_sure)
    Button button;

    @BindView(R.id.btn_usdetail)
    Button btn_usdetail;

    @BindView(R.id.login_back)
    Button back;

    private String stu;
    private String pwd;
    private String repwd;
    private String pnb;
    private String email;
    private LogInPresenter iLogInPresenter;

    public static final int pwdshortestlength = 8;
    public static final int pwdlongestlength = 24;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
        iLogInPresenter = new LogInPresenter(this);
        // 给学号编辑框添加文本变化监听器
        loginStudent.addTextChangedListener(new CheckOutTextWatcher(loginStudent, loginPassword));
        // 给手机编辑框添加文本变化监听器
        loginPhone.addTextChangedListener(new CheckOutTextWatcher(loginPhone));
        // 给密码编辑框添加文本变化监听器
        loginRepassword.addTextChangedListener(new CheckOutTextWatcher(loginPassword, loginRepassword));
        EditUtil.EditAllClear(all_clear,loginPassword);
        EditUtil.EditAllClear(all_clear2,loginRepassword);
    }
    @Override
    protected void onDestroy() {
        iLogInPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.login_sure, R.id.btn_usdetail, R.id.all_clear, R.id.all_clear2,R.id.cl_hide})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.login_sure: {
                stu = loginStudent.getText().toString();
                pwd = loginPassword.getText().toString();
                repwd = loginRepassword.getText().toString();
                pnb = loginPhone.getText().toString();
                email = loginEmail.getText().toString();
                Log.e("LoginActivity",""+stu+pwd+repwd+pnb+email);
                if ("".equals(pwd) || "".equals(repwd) || "".equals(pnb) || "".equals(email) || "".equals(stu)) {
                    FancyToast.makeText(this, "请填写完整", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                }else if (!pwd.equals(repwd)) {
                    FancyToast.makeText(this, "密码不一致", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                } else if (pwd.length()<pwdshortestlength||pwd.length()>pwdlongestlength){
                    FancyToast.makeText(this, "密码长度8～24位", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                } else if(!isEmail(email)) {
                    FancyToast.makeText(this, "邮箱格式错误", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                } else if (!isPhonenumber(pnb)) {
                    FancyToast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                } else {
                    iLogInPresenter.getCodeStatus(email);
                }
                break;
            }
            case R.id.btn_usdetail:{
                Intent intent = new Intent(this, AboutUsDetailActivity.class);
                intent.putExtra("isExit1", true);
                startActivity(intent);
                break;
            }
            case R.id.all_clear:{
                loginPassword.setText("");
                loginRepassword.requestFocusFromTouch();
                break;
            }
            case R.id.all_clear2:{
                loginRepassword.setText("");
                loginRepassword.requestFocusFromTouch();
                break;
            }
            case R.id.cl_hide:{
                ViewUtil.hideOneInputMethod(LogInActivity.this, loginPhone);
                break;
            }

            case R.id.login_back:{
                finish();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void showEmailStatus(Integer status, String session) {
        switch (status) {
            case 200: {
                FancyToast.makeText(this, "发送验证码成功", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                Intent intent = new Intent(LogInActivity.this, VerifyActivity.class);
                intent.putExtra(PWD, pwd);
                intent.putExtra(PNB, pnb);
                intent.putExtra(EMAIL, email);
                intent.putExtra(STU, stu);
                intent.putExtra(SESSION, session);
                startActivity(intent);
                break;
            }
            case 201: {
                FancyToast.makeText(this, "邮箱已经存在", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                break;
            }
            case 400: {
                FancyToast.makeText(this, "发送验证码失败或邮箱不存在", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                break;
            }
            default: {
                break;
            }
        }
    }

    private class CheckOutTextWatcher implements TextWatcher {
        private EditText mView; // 声明一个编辑框对象
        private int mMaxLength; // 声明一个最大长度变量
        private CharSequence mStr; // 声明一个文本串
        private EditText mNextView;// 声明下一个视图对象

        public CheckOutTextWatcher(EditText vThis, EditText vNext) {
            super();
            mView = vThis;
            if (vNext != null) {
                mNextView = vNext;
                mMaxLength = ViewUtil.getMaxLength(vThis);
            }
        }

        public CheckOutTextWatcher(EditText v) {
            super();
            mView = v;
            // 通过反射机制获取编辑框的最大长度
            mMaxLength = ViewUtil.getMaxLength(v);
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            mStr = s;
        }

        @Override
        public void afterTextChanged(Editable s) {
            //检查两次密码是否一致
            if (loginPassword != null && loginRepassword != null) {
                checkOut.setVisibility(View.VISIBLE);
                if (loginPassword.getText().toString().equals(loginRepassword.getText().toString())) {
                    checkOut.setVisibility(View.INVISIBLE);
                }
            }

            if (mStr == null || mStr.length() == 0)
                return;
            // 输入文本达到11位时关闭输入法
            if (mStr.length() == 11 && mMaxLength == 11) {
                ViewUtil.hideAllInputMethod(LogInActivity.this);
            }
            // 输入文本达到10位跳转到下一个编辑框
            if (mStr.length() == 10 && mMaxLength == 10) {
                // 让下一个视图获得焦点，即将光标移到下个视图
                mNextView.requestFocus();
                // 让光标自动移到编辑框内部的文本末尾
                EditText et = (EditText) mNextView;
                et.setSelection(et.getText().length());
            }

        }
    }

    //验证邮箱格式
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z]*[\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9]*[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    //验证手机号格式
    public static boolean isPhonenumber(String strnumber) {
        String pattern = "^1\\d{10}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(strnumber);
        return m.matches();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
