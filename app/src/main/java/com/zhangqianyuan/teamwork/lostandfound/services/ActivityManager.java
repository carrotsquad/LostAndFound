package com.zhangqianyuan.teamwork.lostandfound.services;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import java.util.Stack;

/**
 * Description
 * 活动管理类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ActivityManager {
    //活动栈
    private  Stack<Activity> activityStack = new Stack<>();

    //碎片栈
    private  Stack<android.support.v4.app.Fragment>  fragmentStack = new Stack<>();

    private static ActivityManager  activityManager  = new ActivityManager();

    public  static ActivityManager getActivityManager(){
        return activityManager;
    }
    private  ActivityManager(){
    }

    //activity的添加
    public void add(Activity activity){
        if (activity!=null){
            activityStack.add(activity);
            Log.d("wow","size"+activityStack.size());
        }
    }

    //删除所有activity
    public void removeAll(){
        for (int i = 0; i <activityStack.size() ; i++) {
            activityStack.get(i).finish();
            activityStack.remove(i);
        }
        Log.d("wow",""+activityStack.size());
    }


    public void addF(android.support.v4.app.Fragment fragment){
        if (fragment!=null){
            fragmentStack.add(fragment);
        }
    }

    public void removeFAll(){
        for (int i = 0; i <fragmentStack.size() ; i++) {
            fragmentStack.get(i).getActivity().onBackPressed();
            fragmentStack.remove(i);
        }
    }


}
