package com.zhangqianyuan.teamwork.lostandfound.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangqianyuan.teamwork.lostandfound.R;

/**
 * Description
 * 用户资料界面
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
// TODO: 2018/11/8  给item加点击事件 
public class UserInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return  inflater.inflate(R.layout.fragment_userinfo,container,false);
    }
    public static Fragment newInstance(){
       UserInfoFragment fragment = new UserInfoFragment();
        return fragment;
    }
}
