package com.yf107.teamwork.lostandfound.view.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.adapter.MyMessageAdapter;
import com.yf107.teamwork.lostandfound.adapter.SearchItemAdapter;
import com.yf107.teamwork.lostandfound.event.MessageEvent;
import com.yf107.teamwork.lostandfound.presenter.MessagePresenter;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.view.interfaces.IMessageFragment;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.yf107.teamwork.lostandfound.view.activity.MainActivity.itemView;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.STU;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * Description: 消息fragment
 */
public class MessageFragment extends Fragment implements IMessageFragment, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private Context mContext;


    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<UpDateMessageBean.DynamicsBeanX> list = new ArrayList<UpDateMessageBean.DynamicsBeanX>();
    private MessagePresenter messagePresenter;
    private GridLayoutManager gridLayoutManager;
    private SearchItemAdapter searchItemAdapter;
    private SharedPreferences sharedPreferences;
    private Boolean isFresh = false;
    MyMessageAdapter myMessageAdapter;
    Activity activity = getActivity();
    Badge badge;
    Boolean aBoolean;
    Message message;
    Message message1;
    private List<Integer> changelist = new ArrayList<>();

    public static int time = 0;
    private Object MainActivity = com.yf107.teamwork.lostandfound.view.activity.MainActivity.class;

    public static Fragment newInstance(){
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }
    Handler handler;
    Handler handler1;


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
        activity = getActivity();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0: {
                        Log.d("进入了吗", "进入了");

                        if (badge != null) {
                            badge.hide(false);
                        }
                        badge = new QBadgeView(activity)
                                .bindTarget(itemView)
                                .setShowShadow(true)
                                .setBadgeGravity(Gravity.END | Gravity.TOP)
                                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                                    @Override
                                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                                    }
                                }).setBadgeText("");
                        break;
                    }
                    case 1:{
                        Log.d("进入了吗1","进入了");
                        if(badge != null){
                            badge.hide(false);
                        }
                    }
                }
            }
        };
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
       // searchItemAdapter = new SearchItemAdapter((ArrayList<DynamicItemBean>) list,changelist,getActivity(),true);
        myMessageAdapter = new MyMessageAdapter((ArrayList<UpDateMessageBean.DynamicsBeanX>) list,changelist,getActivity(),true);
        myMessageAdapter.attachPresenter(messagePresenter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(myMessageAdapter);
    }

    private void initList(){
        String username = sharedPreferences.getString(EMAIL,"null");
        String userphoto = sharedPreferences.getString(USERPHOTO,"null");
        String usernickname = sharedPreferences.getString(STU,"null");
        String jsessionid = sharedPreferences.getString(SESSION,"null");
        messagePresenter.getMessageData(usernickname, userphoto, username, jsessionid, 0, 500);
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
    public void onDataBack(Boolean status, List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList) {
        refreshLayout.setRefreshing(false);
        if(!status){
            FancyToast.makeText(mContext,"出现了问题",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }else {
            if(isFresh){
                isFresh = false;
                FancyToast.makeText(mContext,"刷新成功",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
            }
            list.clear();
            list.addAll(dynamicItemBeanList);


            for (int i =0 ; i<list.size(); i++) {
                changelist.add(0);

            }
            myMessageAdapter.notifyDataSetChanged();
            message = new Message();
            if(dynamicItemBeanList.size() != 0) {
                for (int i = 0; i < dynamicItemBeanList.size(); i++) {
                    if (dynamicItemBeanList.get(i).getRead() == 0) {
                        aBoolean = true;
                        break;
                    } else {
                        aBoolean = false;
                    }
                }
            }else {
                aBoolean = false;
            }

            if(aBoolean){
                message.what = 0;
            }else {
                message.what = 1;

            }
            Log.d("我康康message的只", String.valueOf(message.arg1));

            handler.sendMessage(message);

        }
    }

    @Override
    public void isRead(boolean status) {

        if(status){
           // Toast.makeText(getContext(),"修改状态成功",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showStatus(Boolean seesion) {

    }

    @Override
    public void onRefresh() {
        isFresh = true;
        initList();

    }
}
