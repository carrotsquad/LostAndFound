package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.utils.popupwindow.ArrowPopWindows;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IPopupEvent;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeImgsList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.getLostThingsPhoto;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.getTypeLittlePhoto;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.getUserPhoto;
import static com.zhangqianyuan.teamwork.lostandfound.utils.popupwindow.ArrowPopWindows.SHOW_TOP;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDESC;
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
// TODO: 2018/11/25 完善删除item
public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private ArrayList<DynamicItemBean> searchItemBeanArrayList;
    private Context mContext;
    private Boolean isMessage;
    private List<Integer> changeNumList;
    private ArrowPopWindows arrowPopWindows;
    private Activity activity;
    private IPopupEvent popupEvent;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setPopupEvent(IPopupEvent popupEvent) {
        this.popupEvent = popupEvent;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        ImageView headimg;
        TextView neckname;
        TextView fabiaotime;
        TextView title;
        ImageView thingType;
        TextView placeanddate;
        ImageView qishileixing;
        ImageView isNeedBounty;
        CircleImageView userphoto;
        TextView newMsg;

        public ViewHolder(View view) {
            super(view);
            relativeLayout = (RelativeLayout) view;
            headimg = view.findViewById(R.id.search_item_thingsphoto);
            neckname = view.findViewById(R.id.search_item_userNickName);
            isNeedBounty = view.findViewById(R.id.search_item_isNeedBounty);
            userphoto = view.findViewById(R.id.search_item_userphoto);
            fabiaotime = view.findViewById(R.id.search_item_fabiaodate);
            title = view.findViewById(R.id.search_item_title);
            placeanddate = view.findViewById(R.id.search_item_placeanddate);
            qishileixing = view.findViewById(R.id.search_item_qishileixing);
            thingType = view.findViewById(R.id.search_item_thingstype);
            newMsg = view.findViewById(R.id.search_item_newmessage);
        }
    }

    public SearchItemAdapter(ArrayList<DynamicItemBean> searchItemBeanArrayList, List<Integer> changeNumList, Activity activity, IPopupEvent popupEvent, Boolean isMessage){
        this.searchItemBeanArrayList = searchItemBeanArrayList;
        this.isMessage = isMessage;
        this.activity = activity;
        this.changeNumList = changeNumList;
        this.popupEvent = popupEvent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        if(isMessage) {
            holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    arrowPopWindows.show(view, SHOW_TOP);
                    return true;
                }
            });


        }


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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

                Intent intent = new Intent(mContext, ThingDetailActivity.class);
                intent.putExtra(OTHERSNICKNAME,dynamicItemBean.getNickname());
                intent.putExtra(OTHERSPHOTO,dynamicItemBean.getUserphoto());
                intent.putExtra(OTHERSFABIAODATE,fabaiodate);
                intent.putExtra(OTHERSDIUSHILEIXING, losttype);
                intent.putExtra(OTHERSDIUSHIDATE, lostdate);
                intent.putExtra(OTHERSPLACE,place);
                intent.putExtra(OTHERSIMGS, dynamicItemBean.getThelost().getPhoto());
                intent.putExtra(OTHERSTHINGSTYPE, dynamicItemBean.getThelost().getTypeid());
                intent.putExtra(OTHERSID, dynamicItemBean.getThelost().getId());
                intent.putExtra(OTHERSDESC,dynamicItemBean.getThelost().getDescription());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DynamicItemBean dynamicItemBean = searchItemBeanArrayList.get(position);
        Integer changenum = 0;
        if(isMessage) {
             changenum = changeNumList.get(position);
        }
        /**
         * "publishtime": "20181105173056",
         * "losttime": "2018110512",
         */
        String date_orig = dynamicItemBean.getThelost().getPublishtime();
        String fabaiodate = "";
        if(!"0".equals(date_orig.substring(4, 5))){
            fabaiodate = fabaiodate+"0";
        }
        fabaiodate = date_orig.substring(5, 6) + "月";
        if(!"0".equals(date_orig.substring(6, 7))) {
            fabaiodate = fabaiodate+date_orig.substring(6, 7);
        }
        fabaiodate = fabaiodate + date_orig.substring(7,8)+"日"+date_orig.substring(8,10)+":"+date_orig.substring(10,12);
        holder.fabiaotime.setText(fabaiodate+"发表");

        String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
        String lostdate = lostdate_orig.substring(0, 4) + "." + lostdate_orig.substring(4, 6) + ".";
        if(!"0".equals(lostdate_orig.substring(6, 7))){
            lostdate = lostdate + lostdate_orig.substring(6, 7);
        }
        lostdate = lostdate + lostdate_orig.substring(7,8)+"";

        int lostplace = dynamicItemBean.getThelost().getPlaceid();
        int losttype = dynamicItemBean.getThelost().getLosttype();
        int thingstype = dynamicItemBean.getThelost().getTypeid();

        String place = allPlaceBeanList.get(lostplace);
        String thingsType = allTypeBeanList.get(thingstype);

        int lostType = 0;
        switch (losttype){
            case 0:{
                lostType = R.drawable.littleicon_type_lost;
                break;
            }
            case 1:{
                lostType = R.drawable.littleicon_type_find;
            }
            default:{
                break;
            }
        }
        //启事类型
        holder.qishileixing.setImageResource(lostType);
        //时间地点
        holder.placeanddate.setText(place+"   "+lostdate);
        //标题
        holder.title.setText(dynamicItemBean.getThelost().getTitle());
        holder.neckname.setText(dynamicItemBean.getNickname());

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
        Log.e("ThingsType",allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1));

        //类型小标签
        Glide.with(mContext)
                .load(getTypeLittlePhoto(sharedPreferences.getString(SESSION,"null"),allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1)))
                .asBitmap()
                .into(holder.thingType);

        //事件图片
        Glide.with(mContext)
                .load(getLostThingsPhoto(sharedPreferences.getString(SESSION,"null"),dynamicItemBean.getThelost().getPhoto()))
                .asBitmap()
                .into(holder.headimg);

        //用户头像
        Glide.with(mContext)
                .load(getUserPhoto(sharedPreferences.getString(SESSION,"null"),dynamicItemBean.getUserphoto()))
                .asBitmap()
                .into(holder.userphoto);

        //赏金
        holder.isNeedBounty.setVisibility(View.INVISIBLE);

        //新消息气泡
        if(isMessage) {
            if(changenum!=0) {
                holder.newMsg.setText("+"+changenum.toString());
            }else {
                holder.newMsg.setVisibility(View.INVISIBLE);
            }
        }

        //是否是消息
        if(isMessage){
            //popupwindow
            arrowPopWindows = new ArrowPopWindows(activity, R.layout.message_popwindow, new ArrowPopWindows.OnViewCreateListener() {
                @Override
                public void onViewCreate(ViewGroup viewGroup) {
                    //设置点击的回调
                    viewGroup.getChildAt(0).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //在主程序回调
                            arrowPopWindows.dismiss();
                            popupEvent.onDelete(holder.getAdapterPosition());
                        }
                    });
                }
            });

        }else {
            holder.newMsg.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return searchItemBeanArrayList.size();
    }


}
