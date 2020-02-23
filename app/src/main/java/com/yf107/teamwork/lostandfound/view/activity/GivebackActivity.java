package com.yf107.teamwork.lostandfound.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.yf107.teamwork.lostandfound.R;

public class GivebackActivity extends AppCompatActivity {
    EditText qq;
    EditText phone;
    Button sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giveback);
        qq = findViewById(R.id.qq);
        phone = findViewById(R.id.phone);
        sure = findViewById(R.id.gb_sure);
    }
}
