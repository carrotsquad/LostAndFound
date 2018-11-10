package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchItem;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private ArrayList<SearchItem> searchItemArrayList;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        CircleImageView headimg;
        TextView neckname;
        TextView time;
        TextView title;
        TextView thingType;
        TextView place;
        TextView qishileixing;

        public ViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.search_item_cardview);
            headimg = view.findViewById(R.id.search_item_photo);
//            neckname = view.findViewById(R.id.dynamic_card_neckname);
            time = view.findViewById(R.id.search_item_date);
            title = view.findViewById(R.id.search_item_title);
            place = view.findViewById(R.id.search_item_place);
            qishileixing = view.findViewById(R.id.search_item_qishileixing);
            thingType = view.findViewById(R.id.dynamic_card_thingtype);
        }
    }

    public SearchItemAdapter(ArrayList<SearchItem> searchItemArrayList){
        this.searchItemArrayList = searchItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item,  parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchItem searchItem = searchItemArrayList.get(position);
//        holder.headimg
        holder.qishileixing.setText(searchItem.getQishileixing());
        holder.place.setText(searchItem.getPlace());
        holder.time.setText(searchItem.getDate());
        holder.title.setText(searchItem.getTitle());
        Glide.with(mContext)
                .load(searchItem.getPhoto())
                .asBitmap()
                .into(holder.headimg);
    }

    @Override
    public int getItemCount() {
        return searchItemArrayList.size();
    }


}
