package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.model.EditInfoModel;
import com.yf107.teamwork.lostandfound.presenter.EditInfoPresenter;
import com.yf107.teamwork.lostandfound.presenter.EditPhoneNumberPresenter;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IEditPhoneNumberPresenter;
import com.yf107.teamwork.lostandfound.view.application.BaseApp;
import com.yf107.teamwork.lostandfound.view.interfaces.BaseView;
import com.yf107.teamwork.lostandfound.view.interfaces.IEditInfoActivity;
import com.yf107.teamwork.lostandfound.view.interfaces.IRevisePhoneNumberActivity;

public class RevisePhoneNumber extends AppCompatActivity implements IRevisePhoneNumberActivity{
    EditText phone_number;
    Button clear;
    Button sure;
    private EditPhoneNumberPresenter editPhoneNumberPresenter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise_phone_number);
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        phone_number = findViewById(R.id.phone_edit);
        clear = findViewById(R.id.phone_clear);
        sure = findViewById(R.id.phone_sure);
        editPhoneNumberPresenter = new EditPhoneNumberPresenter();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone_number.setText("");
            }
        });

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("wocaonimade", "wocaonima");
                String s = phone_number.getText().toString();
                if (!s.equals("")) {
                    editPhoneNumberPresenter.uoloadPhoneNumber(sharedPreferences.getString("SESSION", null), phone_number.getText().toString());
                    Log.d("?????", "?????");
                } else {
                    FancyToast.makeText(RevisePhoneNumber.this, "请输入", FancyToast.WARNING).show();
                }
            }
        });
    }

    @Override
    public void onSuccess(int status) {
        Log.d("statussss",String.valueOf(status));
        if (status == 200) {
            FancyToast.makeText(this, "修改成功", FancyToast.SUCCESS).show();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("PNB", phone_number.getText().toString());
            editor.commit();
            Log.d("onChangeNickName", sharedPreferences.getString("PNB", null));
            finish();
        } else if (status == 400) {
            FancyToast.makeText(this,"修改失败", FancyToast.ERROR).show();
        }
    }
}
