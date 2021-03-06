package com.yf107.teamwork.lostandfound.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.TheLostBean;
import com.yf107.teamwork.lostandfound.utils.SelectTypeUtil;
import com.yf107.teamwork.lostandfound.utils.SelectTypeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHistoryAdapter  extends RecyclerView.Adapter<MyHistoryAdapter.ViewHolder>  {
    private List<TheLostBean> lists ;
    private Context mContext;
    private String url;


    public static class ViewHolder extends  RecyclerView.ViewHolder {
        @BindView(R.id.myhistory_thingtype)
        ImageView  thingtype;

        @BindView(R.id.myhistory_thingtype_txt)
        TextView  thingtypetxt;

        @BindView(R.id.myhistory_eventtype)
        TextView  eventtype;

        @BindView(R.id.myhistory_time)
        TextView  time  ;                     //月 日

//        @BindView(R.id.myhistory_time2)
//        TextView  time2;                     //19:00

        @BindView(R.id.myhistory_where)
        TextView  where;

        @BindView(R.id.isdone_description)
        TextView   isdong;

        public ViewHolder(View  view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public MyHistoryAdapter (List<TheLostBean> lists){
      this.lists=lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userinfo_myhistory_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      //  String s = AllURI.getLostThingsPhoto(lists.get(position).getId());

        if(lists.get(position).getPhoto().equals("default.jpg")){
            holder.thingtype.setImageResource(R.mipmap.diai1);
        }else {
            Glide.with(mContext)
                    .load(lists.get(position).getPhoto())
                    .asBitmap()
                    .into(holder.thingtype);
        }
        Log.d("zhaopian",lists.get(position).getPhoto());

        //类型图片
        holder.thingtypetxt.setText(" "+ SelectTypeUtils.getInstance().getImage(lists.get(position).getTypeid())+" ");


        if (lists.get(position).getIshandled()==1){
            holder.isdong.setText("递爱成功");
        }else if (lists.get(position).getIshandled()==0){
            holder.isdong.setText("递爱失败");
        }
        if (lists.get(position).getLosttype()==0){
            holder.eventtype.setBackgroundResource(R.drawable.shape_thingstype_lost);
            holder.eventtype.setText(" "+"丢"+" ");
        }else if (lists.get(position).getLosttype()==1){
            holder.eventtype.setBackgroundResource(R.drawable.shape_thingstype_find);
            holder.eventtype.setText(" "+"拾"+" ");
        }
        String lostPlace = AllURI.allPlaceBeanList.get(lists.get(position).getPlaceid()-1);
        holder.where.setText(lostPlace);
        String publishTime= lists.get(position).getPublishtime();
        if (!publishTime.equals("")) {
            String year = publishTime.substring(0, 4);
            String mouth = publishTime.substring(4, 6);
            String day = publishTime.substring(6, 8);
            String hours = publishTime.substring(8, 10);
            String time1 = year + "." + mouth + "." + "" + day;
            String time2 = hours + "点";
            holder.time.setText(time1);
//            holder.time2.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }



}
