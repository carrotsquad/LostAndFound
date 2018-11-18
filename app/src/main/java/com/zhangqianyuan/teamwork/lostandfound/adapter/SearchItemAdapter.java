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

import com.bumptech.glide.Glide;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHIDATE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHILEIXING;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSFABIAODATE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSID;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSIMGS;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSNICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSPHOTO;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSPLACE;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSTHINGSTYPE;

/**
 * Description: 搜索fragment的recyclerview适配器
 * Created at: 2018/11/10 20:05
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private ArrayList<DynamicItemBean> searchItemBeanArrayList;
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

    public SearchItemAdapter(ArrayList<DynamicItemBean> searchItemBeanArrayList){
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
                DynamicItemBean dynamicItemBean = searchItemBeanArrayList.get(position);

                String date_orig = dynamicItemBean.getThelost().getPublishtime();
                String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
                if(!"0".equals(date_orig.substring(6, 7))) {
                    fabaiodate = fabaiodate+date_orig.substring(6, 7);
                }
                fabaiodate = fabaiodate + date_orig.substring(7,8)+"日"+date_orig.substring(8,10)+":"+date_orig.substring(10,12);

                String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
                String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
                if(!"0".equals(lostdate_orig.substring(6, 7))){
                    lostdate = lostdate + lostdate_orig.substring(6, 7);
                }
                lostdate = lostdate + lostdate_orig.substring(7,8)+"日";

                int lostplace = dynamicItemBean.getThelost().getPlaceid();
                int losttype = dynamicItemBean.getThelost().getLosttype();
                int thingstype = dynamicItemBean.getThelost().getTypeid();

                String place = allPlaceBeanList.get(lostplace);
                String thingsType = allTypeBeanList.get(thingstype);
                String lostType="";

                switch (losttype){
                    case 0:{
                        lostType = "寻物启示";
                        break;
                    }
                    case 1:{
                        lostType = "招领启示";
                    }
                    default:{
                        break;
                    }
                }

                Intent intent = new Intent(mContext, ThingDetailActivity.class);
                intent.putExtra(OTHERSNICKNAME,dynamicItemBean.getNickname());
                intent.putExtra(OTHERSPHOTO,dynamicItemBean.getUserphoto());
                intent.putExtra(OTHERSFABIAODATE,fabaiodate);
                intent.putExtra(OTHERSDIUSHILEIXING, lostType);
                intent.putExtra(OTHERSDIUSHIDATE, lostdate);
                intent.putExtra(OTHERSPLACE,place);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(OTHERSIMGS, (ArrayList<String>) dynamicItemBean.getThelost().getPhoto());
                intent.putExtra(OTHERSIMGS, bundle);
                intent.putExtra(OTHERSTHINGSTYPE, dynamicItemBean.getThelost().getTypeid());
                intent.putExtra(OTHERSID, dynamicItemBean.getThelost().getId());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DynamicItemBean dynamicItemBean = searchItemBeanArrayList.get(position);
        /**
         * "publishtime": "20181105173056",
         * "losttime": "2018110512",
         */
        holder.qishileixing.setText("启事类型:"+ dynamicItemBean.getThelost().getLosttype());
        String date_orig = dynamicItemBean.getThelost().getPublishtime();
        String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
        if(!"0".equals(date_orig.substring(6, 7))) {
            fabaiodate = fabaiodate+date_orig.substring(6, 7);
        }
        fabaiodate = fabaiodate + date_orig.substring(7,8)+"日"+date_orig.substring(8,10)+":"+date_orig.substring(10,12);
        holder.fabiaotime.setText("发表时间:"+ fabaiodate);

        String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
        String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
        if(!"0".equals(lostdate_orig.substring(6, 7))){
            lostdate = lostdate + lostdate_orig.substring(6, 7);
        }
        lostdate = lostdate + lostdate_orig.substring(7,8)+"日";

        int lostplace = dynamicItemBean.getThelost().getPlaceid();
        int losttype = dynamicItemBean.getThelost().getLosttype();
        int thingstype = dynamicItemBean.getThelost().getTypeid();

        String place = allPlaceBeanList.get(lostplace);
        String thingsType = allTypeBeanList.get(thingstype);
        String lostType="";
        switch (losttype){
            case 0:{
                lostType = "寻物启示";
                break;
            }
            case 1:{
                lostType = "招领启示";
            }
            default:{
                break;
            }
        }

        holder.placeanddate.setText("丢失时间:"+ lostdate+" 丢失地点:"+place);
        holder.title.setText(dynamicItemBean.getThelost().getTitle());
        Glide.with(mContext)
                .load(dynamicItemBean.getThelost().getPhoto())
                .asBitmap()
                .into(holder.headimg);
    }

    @Override
    public int getItemCount() {
        return searchItemBeanArrayList.size();
    }


}
