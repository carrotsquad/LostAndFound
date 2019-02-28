package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.bean.PlaceBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.SignInBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TypeBean;
import com.zhangqianyuan.teamwork.lostandfound.presenter.AllTypesAndPlacesPresenter;
import com.zhangqianyuan.teamwork.lostandfound.presenter.SignPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IAllTypesAndPlaces;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.ISignInActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.finalteam.toolsfinal.Logger;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeImgsList;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.EMAIL;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.NICKNAME;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PNB;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.PWD;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.USERPHOTO;

/**
 * @Description: 缓冲页
 * Created at: 2019/2/27 23:40
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */
public class BufferPageActivity extends AppCompatActivity implements ISignInActivity, IAllTypesAndPlaces {


    private SignPresenter signPresenter;
    private AllTypesAndPlacesPresenter allTypesAndPlacesPresenter;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;
    private String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer_page);
        Intent intent = getIntent();
        String session = intent.getStringExtra(SESSION);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        signPresenter = new SignPresenter(this);
        allTypesAndPlacesPresenter = new AllTypesAndPlacesPresenter(this);
        if(session!=null&&!"".equals(session)) {
            Observable.timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            FancyToast.makeText(BufferPageActivity.this, "登录成功", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
                            Intent intent = new Intent(BufferPageActivity.this, MainActivity.class);
                            intent.putExtra(SESSION, session);
                            startActivity(intent);
                            finish();
                        }
                    });
        }else {
            signPresenter.getSignIn(sharedPreferences.getString(EMAIL, "null"), sharedPreferences.getString(PWD, "null"));
        }
    }

    @Override
    protected void onDestroy() {
        allTypesAndPlacesPresenter.dettachActivity();
        signPresenter.dettachActivity();
        super.onDestroy();
    }

    /**
     * 沉浸式状态栏
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    public void getIAllTypesAndPlaces(Boolean status, List<TypeBean> typeBeanList, List<PlaceBean> placeBeanList) {
        if (status) {
            int i = typeBeanList.size();
            for (int k = 0; k < i; k++) {
                allTypeBeanList.add(typeBeanList.get(k).getName());
                allTypeImgsList.add(typeBeanList.get(k).getPhoto());
            }
            i = placeBeanList.size();
            for (int k = 0; k < i; k++) {
                allPlaceBeanList.add(placeBeanList.get(k).getName());
            }
            Log.e("SignIn", allPlaceBeanList.toString());
            Log.e("SignIn", allTypeBeanList.toString());
            Log.e("SignIn", allTypeImgsList.toString());
            Log.e("SignIn", sharedPreferences.getString(SESSION, "null"));
            Intent intent = new Intent(BufferPageActivity.this, MainActivity.class);
            intent.putExtra(SESSION, session);
            startActivity(intent);
            finish();
        } else {
            FancyToast.makeText(BufferPageActivity.this, "无法获取丢失物品类型和地点", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }

    @Override
    public void showSignInStatus(Boolean status, SignInBean signInBean) {
        if (status) {
            editor = sharedPreferences.edit();
            int[] arrayint = new int[10000];
            editor.putString(EMAIL, signInBean.getUser().getUsername());
            editor.putString(PWD, signInBean.getUser().getPassword());
            editor.putString(NICKNAME, signInBean.getUser().getNickname());
            editor.putString(PNB, signInBean.getUser().getPhonenumber());
            editor.putString(USERPHOTO, signInBean.getUser().getPhoto());
            editor.putString(SESSION, signInBean.getJSESSIONID());
            session = signInBean.getJSESSIONID();
            //去获取所有的丢失物品类型和地点
            editor.commit();
            allTypesAndPlacesPresenter.getAllTypesAndPlaces(signInBean.getJSESSIONID());
        } else {
            FancyToast.makeText(BufferPageActivity.this, "登录失败", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }
}
