package com.zhangqianyuan.teamwork.lostandfound.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.MyLoadPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMyLoadActivity;

import java.util.ArrayList;
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
public class MyLoadItemAdapter extends RecyclerView.Adapter<MyLoadItemAdapter.ViewHolder> implements IMyLoadActivity {
    private List<MyLoadItemBean> lists = new ArrayList<>();

    @Override
    public void showData(List<MyLoadItemBean> bean) {
        lists.clear();
        lists.addAll(bean);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView  description;
        @BindView(R.id.userinfo_myupload_card)
        CardView  mCardView;

        @BindView(R.id.userinfo_myupload_typeimg)
        ImageView typeImg;

        @BindView(R.id.userinfo_myupload_where)
        TextView  where;

        @BindView(R.id.userinfo_myupload_typetext)
        ImageView typeText;

        @BindView(R.id.userinfo_myupload_time)
        TextView  time;
        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public MyLoadItemAdapter (){
        MyLoadPresenter presenter = new MyLoadPresenter(new MyLoadModel());
        presenter.getMyloadData();
    }

    @NonNull
    @Override
    // TODO: 2018/11/13  需要做每个item 的点击后的界面
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info_myload_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       MyLoadItemBean bean = lists.get(position);
       holder.typeImg.setImageResource(bean.getTypeId());
       holder.typeText.setImageResource(bean.getType());
       holder.time.setText(bean.getTime());
       holder.where.setText(bean.getWhere());
       holder.description.setText(bean.getDescription());
    }

    @Override
    public int getItemCount() {
       return  lists.size();
    }

}
