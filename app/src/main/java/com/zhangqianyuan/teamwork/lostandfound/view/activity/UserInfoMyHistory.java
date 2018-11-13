package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyHistoryAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 * 我的板块 我的历史界面
 * @author   zhou
 */

/**
 *
 *
 *
 */
// TODO: 2018/11/13 数据加入逻辑
public class UserInfoMyHistory extends AppCompatActivity {
    @BindView(R.id.userinfo_myhistory_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.userinfo_myhistory_toolbar)
    Toolbar mToolbar;

    private List<MyHistoryItemBean> mMyHistoryItemBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_history);
        initView();
        initList();
    }

    public void initView(){
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyHistoryAdapter(mMyHistoryItemBeans));
    }

    public void initList(){
        for (int i = 0; i <50 ; i++) {
            MyHistoryItemBean bean = new MyHistoryItemBean(R.drawable.gril6,R.drawable.gril6
            ,true,"fhh","10.10.10");
            mMyHistoryItemBeans.add(bean);
        }
    }

}

