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

public class MessageFragment extends Fragment {

    private View view;

    @BindView(R.id.message_recyclerview)
    RecyclerView recyclerView;

    public static Fragment newInstance(){
        MessageFragment messageFragment = new MessageFragment();
        return messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container,false);
        ButterKnife.bind(this,view);
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

    private void initView(){}
}
