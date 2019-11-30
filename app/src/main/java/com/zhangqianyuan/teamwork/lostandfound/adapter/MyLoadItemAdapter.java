package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.network.AllURI;
import com.zhangqianyuan.teamwork.lostandfound.popupwindow.ArrowPopWindows;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MyLoadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.ThingDetailActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UploadFormActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.popupwindow.ArrowPopWindows.SHOW_TOP;
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
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */

public class MyLoadItemAdapter  extends RecyclerView.Adapter<MyLoadItemAdapter.ViewHolder>  {
    private List<TheLostBean> lists ;
    private Context mContext;
    private String userphoto;
    private String username;
    private String nickname;
    private Boolean isMessage;
    private int id;
    private MyLoadPresenter myLoadPresenter;
    private SharedPreferences sharedPreferences;
    private String jsession;

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        ImageView thingtype;
        ImageView  thingtypetxt;
        ImageView  eventtype;
        TextView  time  ;                     //月 日
        TextView  time2;                     //19:00
        TextView  where;
        RelativeLayout relativeLayout;
        SwipeMenuLayout swipeMenuLayout;
        ArrowPopWindows arrowPopWindows;
        Button btnDlt;
        Button btnEdit;
        Button btnFinish;

        public ViewHolder(View view){
            super(view);
            swipeMenuLayout = (SwipeMenuLayout) view;
            thingtype = view.findViewById(R.id.myupload_thingtype);
            thingtypetxt = view.findViewById(R.id.myupload_thingtype_txt);
            eventtype = view.findViewById(R.id.myupload_eventtype);
            time = view.findViewById(R.id.myupload_time);
            time2 = view.findViewById(R.id.myupload_time2);
            where= view.findViewById(R.id.myupload_where);
            relativeLayout= view.findViewById(R.id.upload_item_relativeview);
            btnDlt=view.findViewById(R.id.upload_item_delete);
            btnEdit = view.findViewById(R.id.upload_item_edit);
            btnFinish = view.findViewById(R.id.upload_item_finish);

        }

    }

    public MyLoadItemAdapter (List<TheLostBean> list, String userphoto, String username, String nickname , Boolean isMessage){
        this.lists=list;
        this.username = username;
        this.userphoto = userphoto;
        this.nickname = nickname;
        this.isMessage = isMessage;
    }

    /**
     * 注入myLoadPresenter
     * @param myLoadPresenter
     */
    public void attachPresenter(MyLoadPresenter myLoadPresenter){
        this.myLoadPresenter=myLoadPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("oncreateviewholder","seccess");
        mContext=parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.userinfo_myupload_item,parent,false);
        final MyLoadItemAdapter.ViewHolder holder = new MyLoadItemAdapter.ViewHolder(view);
        if(isMessage) {
            holder.relativeLayout.setOnLongClickListener(v->{
                        holder.arrowPopWindows.show(view, SHOW_TOP);
                        return true;
                    }
            );
        }else {
            holder.swipeMenuLayout.setSwipeEnable(false);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                for (TheLostBean bean:
//                        list) {
//                    DynamicItemBean dynamicItemBean = new DynamicItemBean();
//                    dynamicItemBean.setThelost(bean);
//                    dynamicItemBean.setUserphoto(userphoto);
//                    dynamicItemBean.setUsername(username);
//                    dynamicItemBean.setNickname(nickname);
//                    dynamicItemBeanList.add(dynamicItemBean);
//                }
                int position = holder.getAdapterPosition();
                DynamicItemBean dynamicItemBean = new DynamicItemBean();
                dynamicItemBean.setNickname(nickname);
                dynamicItemBean.setUsername(username);
                dynamicItemBean.setUserphoto(userphoto);
                dynamicItemBean.setThelost(lists.get(position));

                String date_orig = dynamicItemBean.getThelost().getPublishtime();
                String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
                if (!"0".equals(date_orig.substring(6, 7))) {
                    fabaiodate = fabaiodate + date_orig.substring(6, 7);
                }
                fabaiodate = fabaiodate + date_orig.substring(7, 8) + "日" + date_orig.substring(8, 10) + ":" + date_orig.substring(10, 12);

                String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
                String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
                if (!"0".equals(lostdate_orig.substring(6, 7))) {
                    lostdate = lostdate + lostdate_orig.substring(6, 7);
                }
                lostdate = lostdate + lostdate_orig.substring(7, 8) + "日";

                int lostplace = dynamicItemBean.getThelost().getPlaceid();
                int losttype = dynamicItemBean.getThelost().getLosttype();
                int thingstype = dynamicItemBean.getThelost().getTypeid();

                String place = allPlaceBeanList.get(lostplace);
                String thingsType = allTypeBeanList.get(thingstype);

                Intent intent = new Intent(mContext, ThingDetailActivity.class);
                intent.putExtra(OTHERSNICKNAME, dynamicItemBean.getNickname());
                intent.putExtra(OTHERSPHOTO, dynamicItemBean.getUserphoto());
                intent.putExtra(OTHERSFABIAODATE, fabaiodate);
                intent.putExtra(OTHERSDIUSHILEIXING, losttype);
                intent.putExtra(OTHERSDIUSHIDATE, lostdate);
                intent.putExtra(OTHERSPLACE, place);
                intent.putExtra(OTHERSIMGS, dynamicItemBean.getThelost().getPhoto());
                intent.putExtra(OTHERSTHINGSTYPE, dynamicItemBean.getThelost().getTypeid());
                intent.putExtra(OTHERSID, dynamicItemBean.getThelost().getId());
                intent.putExtra(OTHERSDESC, dynamicItemBean.getThelost().getDescription());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sharedPreferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);
        String s = AllURI.getLostThingsPhoto(mContext.getSharedPreferences("users",Context.MODE_PRIVATE).getString("SESSION",null),lists.get(position).getPhoto());;
        Glide.with(mContext)
                .load(s)
                .asBitmap()
                .into(holder.thingtype);
        String x = AllURI.getTypeLittlePhoto(mContext.getSharedPreferences("users",Context.MODE_PRIVATE).getString("SESSION",null),AllURI.allTypeImgsList.get(lists.get(position).getTypeid()-1));
        Glide.with(mContext)
                .load(x)
                .asBitmap()
                .into(holder.thingtypetxt);
        if (lists.get(position).getLosttype()==0){
            holder.eventtype.setImageResource(R.drawable.littleicon_type_lost);
        }else if (lists.get(position).getLosttype()==1){
            holder.eventtype.setImageResource(R.drawable.littleicon_type_find);
        }
        String lostPlace = AllURI.allPlaceBeanList.get(lists.get(position).getPlaceid());
        Log.e("MyLoadItemAdapter",""+lists.get(position).getPlaceid()+lostPlace);
        holder.where.setText(lostPlace);
        String publishTime= lists.get(position).getPublishtime();
        if (!publishTime.equals("")){
            String   mouth = publishTime.substring(4,6);
            String    day = publishTime.substring(6,8);
            String    hours=publishTime.substring(8,10);
            String time1 = mouth+"月"+day+"日";
            String time2=  hours+"点";
            holder.time.setText(time1);
            holder.time2.setText(time2);
        }



        if (isMessage) {
            //侧滑删除
            holder.btnDlt.setOnClickListener(v -> {
                holder.swipeMenuLayout.quickClose();
                lists.remove(position);
                FancyToast.makeText(mContext, "成功删除", FancyToast.CONFUSING, Toast.LENGTH_SHORT, false).show();
                notifyItemRemoved(position);
                notifyDataSetChanged();
            });

            holder.btnFinish.setOnClickListener(view -> {
                jsession = sharedPreferences.getString(SESSION, "null");
                id = lists.get(position).getId();
                myLoadPresenter.postSuccess(jsession,id);
                Log.d("Tag","success"+"id");
                holder.swipeMenuLayout.quickClose();
                lists.remove(position);
                FancyToast.makeText(mContext, "递爱成功", FancyToast.CONFUSING, Toast.LENGTH_SHORT, false).show();
                notifyItemRemoved(position);
                notifyDataSetChanged();
            });

            holder.btnEdit.setOnClickListener(view -> {
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Intent intent = new Intent(mContext, UploadFormActivity.class);
                mContext.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }



}
