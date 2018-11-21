package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.util.List;

public class NetworkImageIndicatorView extends ImageIndicatorView {

    private Context context;
    public NetworkImageIndicatorView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void setupLayoutByImageUrl(List<String> urlList) {
        for(String url: urlList) {
            ImageView imageView = new ImageView(getContext());
            //load image from url and set to imageView, you can use UIL or Volley to do this work
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .into(imageView);
            addViewItem(imageView);
        }
    }
}
