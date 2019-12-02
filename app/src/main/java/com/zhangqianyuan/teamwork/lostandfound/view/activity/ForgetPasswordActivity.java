package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.haha.perflib.Value;
import com.squareup.haha.perflib.analysis.TopologicalSort;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.presenter.ForgetPasswordPrensenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IForgetPasswordPresenter;
import com.zhangqianyuan.teamwork.lostandfound.utils.EditUtil;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PWD;

public class ForgetPasswordActivity extends AppCompatActivity implements IForgetPasswordActivity {
    private String SESSION = new String();
    private String email = new String();
    private String checkcode = new String();

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

    private ForgetPasswordPrensenter forgetPasswordPrensenter;

    String mailbox111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        EditUtil.EditAllClear(all_clear, new_password_confirm);
        forgetPasswordPrensenter = new ForgetPasswordPrensenter(this);
    }


    @OnClick({R.id.reset_getmailbox, R.id.reset_submit, R.id.reset_all_clear1})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_submit: {
                if(!new_password.getText().equals(new_password_confirm.getText()))  new_password_wrong.setText("两次密码不一致！");
                forgetPasswordPrensenter.checkCode(SESSION,checkcode);
                if(checkcode.equals(confirm.getText())) {
                    Toast.makeText(this, "修改密码成功", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(ForgetPasswordActivity.this, SignInActivity.class);
                    intent.putExtra(PWD, new_password.getText().toString());
                    intent.putExtra("ForgetorLogin","Foeget");
                    startActivity(intent);
                    finish();
                }else{
                    mailbox_wrong.setText("验证码错误");
                }

                break;
            }

            case R.id.reset_all_clear1: {
                new_password_confirm.setText("");
                new_password_confirm.requestFocusFromTouch();
                break;
            }

            case R.id.reset_getmailbox: {
                email = mailbox.getText().toString();
                forgetPasswordPrensenter.getCodeStatus(email);
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
                Toast.makeText(this, "发送邮箱成功", Toast.LENGTH_SHORT);
                Log.e("TAG","SESSSSSS");
                break;
            }
            case 201: {
                Toast.makeText(this, "已有", Toast.LENGTH_SHORT);
                Log.d("TAG","有邮箱了");
                break;
            }
            case 400: {
                FancyToast.makeText(this, "发送验证码失败或邮箱不存在", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                Log.d("TAG","不存在");
            break;
            }
        }

    }

    @Override
    public void showcheckcodestatus(Boolean status, String session, String checkcode) {
        if(status){
            Toast.makeText(this,"获取验证码成功",Toast.LENGTH_SHORT);
            Log.d("TAG","获取验证码成功了的");
            this.checkcode = checkcode;
            SESSION = session;
        }
        else{
            Log.d("TAG","获取验证码没成功");
        }
    }

}
