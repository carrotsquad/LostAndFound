package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yf107.teamwork.lostandfound.R;

public class GivebackActivity extends AppCompatActivity {
    EditText qq;
    EditText phone;
    Button sure;
    TextView guihuan_zhanling;
    TextView tishi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveback);
        qq = findViewById(R.id.qq);
        phone = findViewById(R.id.phone);
        sure = findViewById(R.id.gb_sure);
        guihuan_zhanling = findViewById(R.id.guihuan_zhaoling);
        tishi = findViewById(R.id.tishi);

        Intent intent = getIntent();
        String type = intent.getStringExtra("TYPE");
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
    }
}
