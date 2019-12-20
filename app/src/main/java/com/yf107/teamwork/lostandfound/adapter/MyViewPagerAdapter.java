package com.yf107.teamwork.lostandfound.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhangqianyuan.teamwork.lostandfound.R;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViewList;
    public MyViewPagerAdapter(List<ImageView> imageViewList) {
        this.imageViewList=imageViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //计算新的位置取余
        int newPosition=position%imageViewList.size();
        //1.将图片取出来
        ImageView imageView=imageViewList.get(newPosition);
        //2.将图片加到ViewPager容器里去
        container.addView(imageView);
        //3.返回图片
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));
    }
}
