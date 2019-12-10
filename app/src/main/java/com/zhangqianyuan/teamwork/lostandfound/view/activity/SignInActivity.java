package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.zhangqianyuan.teamwork.lostandfound.utils.EditUtil;
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
    public static final String STU = "STU";
    public static final String SESSION = "SESSION";
    public static final String USERPHOTO = "USERPHOTO";
    public static final String ALLTYPES = "ALLTYPES";
    public static final String ALLPLACES = "ALLPLACES";
    public static final String ISEXIT = "isExit";
    public static final String EXIT_BACK = "isExit1";

//    public static final String DElETED_NUM_LIST = "DElETED_NUM_LIST";

    @BindView(R.id.signin_signin)
    Button signin;

    @BindView(R.id.signin_pwd)
    EditText pwd;

    @BindView(R.id.signin_username)
    EditText email;

    @BindView(R.id.signin_tologin)
    TextView register;

    @BindView(R.id.wrong1)
    TextView wrong1;

    @BindView(R.id.reset_password)
    TextView reset_password;

    @BindView(R.id.all_clear)
    Button all_clear;

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
        boolean isExit = Boolean.parseBoolean(sharedPreferences.getString(ISEXIT,"true"));
        boolean isExit1 = getIntent().getBooleanExtra(EXIT_BACK,false);
        Log.d("Boomerr---日志打印", String.valueOf(isExit));
        /**
         * isExit是写入缓存中判断当前用户是否退出了登录  默认是true
         * 当用户登陆成功后会将这个值重写为false
         * isExit1是用户从设置界面退出后传出的值，一边情况设置为false  当用户退出登录的时候回传一个true 阻止直接跳过登录页面
         *
         * */
        if(isExit1){
            sharedPreferences.edit().putString(ISEXIT,"true").commit();
            Log.d("Boomerr日志打印","更新isEixt" + sharedPreferences.getString(ISEXIT,"true"));
        }
        //如果已经登录过，再次登陆，直接进入缓冲页
        if (!"".equals(sharedPreferences.getString(EMAIL, "")) && !"".equals(sharedPreferences.getString(SESSION, "")) && !isExit && !isExit1) {
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
        reset_password.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

        EditUtil.EditAllClear(all_clear,pwd);
    }


    @Override
    protected void onDestroy() {
        allTypesAndPlacesPresenter.dettachActivity();
        signPresenter.dettachActivity();
        super.onDestroy();
    }

    @OnClick({R.id.signin_tologin, R.id.signin_signin,R.id.all_clear,R.id.reset_password})
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

                break;
            }

            //一键清除清除文本框
            case R.id.all_clear:{
                pwd.setText("");
                pwd.requestFocusFromTouch();
                break;
            }
            //重置密码

            case R.id.reset_password:{
                Intent intent = new Intent(SignInActivity.this,ForgetPasswordActivity.class);
                intent.putExtra("session",SESSION);
                startActivity(intent);

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
//            Intent intent = getIntent();
//            String s = intent.getStringExtra(PWD);
//            signInBean.getUser().setPassword(s);
            int[] arrayint = new int[10000];
            editor.putString(EMAIL, signInBean.getUser().getUsername());
            editor.putString(PWD, signInBean.getUser().getPassword());
            editor.putString(STU, signInBean.getUser().getNickname());
            editor.putString(PNB, signInBean.getUser().getPhonenumber());
            editor.putString(USERPHOTO, signInBean.getUser().getPhoto());
            editor.putString(SESSION, signInBean.getJSESSIONID());
            editor.putString(ISEXIT,"false");
            session = signInBean.getJSESSIONID();
            //去获取所有的丢失物品类型和地点
            editor.commit();
            allTypesAndPlacesPresenter.getAllTypesAndPlaces(signInBean.getJSESSIONID());
        } else {
            wrong1.setText("  您输入的账号或密码错误！");
            //FancyToast.makeText(SignInActivity.this, "登录失败", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
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
