package com.yf107.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.yf107.teamwork.lostandfound.adapter.MyLoadItemAdapter;
import com.yf107.teamwork.lostandfound.model.MyLoadModel;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.services.UpdateMessageService;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IMyLoadActivity;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;
import com.yf107.teamwork.lostandfound.presenter.MyLoadPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.STU;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * Description
 * 我的板块中 我的发布板块
 *
 */
public class UserInfoMyUpload extends AppCompatActivity implements IMyLoadActivity {
    private List<TheLostBean> lists = new ArrayList<>();
    private MyLoadPresenter presenter = new MyLoadPresenter(new MyLoadModel());
    private MyLoadItemAdapter mAdapter;
    private SharedPreferences sharedPreferences;
    public static int lostidddd;
    @BindView(R.id.myload_list)
    RecyclerView list;

    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_upload);
        initMvp();
        ActivityManager.getActivityManager().add(this);
        initView();
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
    }

    public void initView() {
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        ButterKnife.bind(this);
        Log.d("123456", "success");
        list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyLoadItemAdapter(this,lists, sharedPreferences.getString(USERPHOTO, "null"), sharedPreferences.getString(EMAIL, "null")
                , sharedPreferences.getString(STU, "null"), true);
        mAdapter.attachPresenter(presenter);
        list.setAdapter(mAdapter);

        Toolbar toolbar = findViewById(R.id.setting_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(UserInfoMyUpload.this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

    public void initMvp() {
        presenter.attachActivity(this);
        presenter.getMyloadData(getSharedPreferences("users", MODE_PRIVATE).getString("SESSION", null), 0, 15);
    }

    @Override
    public void showData(List<TheLostBean> beans) {
        lists.clear();
        lists.addAll(beans);
        if(lists.size() == 0){
            lostidddd = -1;
        }else{
            lostidddd = beans.get(lists.size() - 1).getId();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showStatus(Boolean seesion) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        presenter.dettachActivity();
        super.onDestroy();
    }
}
