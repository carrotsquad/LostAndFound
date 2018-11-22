package com.zhangqianyuan.teamwork.lostandfound.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 消息fragment
 * Created at: 2018/11/13 10:51
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class MessageFragment extends Fragment {

    private View view;

    private RecyclerView recyclerView;

    public static Fragment newInstance(){
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container,false);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.message_recyclerview);
    }
}
