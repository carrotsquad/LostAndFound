package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.transition.Transition;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.zhangqianyuan.teamwork.lostandfound.R;

import com.zhangqianyuan.teamwork.lostandfound.adapter.TabLayoutViewPagerAdapter;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description 动态界面
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/8  实现 动态加载数据  下拉刷新 上拉加载更多
// TODO: 2018/11/9  实现bottomnavegation 点击 和ViewPager滑动  在子fragment中加载recycleview并设置颜色
    /*
     最开始时 默认预加载16条动态信息，动态中的count数目按照时间顺序 0到15排序
     上拉 或者 下拉时  count数目清零 再次向服务器请求16条数据.......
     */
public class DynamicFragment extends Fragment {

    @BindView(R.id.dynamic_fragment_tablayout)
    TabLayout  tab;

    @BindView(R.id.dynamic_viewpager)
    ViewPager mViewPager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        //bind（）参数为空间所在的layout
        ButterKnife.bind(this,view);
        linkBottomWithViewpager();
        return view;
    }

    public void linkBottomWithViewpager(){
        /*如果把这两个list写在外面，当跳转的Fragment过多时 这个fragment被销毁 ，但是数据任然保存，再次进入这个fragment会再次执行onCreateView方法
          导致这两个list的长度无限增加
          */
        List<Fragment> mFragments = new ArrayList<>();
        List<String>  title     = new ArrayList<>();
        mFragments.add(DynamicChildFragment.newInstance());
        mFragments.add(DynamicChildFragment.newInstance());
        title.add("失物");
        title.add("招领");
        FragmentManager man = getChildFragmentManager();
        TabLayoutViewPagerAdapter adapter = new TabLayoutViewPagerAdapter(man,mFragments,title);
        mViewPager.setAdapter(adapter);
        tab.setupWithViewPager(mViewPager);
        tab.setTabsFromPagerAdapter(adapter);
}
    public static DynamicFragment newInstance(){
        DynamicFragment fragment = new DynamicFragment();
        return fragment;
    }

}
