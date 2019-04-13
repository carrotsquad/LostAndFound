package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.Logger;

import static android.provider.Telephony.Carriers.PASSWORD;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeImgsList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeLittleImgsList;
import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.TO_SIGN_IN;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.VerifyActivity.SIGNIN;

/**
 * Description: 登陆activity
 * Created at: 2018/11/13 10:51
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class SignInActivity extends AppCompatActivity implements ISignInActivity, IAllTypesAndPlaces {

    public static final String PWD = "PWD";
    public static final String PNB = "PNB";
    public static final String EMAIL = "EMAIL";
    public static final String NICKNAME = "NICKNAME";
    public static final String SESSION = "SESSION";
    public static final String USERPHOTO = "USERPHOTO";
    public static final String ALLTYPES = "ALLTYPES";
    public static final String ALLPLACES = "ALLPLACES";
//    public static final String DElETED_NUM_LIST = "DElETED_NUM_LIST";

    @BindView(R.id.signin_signin)
    Button signin;

    @BindView(R.id.signin_pwd)
    EditText pwd;

    @BindView(R.id.signin_username)
    EditText email;

    @BindView(R.id.signin_tologin)
    TextView register;

    private SignPresenter signPresenter;
    private AllTypesAndPlacesPresenter allTypesAndPlacesPresenter;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    private Intent mIntent;
    private String session;
    //判断是否用户自己选择退出
    private boolean isExit ;
    private View statusBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        signPresenter = new SignPresenter(this);
        allTypesAndPlacesPresenter = new AllTypesAndPlacesPresenter(this);
        isExit = getIntent().getBooleanExtra("isExit", false);
        //如果已经登录过，再次登陆，直接进入缓冲页
        if (!"".equals(sharedPreferences.getString(EMAIL, "")) && !"".equals(sharedPreferences.getString(SESSION, "")) && !isExit) {
            Intent intent = new Intent(SignInActivity.this, BufferPageActivity.class);
            startActivity(intent);
            finish();
//            email.setText(sharedPreferences.getString(EMAIL, "null"));
//            pwd.setText(sharedPreferences.getString(PWD, "null"));
//            signPresenter.getSignIn(sharedPreferences.getString(EMAIL, "null"), sharedPreferences.getString(PWD, "null"));
        } else if (isExit) {
            email.setText("");
            pwd.setText("");
            isExit = false;
        }
//        else {
//            mIntent = getIntent();
//            if (!mIntent.getBooleanExtra(TO_SIGN_IN, true)) {
//                signPresenter.getSignIn(mIntent.getStringExtra(EMAIL), mIntent.getStringExtra(PWD));
//            }
//        }
        //实现渐变式状态栏
        setGradientStatusBarColor(this,statusBarView);
        //字体
        register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }


    @Override
    protected void onDestroy() {
        allTypesAndPlacesPresenter.dettachActivity();
        signPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.signin_tologin, R.id.signin_signin})
    void onClicked(View view) {
        switch (view.getId()) {
            //点击登陆
            case R.id.signin_signin: {
                if ("".equals(pwd.getText().toString()) || "".equals(email.getText().toString())) {
                    FancyToast.makeText(SignInActivity.this, "输入有问题", FancyToast.LENGTH_SHORT, FancyToast.WARNING, false).show();
                } else {
                    String eemail = email.getText().toString();
                    String password = pwd.getText().toString();
                    signPresenter.getSignIn(eemail, password);
                }
                break;
            }
            //点击“未有账号，点击注册”
            case R.id.signin_tologin: {
                Intent intent = new Intent(SignInActivity.this, LogInActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            default: {
                break;
            }
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
            FancyToast.makeText(SignInActivity.this, "登录失败", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
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
            Intent intent = new Intent(SignInActivity.this, BufferPageActivity.class);
            intent.putExtra(SESSION, session);
            startActivity(intent);
            finish();
        } else {
            FancyToast.makeText(SignInActivity.this, "无法获取丢失物品类型和地点", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }
}
