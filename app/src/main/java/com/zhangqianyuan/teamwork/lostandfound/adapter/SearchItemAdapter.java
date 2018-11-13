package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItemBean;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHIDATE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHILEIXING;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSFABIAODATE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSID;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSIMG;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSTHINGSTYPES;

/**
 * Description: 搜索fragment的recyclerview适配器
 * Created at: 2018/11/10 20:05
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private ArrayList<SearchItemBean> searchItemBeanArrayList;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        CircleImageView headimg;
        TextView neckname;
        TextView fabiaotime;
        TextView title;
        TextView thingType;
        TextView placeanddate;
        TextView qishileixing;

        public ViewHolder(View view) {
            super(view);
            mCardView = (CardView) view;
            headimg = view.findViewById(R.id.search_item_photo);
//            neckname = view.findViewById(R.id.dynamic_card_neckname);
            fabiaotime = view.findViewById(R.id.search_item_fabiaodate);
            title = view.findViewById(R.id.search_item_title);
            placeanddate = view.findViewById(R.id.search_item_placeanddate);
            qishileixing = view.findViewById(R.id.search_item_qishileixing);
            thingType = view.findViewById(R.id.dynamic_card_thingtype);
        }
    }

    public SearchItemAdapter(ArrayList<SearchItemBean> searchItemBeanArrayList){
        this.searchItemBeanArrayList = searchItemBeanArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SearchItemBean searchItemBean = searchItemBeanArrayList.get(position);
                Intent intent = new Intent(mContext, ThingDetailActivity.class);
                intent.putExtra(OTHERSIMG, searchItemBean.getPhoto());
                intent.putExtra(OTHERSFABIAODATE,searchItemBean.getFabiaodate());
                intent.putExtra(OTHERSDIUSHILEIXING, searchItemBean.getQishileixing());
                intent.putExtra(OTHERSDIUSHIDATE, searchItemBean.getDiushidate());
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(OTHERSTHINGSTYPES, (ArrayList<String>) searchItemBean.getTypes());
                intent.putExtra(OTHERSTHINGSTYPES, bundle);
                intent.putExtra(OTHERSID, searchItemBean.getID());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchItemBean searchItemBean = searchItemBeanArrayList.get(position);
//        holder.headimg
        holder.qishileixing.setText("启事类型:"+ searchItemBean.getQishileixing());
        holder.fabiaotime.setText("发表地点:"+ searchItemBean.getFabiaodate());
//        holder.headimg
        holder.placeanddate.setText("丢失时间:"+ searchItemBean.getDiushidate()+" 丢失地点:"+searchItemBean.getDiushidate());
        holder.title.setText(searchItemBean.getTitle());
//        Glide.with(mContext)
//                .load(searchItemBean.getPhoto())
//                .asBitmap()
//                .into(holder.headimg);
    }

    @Override
    public int getItemCount() {
        return searchItemBeanArrayList.size();
    }


}
