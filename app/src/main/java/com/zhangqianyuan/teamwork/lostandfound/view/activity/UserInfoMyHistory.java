package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyHistoryAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyLoadItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyHistoryModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MyHistoryPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyHistoryActivity;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description
 * 我的板块 我的历史界面
 * @author   zhou
 */

// TODO: 2018/11/13 数据加入逻辑
public class UserInfoMyHistory extends AppCompatActivity implements IMyHistoryActivity {
    private MyHistoryAdapter mAdapter;
    private List<TheLostBean> lists =new ArrayList<>();
    private MyHistoryPresenter presenter;

    @BindView(R.id.myhistory_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.myhistory_back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_history);
        ActivityManager.getActivityManager().add(this);
        initView();
        initMvp();

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
    }
}

