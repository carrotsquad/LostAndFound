package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.TabLayoutViewPagerAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.TabLayoutViewPagerAdapterNew;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.presenter.DynamicChildPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * Description
 * 动态板块子页面（分为失物/招领）
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor Zhangqianyuan
 * @updateDes 交错layout
 */

@SuppressLint("ValidFragment")
public class DynamicChildFragment extends Fragment {

    private int pos;

    private String session = "";
    private DynamicChildPresenter iDynamicChildPresenter;
    private TabLayout tab;
    private ViewPager mViewPager;

    List<Fragment> mFragments = new ArrayList<>();
    List<String>  title     = new ArrayList<>();

    @SuppressLint("ValidFragment")
    public DynamicChildFragment(int i, String session) {
        pos = i;
        this.session = session;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dynamic_child, container, false);
        mViewPager = (ViewPager)view.findViewById(R.id.dynamic_child_viewpager);
        tab = (TabLayout)view.findViewById(R.id.dynamic_child_fragment_tablayout);
        ActivityManager.getActivityManager().addF(this);
        if (savedInstanceState==null){
            initLists();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void initLists() {
        switch (pos) {
            case 0: {
                Log.e("Lost","Lost");
                mFragments.add(new DynamicChildChildFragment(2, session));
                mFragments.add(new DynamicChildChildFragment(3, session));
                mFragments.add(new DynamicChildChildFragment(4, session));
                title.add("今天");
                title.add("昨天");
                title.add("更早");
                FragmentManager man = getChildFragmentManager();
                TabLayoutViewPagerAdapter adapter = new TabLayoutViewPagerAdapter(man, mFragments, title);
                TabLayoutViewPagerAdapterNew adapterNew = new TabLayoutViewPagerAdapterNew(man,mFragments);
                mViewPager.setAdapter(adapterNew);
                tab.setupWithViewPager(mViewPager);
                tab.getTabAt(0).setCustomView(getTabView());
                tab.getTabAt(1).setCustomView(getTabView1());
                tab.getTabAt(2).setCustomView(getTabView2());
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
                break;
            }
            case 1: {
                Log.e("Find","Find");
                mFragments.add(new DynamicChildChildFragment(5, session));
                mFragments.add(new DynamicChildChildFragment(6, session));
                mFragments.add(new DynamicChildChildFragment(7, session));
                title.add("今天");
                title.add("昨天");
                title.add("更早");
                FragmentManager man = getChildFragmentManager();
                TabLayoutViewPagerAdapterNew adapterNew = new TabLayoutViewPagerAdapterNew(man,mFragments);
                mViewPager.setAdapter(adapterNew);
                tab.setupWithViewPager(mViewPager);
                tab.setupWithViewPager(mViewPager);
                tab.getTabAt(0).setCustomView(getTabView());
                tab.getTabAt(1).setCustomView(getTabView1());
                tab.getTabAt(2).setCustomView(getTabView2());
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
                break;
            }
            default: {
                break;
            }

        }
    }

    public View getTabView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tabiconstart,null);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.tabselect1);
        return view;
    }

    public View getTabView1(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tabicon,null);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.tabselect2);
        return view;
    }

    public View getTabView2(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tabiconsend,null);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.tabselect3);
        return view;
    }
}

