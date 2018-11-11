package com.zhangqianyuan.teamwork.lostandfound.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.DynamicItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * 动态板块子页面（分为失物/招领）
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DynamicChildFragment extends Fragment {
    RecyclerView mRecyclerView;
    DynamicItemAdapter mDynamicItemAdapter;
    List<DynamicItemBean> lists = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_dynamic_lostorfind,container,false);
        mRecyclerView = view.findViewById(R.id.dynamic_list);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        initLists();
        mRecyclerView.setLayoutManager(manager);
        mDynamicItemAdapter = new DynamicItemAdapter(lists);
        mRecyclerView.setAdapter(mDynamicItemAdapter);
       return view;
    }

    public void  initLists(){
            for (int i = 0; i <6 ; i++) {
                DynamicItemBean da = new DynamicItemBean(R.drawable.gril6,"周大大","walete",
                        "10.10","home","我在马路边丢了很多钱",1);
                lists.add(da);
            }
    }



    public static DynamicChildFragment newInstance(){
       DynamicChildFragment dynamicChildFragment = new DynamicChildFragment();
       return dynamicChildFragment;
    }
}

