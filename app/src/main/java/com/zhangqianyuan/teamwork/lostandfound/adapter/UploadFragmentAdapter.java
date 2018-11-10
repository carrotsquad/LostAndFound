package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.UploadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UploadActivity;
import com.zhangqianyuan.teamwork.lostandfound.view.activity.UploadFormActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UploadFragmentAdapter extends RecyclerView.Adapter<UploadFragmentAdapter.ViewHolder> {
    private List<UploadItemBean> lists;

    private Context mContext;



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

    public UploadFragmentAdapter(List<UploadItemBean> list){
        this.lists = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_recycle_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.getContext().startActivity(new Intent(mContext,UploadFormActivity.class));
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UploadItemBean  bean = lists.get(position);
       holder.img.setImageResource(bean.getTypeImgId());
        holder.text.setText(bean.getTypeText());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

}
