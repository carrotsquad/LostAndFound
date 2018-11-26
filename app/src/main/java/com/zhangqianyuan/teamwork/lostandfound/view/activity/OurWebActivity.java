package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.OurWebsite;

public class OurWebActivity extends AppCompatActivity {

    @BindView(R.id.ourweb)
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_web);
        ButterKnife.bind(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setDomStorageEnabled(true);//打开DOM存储API
        web.setWebChromeClient(new WebChromeClient());
        web.loadUrl(OurWebsite);
    }
}
