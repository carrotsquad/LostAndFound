package com.yf107.teamwork.lostandfound.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.presenter.MessagePresenter;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;
import com.yf107.teamwork.lostandfound.image.GetImageFromWeb;
import com.yf107.teamwork.lostandfound.popupwindow.ArrowPopWindows;
import com.yf107.teamwork.lostandfound.utils.SelectTypeUtil;
import com.yf107.teamwork.lostandfound.utils.SelectTypeUtils;
import com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.yf107.teamwork.lostandfound.popupwindow.ArrowPopWindows.SHOW_TOP;
import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDESC;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHIDATE;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSDIUSHILEIXING;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSFABIAODATE;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSID;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSIMGS;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSNICKNAME;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSPHOTO;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSPLACE;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSTHINGSTYPE;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERSTITLE;
import static com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity.OTHERUSERNAME;

public class MyMessageAdapter extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

    private ArrayList<UpDateMessageBean.DynamicsBeanX> searchItemBeanArrayList;
    private Context mContext;
    private Boolean isMessage;
    private List<Integer> changeNumList;
    private Activity activity;
    private MessagePresenter messagePresenter;
    Handler handler;

    SharedPreferences sharedPreferences;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void attachPresenter(MessagePresenter messagePresenter){
        this.messagePresenter = messagePresenter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        SwipeMenuLayout swipeMenuLayout;
        RelativeLayout relativeLayout;
        ArrowPopWindows arrowPopWindows;
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
        Button btnDlt;

        public ViewHolder(View view) {
            super(view);
            swipeMenuLayout = (SwipeMenuLayout) view;
            relativeLayout= view.findViewById(R.id.search_item_relativeview);
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
          //  btnDlt=view.findViewById(R.id.search_item_delete);
        }
    }

    public MyMessageAdapter(ArrayList<UpDateMessageBean.DynamicsBeanX> searchItemBeanArrayList, List<Integer> changeNumList, Activity activity,Boolean isMessage){
        this.searchItemBeanArrayList = searchItemBeanArrayList;
        this.isMessage = isMessage;
        this.activity = activity;
        this.changeNumList = changeNumList;
    }

    @NonNull
    @Override
    public SearchItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.search_item,parent,false);
        final SearchItemAdapter.ViewHolder holder = new SearchItemAdapter.ViewHolder(view);

        if(isMessage) {
            holder.relativeLayout.setOnLongClickListener(v->{
                        holder.arrowPopWindows.show(view, SHOW_TOP);
                        return true;
                    }
            );
        }else {
            holder.swipeMenuLayout.setSwipeEnable(false);
        }

        holder.relativeLayout.setOnClickListener(v->{
            int position = holder.getAdapterPosition();
            UpDateMessageBean.DynamicsBeanX dynamicItemBean = searchItemBeanArrayList.get(position);


            String date_orig = dynamicItemBean.getDynamics().getThelost().getPublishtime();
            String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
            if(!"0".equals(date_orig.substring(6, 7))) {
                fabaiodate = fabaiodate+date_orig.substring(6, 7);
            }
            fabaiodate = fabaiodate + date_orig.substring(7,8)+"日"+date_orig.substring(8,10)+":"+date_orig.substring(10,12);

            String lostdate_orig = dynamicItemBean.getDynamics().getThelost().getLosttime();
            String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
            if(!"0".equals(lostdate_orig.substring(6, 7))){
                lostdate = lostdate + lostdate_orig.substring(6, 7);
            }
            lostdate = lostdate + lostdate_orig.substring(7,8)+"日";

            int lostplace = dynamicItemBean.getDynamics().getThelost().getPlaceid();
            int losttype = dynamicItemBean.getDynamics().getThelost().getLosttype();
            int thingstype = dynamicItemBean.getDynamics().getThelost().getTypeid();

            String place = AllURI.allPlaceBeanList.get(lostplace-1);
//                String thingsType = allTypeBeanList.get(thingstype);

            holder.newMsg.setVisibility(View.GONE);
            Intent intent = new Intent(mContext, ThingDetailActivity.class);
            intent.putExtra(OTHERSNICKNAME,dynamicItemBean.getDynamics().getNickname());
            intent.putExtra(OTHERSPHOTO,dynamicItemBean.getDynamics().getUserphoto());
            intent.putExtra(OTHERSFABIAODATE,fabaiodate);
            intent.putExtra(OTHERSDIUSHILEIXING, losttype);
            intent.putExtra(OTHERSDIUSHIDATE, lostdate);
            intent.putExtra(OTHERSPLACE,place);
            intent.putExtra(OTHERSIMGS, dynamicItemBean.getDynamics().getThelost().getPhoto());
            intent.putExtra(OTHERSTHINGSTYPE, dynamicItemBean.getDynamics().getThelost().getTypeid());
            intent.putExtra(OTHERSID, dynamicItemBean.getDynamics().getThelost().getId());
            intent.putExtra(OTHERSDESC,dynamicItemBean.getDynamics().getThelost().getDescription());
            intent.putExtra(OTHERSTITLE,dynamicItemBean.getDynamics().getThelost().getTitle());
            intent.putExtra(OTHERUSERNAME,dynamicItemBean.getDynamics().getUsername());
            messagePresenter.updateIsRead(sharedPreferences.getString("SESSION",null),dynamicItemBean.getDynamics().getThelost().getId());
            mContext.startActivity(intent);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemAdapter.ViewHolder holder, int position) {

        UpDateMessageBean.DynamicsBeanX dynamicItemBean = searchItemBeanArrayList.get(position);
        Integer changenum = 0;
        if(isMessage) {
            changenum = changeNumList.get(position);
        }
        /**
         * "publishtime": "20181105173056",
         * "losttime": "2018110512",
         */
        String date_orig = dynamicItemBean.getDynamics().getThelost().getPublishtime();
        String fabaiodate = date_orig.substring(0,4)+".";
        if(!"0".equals(date_orig.substring(4, 5))){
            fabaiodate = fabaiodate+"0";
        }
        fabaiodate = date_orig.substring(5, 6) + ".";
        if(!"0".equals(date_orig.substring(6, 7))) {
            fabaiodate = fabaiodate+date_orig.substring(6, 7);
        }
        fabaiodate = fabaiodate + date_orig.substring(7,8);


        String lostdate_orig = dynamicItemBean.getDynamics().getThelost().getLosttime();
        String lostdate = lostdate_orig.substring(0, 4) + "." + lostdate_orig.substring(4, 6) + ".";
        if(!"0".equals(lostdate_orig.substring(6, 7))){
            lostdate = lostdate + lostdate_orig.substring(6, 7);
        }
        Log.e("Search1",""+lostdate+ "+"+lostdate_orig);
        lostdate = lostdate + lostdate_orig.substring(7,8)+"";
        Log.e("Search2",""+lostdate);
        int lostplace = dynamicItemBean.getDynamics().getThelost().getPlaceid();
        int losttype = dynamicItemBean.getDynamics().getThelost().getLosttype();
        int thingstype = dynamicItemBean.getDynamics().getThelost().getTypeid();
        int isread = dynamicItemBean.getRead();

        String place = AllURI.allPlaceBeanList.get(lostplace - 1);
//        String thingsType = allTypeBeanList.get(thingstype);

        int lostType = 0;
        switch (losttype) {
            case 0:{
                holder.qishileixing.setBackgroundResource(R.drawable.shape_thingstype_lost);
                holder.qishileixing.setText(" "+"丢"+" ");
                break;
            }
            case 1:{
                holder.qishileixing.setBackgroundResource(R.drawable.shape_thingstype_find);
                holder.qishileixing.setText(" "+"拾"+" ");
            }
            default: {
                break;
            }
        }
        //启事类型
       // holder.qishileixing.setImageResource(lostType);
        //时间地点
        holder.placeanddate.setText(place);
        //标题
        holder.title.setText(dynamicItemBean.getDynamics().getThelost().getTitle());
        holder.fabiaotime.setText(lostdate);
        holder.neckname.setText(dynamicItemBean.getDynamics().getNickname());

        sharedPreferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
        Log.e("ThingsType", AllURI.allTypeImgsList.get(dynamicItemBean.getDynamics().getThelost().getTypeid()-1));

        //类型小标签

        //类型图片
        holder.thingType.setText(" "+ SelectTypeUtils.getInstance().getImage(dynamicItemBean.getDynamics().getThelost().getLosttype())+" ");


        if(dynamicItemBean.getDynamics().getThelost().getPhoto() == null||dynamicItemBean.getDynamics().getThelost().getPhoto().equals("")||dynamicItemBean.getDynamics().getThelost().getPhoto().equals("default.jpg")) {
            holder.headimg.setImageResource(R.mipmap.diai1);
        }else {
            //事件图片
            Glide.with(mContext)
                    .load(dynamicItemBean.getDynamics().getThelost().getPhoto())
                    //.load(AllURI.getLostThingsPhoto(sharedPreferences.getString(SESSION, "null"), dynamicItemBean.getDynamics().getThelost().getPhoto()))
                    .asBitmap()
                    .into(holder.headimg);
        }



        //用户头像
        if(dynamicItemBean.getDynamics().getUserphoto().equals("default.jpg")) {

            holder.userphoto.setImageResource(R.mipmap.user);
        }else {
            Glide.with(mContext)
                    .load(dynamicItemBean.getDynamics().getUserphoto())
                    // .load(AllURI.getUserPhoto(sharedPreferences.getString(SESSION,"null"),dynamicItemBean.getDynamics().getUserphoto()))
                    .asBitmap()
                    .into(holder.userphoto);
        }

        //赏金
        holder.isNeedBounty.setVisibility(View.INVISIBLE);

                    //新消息气泡
                    if (isMessage) {
                        if (isread == 0){
                            holder.newMsg.setImageResource(R.drawable.message);
                            Log.d("显示没", String.valueOf(isread));
                        } else {
                            holder.newMsg.setImageResource(R.drawable.kongbai);
                        }
    }


        }

    @Override
    public int getItemCount() {
        return searchItemBeanArrayList.size();
    }

}

