package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItemBean;

import org.w3c.dom.Text;

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
public class MyHistoryAdapter  extends RecyclerView.Adapter<MyHistoryAdapter.ViewHolder> {
    private List<MyHistoryItemBean> lists;


    public static class ViewHolder extends  RecyclerView.ViewHolder {
        @BindView(R.id.userinfo_myhistory_card)
        CardView mCardView;

        @BindView(R.id.userinfo_myhistory_typeimg)
        ImageView  typeImg;

        @BindView(R.id.userinfo_myhistory_typetext)
        ImageView typeText;

        @BindView(R.id.userinfo_myhistory_isdone)
        TextView isdone;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public MyHistoryAdapter ( List<MyHistoryItemBean> list){
        this.lists = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
          MyHistoryItemBean bean = lists.get(position);
          holder.typeImg.setImageResource(bean.getTypeImg());
          holder.typeText.setImageResource(bean.getType());
          if (bean.isDone()){
              holder.isdone.setText("递爱成功!");
          }else
          holder.isdone.setText("递爱失败!");
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }



}
