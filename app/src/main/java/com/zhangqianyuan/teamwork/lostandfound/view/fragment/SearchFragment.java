package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.yyydjk.library.DropDownMenu;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ConstellationAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.GirdDropDownAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ListDropDownAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.ButterKnife;





import butterknife.ButterKnife;

/**
 * Description: 搜索界面
 * Created at: 2018/11/10 17:45
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchFragment extends Fragment {

    private EditText searchInput;
    private Button sure;
    private DropDownMenu dropDownMenu;
    private List<View> popupViews = new ArrayList<>();
    private View view;
    private Context context;

    private String headers[] = {"启事类型", "丢失地点", "物品类型"};
    private String diushitypes[] = {"不限","失物","招领"};
    private String places[]={"不限","一教","二教","三教","四教","五教","六教","七教","八教","二维码大楼","信科","逸夫楼","老图","数图","太极运动场","风华运动场","风雨操场"};
    private String thingstypes[] = {"不限","衣物","首饰","运动器材","书本","手机","电脑","有赏金","其他"};

    private int thingsPosition = 0;

    private GirdDropDownAdapter diushitypeAdapter;
    private ListDropDownAdapter placesAdapter;
    private ConstellationAdapter thingsAdapter;
    private ListView diushitypesView;
    private ListView placesView;
    private View thingsView;
    private GridView thingsConstellationView;
    private RecyclerView recyclerView;
    private TextView ok;
    private GridLayoutManager gridLayoutManager;

    private SearchItemAdapter searchItemAdapter;
    private ArrayList<SearchItem> searchItemArrayList = new ArrayList<>();


    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, null);
        context = getActivity();
        SearchItem searchItem1=new SearchItem();
        searchItem1.setTitle("我掉了红色的手表");
        SearchItem searchItem2=new SearchItem();
        searchItem2.setTitle("我掉了我的校卡");
        SearchItem searchItem3=new SearchItem();
        searchItem3.setTitle("我掉了我的打火机");
        SearchItem searchItem4=new SearchItem();
        searchItem4.setTitle("我掉了我的手机");
        SearchItem searchItem5=new SearchItem();
        searchItem5.setTitle("我掉了我的作业本");
        searchItemArrayList.add(searchItem1);
        searchItemArrayList.add(searchItem2);
        searchItemArrayList.add(searchItem3);
        searchItemArrayList.add(searchItem4);
        searchItemArrayList.add(searchItem5);

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
        ButterKnife.bind(view);
        searchInput = view.findViewById(R.id.search_input);
        sure = view.findViewById(R.id.search_sure);
        dropDownMenu = view.findViewById(R.id.search_dropDownMenu);

        //初始化启事类型过滤
        diushitypesView = new ListView(context);
        diushitypeAdapter = new GirdDropDownAdapter(context, Arrays.asList(diushitypes));
        diushitypesView.setDividerHeight(0);
        diushitypesView.setAdapter(diushitypeAdapter);

        //初始化丢失地点过滤
        placesView = new ListView(context);
        placesAdapter = new ListDropDownAdapter(context, Arrays.asList(places));
        placesView.setDividerHeight(0);
        placesView.setAdapter(placesAdapter);

        //初始化物品类型过滤
        thingsView = getLayoutInflater().inflate(R.layout.custom_layout,null);
        thingsConstellationView = ButterKnife.findById(thingsView, R.id.constellation);
        thingsAdapter = new ConstellationAdapter(context, Arrays.asList(thingstypes));
        thingsConstellationView.setAdapter(thingsAdapter);
        ok = ButterKnife.findById(thingsView,R.id.ok);

        //init popupViews
        popupViews.add(diushitypesView);
        popupViews.add(placesView);
        popupViews.add(thingsView);

        //初始化recyclerView
        searchItemAdapter = new SearchItemAdapter(searchItemArrayList);
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        gridLayoutManager = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchItemAdapter);
//        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(context);

        //设置dropDownMenu
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, recyclerView);
        
        diushitypesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                diushitypeAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : diushitypes[position]);
                dropDownMenu.closeMenu();
            }
        });
        
        placesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                placesAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : places[position]);
                dropDownMenu.closeMenu();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其内部已经设置了记录当前tab位置的参数，该参数会随tab被点击而改变，所以这里直接设置tab值即可
                //此处若想获得constellations第一个值“不限”，可修改constellationPosition初始值为-1，且这里代码改为constellationPosition == -1)
                dropDownMenu.setTabText((thingsPosition == 0) ? headers[2] : thingstypes[thingsPosition]);
                dropDownMenu.closeMenu();
//                changeContentView();   //在这里可以请求获得经筛选后要显示的内容
            }
        });

        thingsConstellationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                thingsAdapter.setCheckItem(position);
                thingsPosition = position;
            }
        });

        //点击搜索按键后
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    
}
