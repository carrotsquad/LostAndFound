package com.zhangqianyuan.teamwork.lostandfound.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.DynamicItemAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicsRequestBean;
import com.zhangqianyuan.teamwork.lostandfound.presenter.DynamicPresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.IDynamicPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IDynaicFragment;

import java.util.ArrayList;
import java.util.List;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.OURJSESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

/**
 * Description
 * 动态板块子页面（分为失物/招领）
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */

@SuppressLint("ValidFragment")
public class DynamicChildFragment extends Fragment implements IDynaicFragment{
    private RecyclerView mRecyclerView;
    private DynamicItemAdapter mDynamicItemAdapter;
    private List<DynamicItemBean> lists = new ArrayList<>();
    private DynamicPresenter iDynamicPresenter;
    private int pos;
    private SharedPreferences sharedPreferences;

    private String session="";

    @SuppressLint("ValidFragment")
    public DynamicChildFragment(int i,String session){
        pos = i;
        this.session = session;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= LayoutInflater.from(getContext()).inflate(R.layout.fragment_dynamic_lostorfind,container,false);
        mRecyclerView = view.findViewById(R.id.dynamic_list);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);

        mRecyclerView.setLayoutManager(manager);
        mDynamicItemAdapter = new DynamicItemAdapter(lists);
        mRecyclerView.setAdapter(mDynamicItemAdapter);
        iDynamicPresenter = new DynamicPresenter();
        iDynamicPresenter.attachActivity(this);
        initLists();

//        sharedPreferences = getContext().getSharedPreferences("users", Context.MODE_PRIVATE);
       return view;
    }

    public void  initLists(){
        switch (pos){
            case 0:{
                iDynamicPresenter.getDynamicLostData(new DynamicsRequestBean(0,15),session);
                break;
            }
            case 1:{
                iDynamicPresenter.getDynamicFindData(new DynamicsRequestBean(0,15),session);
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        iDynamicPresenter.dettachActivity();
        super.onDestroyView();
    }

    @Override
    public void showData(Boolean status, List<DynamicItemBean> searchItemBeanArrayList) {
        if(status){
            lists.clear();
            lists.addAll(searchItemBeanArrayList);
            mDynamicItemAdapter.notifyDataSetChanged();
//            searchItemAdapter.notifyItemChanged(this.searchItemBeanArrayList.size()-1);
//            recyclerView.scrollToPosition(msgList.size() - 1);
        }else {
            FancyToast.makeText(getContext(),"出现了问题",FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
        }
    }
}

