package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.yf107.teamwork.lostandfound.adapter.UploadFragmentAdapter;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.UploadItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yf107.teamwork.lostandfound.view.activity.MainActivity.QISHILEIXING;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description
 * 上传失物/招领启事界面

 */
public class UploadActivity extends AppCompatActivity {

    public static final String ACTION_SHORTCUT_2 = "SHORTCUT_2";//shortcuts跳转需要

    private List<UploadItemBean> lists = new ArrayList<>();

    @BindView(R.id.upload_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.upload_toolbar)
    Toolbar mToolbar;

    private SharedPreferences sharedPreferences;

    private UploadFragmentAdapter uploadFragmentAdapter;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ActivityManager.getActivityManager().add(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        if (savedInstanceState == null) {
            initLists();
        }
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void initView() {
        Intent intent = getIntent();
        Integer qishileixing = intent.getIntExtra(QISHILEIXING, 0);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this, statusBarView);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        UploadFragmentAdapter uploadFragmentAdapter = new UploadFragmentAdapter(lists, qishileixing);
        mRecyclerView.setAdapter(uploadFragmentAdapter);
    }

    public void initLists() {
        int i = AllURI.allTypeImgsList.size();
        String session = sharedPreferences.getString(SESSION, " ");
        for (int k = 0; k < i; k++) {
            String s = "";
            s = AllURI.getTypePhoto(session, AllURI.allTypeImgsList.get(k));
            UploadItemBean uploadItemBean = new UploadItemBean(s, AllURI.allTypeBeanList.get(k));
            lists.add(uploadItemBean);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.upload_lost: {
                /*
                切换为失物界面
                如果以及是失物界面
                */
                if (item.isChecked()) {
                    Toast.makeText(UploadActivity.this, "已经是失物启事页面了", Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                break;
            }
            case R.id.upload_find: {
                /*
                 同上
                 */
                if (item.isChecked()) {
                    Toast.makeText(UploadActivity.this, "已经是招领启事页面了", Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                break;
            }
            default: {
                break;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
