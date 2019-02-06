package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.zhangqianyuan.teamwork.lostandfound.R;

import com.zhangqianyuan.teamwork.lostandfound.adapter.TabLayoutViewPagerAdapter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description 动态界面
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Zhangqianyuan$
 * @updateDes
 */
    /*
     最开始时 默认预加载16条动态信息，动态中的count数目按照时间顺序 0到15排序
     上拉 或者 下拉时  count数目清零 再次向服务器请求16条数据.......
     */
@SuppressLint("ValidFragment")
public class DynamicFragment extends Fragment {

    private TabLayout  tab;

    private ViewPager mViewPager;

    private String session="";

    @SuppressLint("ValidFragment")
    public DynamicFragment(String session){
        this.session = session;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_dynamic,container,false);
        //bind（）参数为空间所在的layout
        mViewPager = (ViewPager)view.findViewById(R.id.dynamic_viewpager);
        tab = (TabLayout)view.findViewById(R.id.dynamic_fragment_tablayout);
        ActivityManager.getActivityManager().addF(this);
        if (savedInstanceState==null){
            linkBottomWithViewpager();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void linkBottomWithViewpager(){
        /*如果把这两个list写在外面，当跳转的Fragment过多时 这个fragment被销毁 ，但是数据任然保存，再次进入这个fragment会再次执行onCreateView方法
          导致这两个list的长度无限增加
          */
        List<Fragment> mFragments = new ArrayList<>();
        List<String>  title     = new ArrayList<>();
        mFragments.add(new DynamicChildFragment(0,session));
        mFragments.add(new DynamicChildFragment(1,session));
        title.add("失物");
        title.add("招领");
        FragmentManager man = getChildFragmentManager();
        TabLayoutViewPagerAdapter adapter = new TabLayoutViewPagerAdapter(man,mFragments,title);
        mViewPager.setAdapter(adapter);
        tab.setupWithViewPager(mViewPager);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
