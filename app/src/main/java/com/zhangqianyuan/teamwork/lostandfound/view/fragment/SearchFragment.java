package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.yyydjk.library.DropDownMenu;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ConstellationAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.GirdDropDownAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ListDropDownAdapter;

public class SearchFragment extends Fragment{

    private EditText searchInput;
    private Button sure;
    private DropDownMenu dropDownMenu;

    private View view;
    private Context context;

    private String headers[] = {"丢失类型", "丢失地点", "物品类型"};
    private String diushitypes[] = {"失物","招领"};
    private String places[]={"一教","二教","三教","四教","五教","六教","七教","八教","二维码大楼","信科","逸夫楼","老图","数图","太极运动场","风华运动场","风雨操场"};
    private String thingstypes[] = {"衣物","首饰","运动器材","书本","手机","电脑","有赏金","其他"};

    private int constellationPosition = 0;

    private GirdDropDownAdapter diushitypeAdapter;
    private ListDropDownAdapter placesAdapter;
    private ConstellationAdapter thingsAdapter;
    private ListView diushitypesView;
    private ListView placesView;
    private View thingsView;
    private GridView constellation;

    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getContext();

        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView(){
        searchInput = view.findViewById(R.id.search_input);
        sure = view.findViewById(R.id.search_sure);
        dropDownMenu = view.findViewById(R.id.search_dropDownMenu);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initTheatreMenu(){

    }



}
