package com.yf107.teamwork.lostandfound.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.CommentFeedBack;
import com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ThingDetailAdapter extends RecyclerView.Adapter<ThingDetailAdapter.ViewHolder> {
    private List<CommentFeedBack.Comments>  bean;
    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private ThingDetailActivity mThingDetailActivity;

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.comment_item_photo)
        CircleImageView head;

        @BindView(R.id.comment_item_nickname)
        TextView nick;

        @BindView(R.id.comment_item_date)
        TextView date;

        @BindView(R.id.comment_item_content)
        TextView  content;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public ThingDetailAdapter(List<CommentFeedBack.Comments> list, ThingDetailActivity activity){
        this.bean = list;
        mThingDetailActivity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThingDetailActivity.popup();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mSharedPreferences = mContext.getSharedPreferences("users",Context.MODE_PRIVATE);
        String s = AllURI.getUserPhoto(mSharedPreferences.getString("SESSION",null),bean.get(position).getPhoto());
        Glide.with(mContext)
                .load(s)
                .asBitmap()
                .into(holder.head);
        holder.nick.setText(bean.get(position).getNickname());
        String publishTime= bean.get(position).getComment().getTime();
        if (!publishTime.equals("")){
            String   mouth = publishTime.substring(4,6);
            String    day = publishTime.substring(6,8);
            String    hours=publishTime.substring(8,10);
            String fen = publishTime.substring(10,12);
            String time1 = mouth+"月"+""+day+"日"+" "+hours+":"+fen;
            holder.date.setText(time1);
        }
        holder.content.setText(bean.get(position).getComment().getContent());
    }

    @Override
    public int getItemCount() {
        return bean.size();
    }
}
