package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.haha.perflib.analysis.TopologicalSort;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IForgetPasswordPresenter;
import com.zhangqianyuan.teamwork.lostandfound.utils.EditUtil;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PWD;

public class ForgetPasswordActivity extends AppCompatActivity implements IForgetPasswordActivity {
    private String SESSION;
    private String email;

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

    private IForgetPasswordPresenter iForgetPasswordPresenter;

    String mailbox111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        EditUtil.EditAllClear(all_clear,new_password_confirm);
    }


    @OnClick({R.id.reset_getmailbox, R.id.reset_submit, R.id.reset_all_clear1})
    void onClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_submit:{
                if(SESSION.equals(confirm.getText())) {
                    Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT);
                    Intent intent = new Intent(ForgetPasswordActivity.this,SignInActivity.class);
                    intent.putExtra(PWD,new_password.getText().toString());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT);
                }
            }

            case R.id.reset_all_clear1:{
                new_password_confirm.setText("");
                new_password_confirm.requestFocusFromTouch();
                break;
            }

            case R.id.reset_getmailbox:{
                email = mailbox.getText().toString();
                iForgetPasswordPresenter.getCodeStatus(email);
                break;

            }
            default: {
                break;
            }
        }

    }

    @Override
    public void showEmailStatus(Integer status, String session) {
        switch (status){
            //发送验证码成功
            case 200:{
                SESSION = session;
            }
            //发送验证码失败或邮箱不存在
            case 400:{

            }

        }

    }

    @Override
    public void showcheckcodestatus(Integer status) {
        if(!status.equals(confirm.getText())){
            mailbox_wrong.setText("验证码不一致！");
        }

    }
}
