package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyLoadItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Description
 * 我的板块中 我的发布板块
 * @author  zhou
 */
// TODO: 2018/11/13  加入 上传item数据
public class UserInfoMyUpload extends AppCompatActivity {

    @BindView(R.id.userinfo_myupload_list)
    RecyclerView  mRecyclerView;

    @BindView(R.id.userinfo_myupload_head)   //头像 不可更改
    CircleImageView mCircleImageView;

    @BindView(R.id.userinfo_myupload_toolbar)
    Toolbar  mToolbar;

    List<MyLoadItemBean> mMyLoadItemBeanList = new ArrayList<>();
    MyLoadItemAdapter  mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_upload);

    }

    public void initView(){
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
