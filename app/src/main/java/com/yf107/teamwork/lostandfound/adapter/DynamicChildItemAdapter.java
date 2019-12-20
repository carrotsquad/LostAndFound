package com.yf107.teamwork.lostandfound.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;
import com.yf107.teamwork.lostandfound.view.activity.ThingDetailActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${}
 * @updateAuthor $Author$
 * @updateDes ${}
 */
/*public class DynamicChildItemAdapter extends RecyclerView.Adapter<DynamicChildItemAdapter.ViewHolder> {
    private Context mContext;
    private List<DynamicItemBean> lists;
    private Activity activity;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        CircleImageView headimg;
        TextView neckname;
        TextView timeandplace;
        TextView title;
        ImageView thingType;
        ImageView qishileixing;
        ImageView thingphoto;
        TextView publishdate;


        public ViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.dynamic_card);
            headimg = view.findViewById(R.id.dynamic_card_headimg);
            neckname = view.findViewById(R.id.dynamic_card_neckname);
            timeandplace = view.findViewById(R.id.dynamic_card_dateandplace);
            title = view.findViewById(R.id.dynamic_card_title);
            thingType = view.findViewById(R.id.dynamic_card_thingtype);
            qishileixing = view.findViewById(R.id.dynamic_card_qishileixin);
            thingphoto = view.findViewById(R.id.dynamic_card_thingsphoto);
            publishdate = view.findViewById(R.id.dynamic_card_publishdate);
        }
    }

    public DynamicChildItemAdapter(List<DynamicItemBean> list, Activity activity) {
        this.activity = activity;
        this.lists = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_dynamic_item, parent, false);
        final DynamicChildItemAdapter.ViewHolder holder = new DynamicChildItemAdapter.ViewHolder(view);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DynamicItemBean dynamicItemBean = lists.get(position);

                String date_orig = dynamicItemBean.getThelost().getPublishtime();
                String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
                if (!"0".equals(date_orig.substring(6, 7))) {
                    fabaiodate = fabaiodate + date_orig.substring(6, 7);
                }
                fabaiodate = fabaiodate + date_orig.substring(7, 8) + "日";

                String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
                String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
                if (!"0".equals(lostdate_orig.substring(6, 7))) {
                    lostdate = lostdate + lostdate_orig.substring(6, 7);
                }
                lostdate = lostdate + lostdate_orig.substring(7, 8) + "日";

                int lostplace = dynamicItemBean.getThelost().getPlaceid();
                int losttype = dynamicItemBean.getThelost().getLosttype();
                int thingstype = dynamicItemBean.getThelost().getTypeid();

                String place = allPlaceBeanList.get(lostplace - 1);
//                String thingsType = allTypeBeanList.get(thingstype);

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
                intent.putExtra(OTHERSTITLE,dynamicItemBean.getThelost().getTitle());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DynamicItemBean dynamicItemBean = lists.get(position);
        /**
         * "publishtime": "20181105173056",
         * "losttime": "2018110512",
         */
       /* String date_orig = dynamicItemBean.getThelost().getPublishtime();
        String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
        if (!"0".equals(date_orig.substring(6, 7))) {
            fabaiodate = fabaiodate + date_orig.substring(6, 7);
        }
        fabaiodate = fabaiodate + date_orig.substring(7, 8) + "日";
        holder.publishdate.setText(fabaiodate + "发表");

        String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
        String lostdate = lostdate_orig.substring(0, 4) + "." + lostdate_orig.substring(4, 6) + ".";
        if (!"0".equals(lostdate_orig.substring(6, 7))) {
            lostdate = lostdate + lostdate_orig.substring(6, 7);
        }
        lostdate = lostdate + lostdate_orig.substring(7, 8) + "";


        holder.neckname.setText(dynamicItemBean.getNickname());

        holder.title.setText(dynamicItemBean.getThelost().getTitle());

        int lostplace = dynamicItemBean.getThelost().getPlaceid();
        int losttype = dynamicItemBean.getThelost().getLosttype();
        int thingstype = dynamicItemBean.getThelost().getTypeid();

        String place = allPlaceBeanList.get(lostplace - 1);
//        String thingsType = allTypeBeanList.get(thingstype);

        holder.timeandplace.setText(place + "  " + date_orig.substring(8,10) + ":" + date_orig.substring(10,12));

        int lostType = 0;
        switch (losttype) {
            case 0: {
                lostType = R.drawable.littleicon_type_lost1;
                break;
            }
            case 1: {
                lostType = R.drawable.littleicon_type_find1;
            }
            default: {
                break;
            }
        }
        //启事类型
        holder.qishileixing.setImageResource(lostType);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);

        Log.e("PHOTO", allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1));
        //类型图片
        if (dynamicItemBean.getThelost().getTypeid() == 1) {
            holder.thingType.setImageResource(R.drawable.littleicon_keys);
         //   holder.thingType.setImageResource(R.drawable.littleicon_type_find1);
        } else {
            Glide.with(mContext)
                 // .load(R.drawable.littleicon_type_lost1)
                    .load(getTypeLittlePhoto(sharedPreferences.getString(SESSION, "null"), allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1)))
                    .asBitmap()
                    .into(holder.thingType);
        }


        Log.e("PHOTO", String.valueOf(position));
        Log.e("PHOTO", dynamicItemBean.getThelost().getPhoto());


        //默认图片，
//        Glide.with(mContext)
//                .load(getTypePhoto(sharedPreferences.getString(SESSION,"null"),allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1)))
//                .asBitmap()
//                .into(holder.thingphoto);

        if ("".equals(dynamicItemBean.getThelost().getPhoto())) {
            Glide.with(mContext)
                .load(getTypePhoto(sharedPreferences.getString(SESSION,"null"),allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1)))
               //.load(R.mipmap.diai1)
                .asBitmap()
                .into(holder.thingphoto);
//            GetImageFromWeb.httpSetImageView(getTypePhoto(sharedPreferences.getString(SESSION, "null"), allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1))
//                    , holder.thingphoto
//                    , activity);
        } else {

            //事件图片
            String[] a = dynamicItemBean.getThelost().getPhoto().split(",");
            String c = getLostThingsPhoto(sharedPreferences.getString(SESSION, "null"), a[0]);
            Glide.with(mContext)
                 //   .load(R.mipmap.diai1)
                    .load(c)
                    .asBitmap()
                    .into(holder.thingphoto);
        }


        //用户头像
        Glide.with(mContext)
            //    .load(getUserPhoto(sharedPreferences.getString(SESSION, "null"), dynamicItemBean.getUserphoto()))
                .load(R.mipmap.user)
                .asBitmap()
                .into(holder.headimg);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }



    public void refresh(List<DynamicItemBean> addlist){
        int position = lists.size();
        lists.addAll(position,addlist);
        notifyDataSetChanged();
    }

}

        */


public class DynamicChildItemAdapter extends RecyclerView.Adapter<DynamicChildItemAdapter.ViewHolder> {
    private Context mContext;
    private List<DynamicItemBean> lists = new ArrayList<>();
    private Activity activity;

    public void addAllData(List<DynamicItemBean> lists) {
        this.lists.addAll(lists);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.lists.clear();
    }

    public DynamicChildItemAdapter(Context context) {
        mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        CircleImageView headimg;
        TextView neckname;
        TextView timeandplace;
        TextView title;
        ImageView thingType;
        ImageView qishileixing;
        ImageView thingphoto;
        TextView publishdate;


        public ViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.dynamic_card);
            headimg = view.findViewById(R.id.dynamic_card_headimg);
            neckname = view.findViewById(R.id.dynamic_card_neckname);
            timeandplace = view.findViewById(R.id.dynamic_card_dateandplace);
            title = view.findViewById(R.id.dynamic_card_title);
            thingType = view.findViewById(R.id.dynamic_card_thingtype);
            qishileixing = view.findViewById(R.id.dynamic_card_qishileixin);
            thingphoto = view.findViewById(R.id.dynamic_card_thingsphoto);
            publishdate = view.findViewById(R.id.dynamic_card_publishdate);
        }
    }

    public DynamicChildItemAdapter(List<DynamicItemBean> list, Activity activity) {
        this.activity = activity;
        this.lists = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_dynamic_item, parent, false);
        final DynamicChildItemAdapter.ViewHolder holder = new DynamicChildItemAdapter.ViewHolder(view);

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DynamicItemBean dynamicItemBean = lists.get(position);

                String date_orig = dynamicItemBean.getThelost().getPublishtime();
                String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
                if (!"0".equals(date_orig.substring(6, 7))) {
                    fabaiodate = fabaiodate + date_orig.substring(6, 7);
                }
                fabaiodate = fabaiodate + date_orig.substring(7, 8) + "日";

                String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
                String lostdate = lostdate_orig.substring(0, 4) + "年" + lostdate_orig.substring(4, 6) + "月";
                if (!"0".equals(lostdate_orig.substring(6, 7))) {
                    lostdate = lostdate + lostdate_orig.substring(6, 7);
                }
                lostdate = lostdate + lostdate_orig.substring(7, 8) + "日";

                int lostplace = dynamicItemBean.getThelost().getPlaceid();
                int losttype = dynamicItemBean.getThelost().getLosttype();
                int thingstype = dynamicItemBean.getThelost().getTypeid();

                String place = AllURI.allPlaceBeanList.get(lostplace - 1);
//                String thingsType = allTypeBeanList.get(thingstype);

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
                intent.putExtra(OTHERSTITLE,dynamicItemBean.getThelost().getTitle());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DynamicItemBean dynamicItemBean = lists.get(position);
        /**
         * "publishtime": "20181105173056",
         * "losttime": "2018110512",
         */
        String date_orig = dynamicItemBean.getThelost().getPublishtime();
        String fabaiodate = date_orig.substring(0, 4) + "年" + date_orig.substring(4, 6) + "月";
        if (!"0".equals(date_orig.substring(6, 7))) {
            fabaiodate = fabaiodate + date_orig.substring(6, 7);
        }
        fabaiodate = fabaiodate + date_orig.substring(7, 8) + "日";
        holder.publishdate.setText(fabaiodate + "发表");

        String lostdate_orig = dynamicItemBean.getThelost().getLosttime();
        String lostdate = lostdate_orig.substring(0, 4) + "." + lostdate_orig.substring(4, 6) + ".";
        if (!"0".equals(lostdate_orig.substring(6, 7))) {
            lostdate = lostdate + lostdate_orig.substring(6, 7);
        }
        lostdate = lostdate + lostdate_orig.substring(7, 8) + "";


        holder.neckname.setText(dynamicItemBean.getNickname());

        holder.title.setText(dynamicItemBean.getThelost().getTitle());

        int lostplace = dynamicItemBean.getThelost().getPlaceid();
        int losttype = dynamicItemBean.getThelost().getLosttype();
        int thingstype = dynamicItemBean.getThelost().getTypeid();

        String place = AllURI.allPlaceBeanList.get(lostplace - 1);
//        String thingsType = allTypeBeanList.get(thingstype);

        holder.timeandplace.setText(place + "  " + date_orig.substring(8,10) + ":" + date_orig.substring(10,12));

        int lostType = 0;
        switch (losttype) {
            case 0: {
                lostType = R.drawable.littleicon_type_lost1;
                break;
            }
            case 1: {
                lostType = R.drawable.littleicon_type_find1;
            }
            default: {
                break;
            }
        }
        //启事类型
        holder.qishileixing.setImageResource(lostType);

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("users", Context.MODE_PRIVATE);

        Log.e("PHOTO", AllURI.allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1));
        //类型图片
        if (dynamicItemBean.getThelost().getTypeid() == 1) {
            holder.thingType.setImageResource(R.drawable.littleicon_keys);
            //   holder.thingType.setImageResource(R.drawable.littleicon_type_find1);
        } else {
            Glide.with(mContext)
                    // .load(R.drawable.littleicon_type_lost1)
                    .load(AllURI.getTypeLittlePhoto(sharedPreferences.getString(SESSION, "null"), AllURI.allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1)))
                    .asBitmap()
                    .into(holder.thingType);
        }


        Log.e("PHOTO", String.valueOf(position));
        Log.e("PHOTO", dynamicItemBean.getThelost().getPhoto());


        //默认图片，
//        Glide.with(mContext)
//                .load(getTypePhoto(sharedPreferences.getString(SESSION,"null"),allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1)))
//                .asBitmap()
//                .into(holder.thingphoto);

        if ("".equals(dynamicItemBean.getThelost().getPhoto())) {
            Glide.with(mContext)
                    .load(AllURI.getTypePhoto(sharedPreferences.getString(SESSION,"null"), AllURI.allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid()-1)))
                    //.load(R.mipmap.diai1)
                    .asBitmap()
                    .into(holder.thingphoto);
//            GetImageFromWeb.httpSetImageView(getTypePhoto(sharedPreferences.getString(SESSION, "null"), allTypeImgsList.get(dynamicItemBean.getThelost().getTypeid() - 1))
//                    , holder.thingphoto
//                    , activity);
        } else {

            //事件图片
            String[] a = dynamicItemBean.getThelost().getPhoto().split(",");
            String c = AllURI.getLostThingsPhoto(sharedPreferences.getString(SESSION, "null"), a[0]);
            Glide.with(mContext)
                    //   .load(R.mipmap.diai1)
                    .load(c)
                    .asBitmap()
                    .into(holder.thingphoto);
        }


        //用户头像
        Glide.with(mContext)
                //    .load(getUserPhoto(sharedPreferences.getString(SESSION, "null"), dynamicItemBean.getUserphoto()))
                .load(R.mipmap.user)
                .asBitmap()
                .into(holder.headimg);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

}
