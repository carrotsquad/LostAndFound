package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DynamicItemAdapter extends RecyclerView.Adapter<DynamicItemAdapter.ViewHolder> {
   private Context mContext;
   private List<DynamicItemBean> lists;


    public static class ViewHolder extends RecyclerView.ViewHolder{
       CardView mCardView;
       CircleImageView headimg;
       TextView        neckname;
       TextView        time;
       TextView        description;
       TextView        thingType;

       public ViewHolder(View view){
           super(view);
           mCardView = view.findViewById(R.id.dynamic_card);
           headimg = view.findViewById(R.id.dynamic_card_headimg);
           neckname = view.findViewById(R.id.dynamic_card_neckname);
           time  = view.findViewById(R.id.dynamic_card_time);
           description = view.findViewById(R.id.dynamic_card_description);
           thingType = view.findViewById(R.id.dynamic_card_thingtype);
       }
   }
    public DynamicItemAdapter(List<DynamicItemBean> list){
        this.lists = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_dynamic_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DynamicItemBean dynamicItemBean = lists.get(position);
        if (dynamicItemBean.getCardType()==DynamicItemBean.TYPE_LOST){
        holder.mCardView.setBackgroundColor(Color.parseColor("FFE086A9"));
    }
       if (dynamicItemBean.getCardType()==DynamicItemBean.TYPE_FIND){
            holder.mCardView.setBackgroundColor(Color.parseColor("#ab47bc"));
       }
        holder.headimg.setImageResource(dynamicItemBean.getHeadImg());
        holder.neckname.setText(dynamicItemBean.getNeckName());
        holder.time.setText(dynamicItemBean.getTime());
        holder.description.setText(dynamicItemBean.getDescription());
        holder.thingType.setText(dynamicItemBean.getThingType());
    }

    @Override
    public int getItemCount() {
        return  lists.size();
    }

}
