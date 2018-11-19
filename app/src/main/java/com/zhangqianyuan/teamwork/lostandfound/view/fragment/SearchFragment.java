package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.yyydjk.library.DropDownMenu;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ConstellationAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.GirdDropDownAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.ListDropDownAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.SearchItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.presenter.ISearchPresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.SearchPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISearchFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.ButterKnife;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;


/**
 * Description: 搜索界面
 * Created at: 2018/11/10 17:45
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchFragment extends Fragment implements ISearchFragment {

    private EditText searchInput;
    private Button sure;
    private DropDownMenu dropDownMenu;
    private List<View> popupViews = new ArrayList<>();
    private View view;
    private Context context;

    private String[] headers = {"启事类型", "丢失地点", "物品类型"};
    private String[] diushitypes = {"不限", "失物", "招领"};
    private List<String> places = new ArrayList<>();
    private List<String> thingstypes = new ArrayList<>();
    private Integer diushiTypePosition = 0;
    private Integer placePosition = -1;
    private Integer thingsTypePosition = -1;

    private Integer thingsPosition = 0;

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
    private ArrayList<DynamicItemBean> searchItemBeanArrayList = new ArrayList<>();

    private ISearchPresenter iSearchPresenter;

    private SharedPreferences sharedPreferences;


    public static Fragment newInstance(){
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container,false);
        context = getContext();
        sharedPreferences = context.getSharedPreferences("users", Context.MODE_PRIVATE);
        iSearchPresenter = new SearchPresenter(SearchFragment.this);
        initView();
        setOnClick();
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

    /**
     * Description: 初始化控件
     */
    private void initView(){
        places.add("不限");
        places.addAll(allPlaceBeanList);
        thingstypes.add("不限");
        thingstypes.addAll(allTypeBeanList);
        
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
        placesAdapter = new ListDropDownAdapter(context, places);
        placesView.setDividerHeight(0);
        placesView.setAdapter(placesAdapter);

        //初始化物品类型过滤
        thingsView = getLayoutInflater().inflate(R.layout.custom_layout,null);
        thingsConstellationView = thingsView.findViewById( R.id.constellation);
        thingsAdapter = new ConstellationAdapter(context, thingstypes);
        thingsConstellationView.setAdapter(thingsAdapter);
        ok = thingsView.findViewById(R.id.ok);

        //init popupViews
        popupViews= Arrays.asList(diushitypesView, placesView, thingsView);

        //初始化recyclerView
        searchItemAdapter = new SearchItemAdapter(searchItemBeanArrayList);
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        gridLayoutManager = new GridLayoutManager(context,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(searchItemAdapter);
//        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(context);

        Log.e("SearchFragment","headers大小:"+Integer.toString(headers.length) + "popupViews大小:"+Integer.toString(popupViews.size()));

        //设置dropDownMenu
        dropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, recyclerView);
        

    }

    /**
     * Description: 设置Listener
     */
    private void setOnClick(){
        diushitypesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                diushitypeAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[0] : diushitypes[position]);
                diushiTypePosition = position;
                dropDownMenu.closeMenu();
            }
        });

        placesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                placesAdapter.setCheckItem(position);
                dropDownMenu.setTabText(position == 0 ? headers[1] : places.get(position));
                placePosition = position;
                dropDownMenu.closeMenu();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //其内部已经设置了记录当前tab位置的参数，该参数会随tab被点击而改变，所以这里直接设置tab值即可
                //此处若想获得constellations第一个值“不限”，可修改constellationPosition初始值为-1，且这里代码改为constellationPosition == -1)
                dropDownMenu.setTabText((thingsPosition == 0) ? headers[2] : thingstypes.get(placePosition));
//                thingstype = thingstypes[thingsPosition];
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
                String keyword = searchInput.getText().toString();
                String session = sharedPreferences.getString(SESSION,"null");
                iSearchPresenter.getSearchResult(keyword, diushiTypePosition-1, placePosition-1, thingsTypePosition-1, session);

            }
        });
    }

    @Override
    public void showSearchResult(Boolean status, List<DynamicItemBean> arrayList) {
        if(status){
            searchItemBeanArrayList.clear();
            searchItemBeanArrayList.addAll(arrayList);
            searchItemAdapter.notifyDataSetChanged();
//            searchItemAdapter.notifyItemChanged(this.searchItemBeanArrayList.size()-1);
//            recyclerView.scrollToPosition(msgList.size() - 1);
        }else {
            FancyToast.makeText(context,"出现了问题",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}
