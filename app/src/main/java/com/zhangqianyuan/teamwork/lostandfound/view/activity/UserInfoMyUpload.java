package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyLoadItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MyLoadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.STU;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * Description
 * 我的板块中 我的发布板块
 *
 * @author zhou
 */
public class UserInfoMyUpload extends AppCompatActivity implements IMyLoadActivity {
    private List<TheLostBean> lists = new ArrayList<>();
    private MyLoadPresenter presenter = new MyLoadPresenter(new MyLoadModel());
    private MyLoadItemAdapter mAdapter;
    private SharedPreferences sharedPreferences;
    @BindView(R.id.myload_list)
    RecyclerView list;

    @BindView(R.id.myupload_back)
    ImageView back;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_my_upload);
        initMvp();
        ActivityManager.getActivityManager().add(this);
        initView();
        //实现渐变式状态栏
        setGradientStatusBarColor(this,statusBarView);
    }

    public void initView() {
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        ButterKnife.bind(this);
        Log.d("123456", "success");
        list.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyLoadItemAdapter(lists, sharedPreferences.getString(USERPHOTO, "null"), sharedPreferences.getString(EMAIL, "null")
                , sharedPreferences.getString(STU, "null"), true);
        list.setAdapter(mAdapter);
        back.setOnClickListener(view->finish());
    }

    public void initMvp() {
        presenter.attachActivity(this);
        presenter.getMyloadData(getSharedPreferences("users", MODE_PRIVATE).getString("SESSION", null), 0, 15);
    }

    @Override
    public void showData(List<TheLostBean> beans) {
        lists.clear();
        lists.addAll(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void delete(String session) {

    }
}
