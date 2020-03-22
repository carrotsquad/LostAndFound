package com.yf107.teamwork.lostandfound.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.yf107.teamwork.lostandfound.model.MyHistoryModel;
import com.yf107.teamwork.lostandfound.presenter.MyHistoryPresenter;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IMyHistoryActivity;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.adapter.MyHistoryAdapter;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 * 我的板块 我的历史界面
 */
public class UserInfoMyHistory extends AppCompatActivity implements IMyHistoryActivity{
    private MyHistoryAdapter mAdapter;
    private List<TheLostBean> lists =new ArrayList<>();
    private MyHistoryPresenter presenter;

    @BindView(R.id.myhistory_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.myhistory_back)
    ImageView back;
    private View statusBarView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_history);
        ActivityManager.getActivityManager().add(this);
        initView();
        initMvp();
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
    }

    public void initView(){
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        presenter.dettachActivity();
        super.onDestroy();
    }

    public void initMvp(){
        presenter = new MyHistoryPresenter(new MyHistoryModel());
        presenter.attachActivity(this);
        presenter.getMyHistoryData(getSharedPreferences("users",MODE_PRIVATE).getString("SESSION",null),0,15);
    }

    @Override
    public void showData(List<TheLostBean> beans) {
        lists.clear();
        lists.addAll(beans);
        mAdapter = new MyHistoryAdapter(lists);
        mRecyclerView.setAdapter(mAdapter);
//        Log.d("TAG","qwertyuiosjkdhskd"+beans.get(2).getId());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

