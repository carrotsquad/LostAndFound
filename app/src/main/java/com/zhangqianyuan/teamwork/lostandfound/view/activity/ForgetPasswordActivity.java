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
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.utils.EditUtil;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IForgetPasswordActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
                Intent intent = new Intent(ForgetPasswordActivity.this,VerifyActivity.class);
                startActivity(intent);
                finish();
                break;
            }

            case R.id.reset_all_clear1:{
                new_password_confirm.setText("");
                break;
            }

            case R.id.reset_getmailbox:{

            }
            default: {
                break;
            }
        }

    }

    @Override
    public void showEmailStatus(Integer status, String session) {

    }
}
