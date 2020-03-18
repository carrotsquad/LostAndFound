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

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.presenter.ReturnPresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IGiveBackView;

public class GivebackActivity extends AppCompatActivity implements IGiveBackView {
    EditText qq;
    EditText phone;
    Button sure;
    TextView guihuan_zhanling;
    TextView tishi;
    Button back;
    TextView warning;
    ReturnPresenter returnPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveback);
        qq = findViewById(R.id.qq);
        phone = findViewById(R.id.phone);
        sure = findViewById(R.id.gb_sure);
        guihuan_zhanling = findViewById(R.id.guihuan_zhaoling);
        tishi = findViewById(R.id.tishi);
        back = findViewById(R.id.giveback_back);
        warning = findViewById(R.id.warning);
        returnPresenter = new ReturnPresenter();
        returnPresenter.attachActivity(this);
        warning.setVisibility(View.INVISIBLE);


        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
        int id =  intent.getIntExtra("ID", 0);
        String session = intent.getStringExtra("SESSION");
        //0是丢失，1是捡到

        if(type.equals("0")){
            sure.setBackgroundResource(R.drawable.sure_back);
            tishi.setText("    为确保您确实是失物的主人，递爱要对您做信息的录入，且方便什物者联系你");
            guihuan_zhanling.setText("我要归还");
        }else{
            sure.setBackgroundResource(R.drawable.sure_get);
            tishi.setText("    为了方便失主联系您，递爱要对您做信息的录入");
            guihuan_zhanling.setText("我要招领");
        }

        sure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (!qq.getText().toString().equals("") && !phone.getText().equals("")) {
                                            Log.d("进入归还了吗", "进入了");
                                            returnPresenter.sendMessage(session, id, qq.getText().toString(), phone.getText().toString());
                                        } else {
                                            warning.setVisibility(View.VISIBLE);
                                            FancyToast.makeText(GivebackActivity.this,"请至少填写一种联系方式",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                                            Log.d("进入归还了吗", "没进了");
                                        }
                                    }
                                });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showStatus(Boolean status) {
        Log.d("状态", String.valueOf(status));
        if(status){
            FancyToast.makeText(GivebackActivity.this,"填写成功！",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS, false).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        returnPresenter.dettachActivity();
    }
}
