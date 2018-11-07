package com.zhangqianyuan.teamwork.lostandfound.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.DynamicItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 动态Fragment
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/8  实现 动态加载数据  下拉刷新 上拉加载更多
public class DynamicFragment extends Fragment {
    RecyclerView mRecyclerView;
    DynamicItemAdapter mDynamicItemAdapter;
    List<DynamicItem> lists = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        mRecyclerView = view.findViewById(R.id.dynamic_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mDynamicItemAdapter = new DynamicItemAdapter(lists);
        mRecyclerView.setAdapter(mDynamicItemAdapter);
        return view;
    }
}
