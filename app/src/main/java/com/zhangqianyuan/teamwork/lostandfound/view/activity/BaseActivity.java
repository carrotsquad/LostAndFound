package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhangqianyuan.teamwork.lostandfound.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理所有activity的 activity
 */
public class BaseActivity extends AppCompatActivity {
    private List<Activity> mActivityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public  void addActivity(Activity activity){
        //判断集合中是否已经添加，添加过的则不再添加
      if (!mActivityList.contains(activity))
          mActivityList.add(activity);
    }

    /**
     * onDestroy()时删除
     * @param activity
     */
    public void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }

    /**
     * 关闭所有Activity
     */
    public  void finishAllActivity(){
        for (Activity activity : mActivityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }


}
