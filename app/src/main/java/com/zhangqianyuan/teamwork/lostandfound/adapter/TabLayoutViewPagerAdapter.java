package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



import java.util.List;

/**
 * Description 因为用bottomNavigation不好看
 * 所以使用Tablayout 的dynamic切换栏
 * @author zhoudada
 * @version $Rev$
 */
public class TabLayoutViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentLists ;
    private List<String> tabTitle  ;

    public TabLayoutViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,
                                   List<String> tab ){
        super(fm);
        this.fragmentLists = fragmentList;
        this.tabTitle = tab;

    }



    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return  fragmentLists.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle.get(position);
    }
}