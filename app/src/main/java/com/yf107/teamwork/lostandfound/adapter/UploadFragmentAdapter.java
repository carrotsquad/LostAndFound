package com.yf107.teamwork.lostandfound.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.UploadItemBean;
import com.yf107.teamwork.lostandfound.view.activity.UploadFormActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.yf107.teamwork.lostandfound.view.activity.MainActivity.QISHILEIXING;
import static com.yf107.teamwork.lostandfound.view.activity.MainActivity.TYPEID;


public class UploadFragmentAdapter extends RecyclerView.Adapter<UploadFragmentAdapter.ViewHolder> {
    private List<UploadItemBean> lists;
    private Boolean isMessage;
    private Context mContext;
    private Integer qishileixing;

    public static class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.upload_item)
        CardView mCardView;

        @BindView(R.id.upload_type_img)
        ImageView img;

        @BindView(R.id.upload_type_text)
        TextView  text;


        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public UploadFragmentAdapter(List<UploadItemBean> list,Integer qishileixing){
        this.qishileixing = qishileixing;
        this.lists = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_recycle_item,parent,false);
        final UploadFragmentAdapter.ViewHolder viewHolder = new UploadFragmentAdapter.ViewHolder(view);
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext,UploadFormActivity.class);
                intent.putExtra(QISHILEIXING,qishileixing);
                intent.putExtra(TYPEID,position);
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UploadItemBean bean = lists.get(position);
        if(position==0) {
            holder.img.setImageResource(R.drawable.ic_keys);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 1){
            holder.img.setImageResource(R.drawable.ic_wallet);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 2){
            holder.img.setImageResource(R.drawable.ic_zhengjian);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 3){
            holder.img.setImageResource(R.drawable.ic_upan);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 4){
            holder.img.setImageResource(R.drawable.ic_book);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 5){
            holder.img.setImageResource(R.drawable.ic_wenju);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 6){
            holder.img.setImageResource(R.drawable.ic_yusan);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 7){
            holder.img.setImageResource(R.drawable.ic_shubao);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 8){
            holder.img.setImageResource(R.drawable.ic_yifu);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 9){
            holder.img.setImageResource(R.drawable.ic_shoushi);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 10){
            holder.img.setImageResource(R.drawable.ic_diannao);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 11){
            holder.img.setImageResource(R.drawable.ic_phone);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 12){
            holder.img.setImageResource(R.drawable.ic_chongdian);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 13){
            holder.img.setImageResource(R.drawable.ic_card);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 14){
            holder.img.setImageResource(R.drawable.ic_chongdian);
            holder.text.setText(bean.getTypeText());
        }
        if (position == 15){
            holder.img.setImageResource(R.drawable.ic_others);
            holder.text.setText(bean.getTypeText());
        }







     /*   }else {
            Glide.with(mContext)
                    .load(bean.getTypeImgId())
                    .into(holder.img);
        }

      */

        Log.e("TypesImgUrl",bean.getTypeImgId());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

}
