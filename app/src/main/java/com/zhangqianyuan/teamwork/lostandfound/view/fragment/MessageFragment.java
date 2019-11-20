package com.zhangqianyuan.teamwork.lostandfound.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.event.MessageEvent;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MessagePresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMessageFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.STU;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * Description: 消息fragment
 * Created at: 2018/11/13 10:51
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class MessageFragment extends Fragment implements IMessageFragment, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private Context mContext;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<DynamicItemBean> list = new ArrayList<>();
    private MessagePresenter messagePresenter;
    private GridLayoutManager gridLayoutManager;
    private SearchItemAdapter searchItemAdapter;
    private SharedPreferences sharedPreferences;
    private Boolean isFresh = false;
    private List<Integer> changelist = new ArrayList<>();
    boolean a = false;

    public static Fragment newInstance(){
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container,false);
        mContext = getContext();
        messagePresenter = new MessagePresenter(this);
        sharedPreferences = mContext.getSharedPreferences("users",Context.MODE_PRIVATE);
        ActivityManager.getActivityManager().addF(this);
        initView();
        initList();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        searchItemAdapter.setActivity(null);
        messagePresenter.dettachActivity();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        searchItemAdapter.setActivity(null);
        messagePresenter.dettachActivity();
        super.onDestroyView();
    }

    private void initView(){
        refreshLayout = view.findViewById(R.id.message_swiperefresh);
        recyclerView = view.findViewById(R.id.message_recyclerview);
        refreshLayout.setOnRefreshListener(this);
        gridLayoutManager = new GridLayoutManager(mContext,1);
        searchItemAdapter = new SearchItemAdapter((ArrayList<DynamicItemBean>) list,changelist,getActivity(),true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchItemAdapter);
    }

    private void initList(){
        String username = sharedPreferences.getString(EMAIL,"null");
        String userphoto = sharedPreferences.getString(USERPHOTO,"null");
        String usernickname = sharedPreferences.getString(STU,"null");
        String jsessionid = sharedPreferences.getString(SESSION,"null");
        messagePresenter.getMessageData(usernickname,userphoto,username,jsessionid,0,500);
    }


    /**
     * 处理更新消息事件,TODO:待完善
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMessage(MessageEvent messageEvent) {
//        Toast.makeText(getContext(),messageEvent.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDataBack(Boolean status, List<DynamicItemBean> dynamicItemBeanList) {
        refreshLayout.setRefreshing(false);
        if(!status){
            FancyToast.makeText(mContext,"出现了问题",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }else {
            if(isFresh){
                isFresh = false;
                FancyToast.makeText(mContext,"刷新成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            }
            /**
             * 是否出现小红点，不晓得逻辑写对没
             */
            int l = list.size();
            list.clear();
            list.addAll(dynamicItemBeanList);
            int s = list.size();
            if(l != s){
                a = true;
            }else {
                a = false;
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("OR",a);
            startActivity(intent);
            for (int i =0 ; i<list.size(); i++) {
                changelist.add(0);
            }
            searchItemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        isFresh = true;
        initList();
    }
}
