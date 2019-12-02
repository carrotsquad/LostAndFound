package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.haha.perflib.Main;
import com.squareup.leakcanary.LeakCanary;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MainViewAdapter;
import com.zhangqianyuan.teamwork.lostandfound.event.MessageEvent;
import com.zhangqianyuan.teamwork.lostandfound.model.UserInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.UserInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.services.ActivityManager;
import com.zhangqianyuan.teamwork.lostandfound.services.UpdateMessageService;
import com.zhangqianyuan.teamwork.lostandfound.view.fragment.DynamicFragment;
import com.zhangqianyuan.teamwork.lostandfound.view.fragment.MessageFragment;
import com.zhangqianyuan.teamwork.lostandfound.view.fragment.SearchFragment;
import com.zhangqianyuan.teamwork.lostandfound.view.fragment.UserInfoFragment;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.Logger;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

import static com.zhangqianyuan.teamwork.lostandfound.utils.StatusBarUtil.setGradientStatusBarColor;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;


/**
 * Description: 主页面
 * Created at: 2018/10/30 0:08
 *
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */

/**
 * ui还没确定所以底部导航的图标是乱改的！！！
 */
public class MainActivity extends AppCompatActivity {

    public static final String TO_SIGN_IN="to sign in";
    public static final Integer FINE_INTERNET_STATUS = 200;
    public static final Integer BAD_INTERNET_STATUS = 400;
    public static final String TYPEID = "TYPEID";
    public static final String QISHILEIXING = "QISHILEIXING";

    /**
     * 上次点击返回键的时间
     */
    private long lastBackPressed;

    /**
     * 两次点击的间隔时间
     */
    private static final int QUIT_INTERVAL = 2000;

    /**
     * 几个常量
     */
    private final int DONGTAI_FRAGMENT = 0;
    private final int SEARCH_FRAGMENT = 1;
    private final int MESSAGE_FRAGMENT = 2;
    private final int MINE_FRAGMENT = 3;

    @BindView(R.id.main_view_pager)
    ViewPager mViewPager;

    /* 这个里面包含了图标和文字 */
    @BindView(R.id.navigation)
    BottomNavigationView mBottomNav;

   // @BindView(R.id.fabu11)
   // Button fabu11;

    BottomNavigationItemView itemView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    badge.hide(true);
                case 1: {
                    badge = new QBadgeView(MainActivity.this)
                            .bindTarget(itemView)
                            .setShowShadow(true)
                            .setBadgeGravity(Gravity.END | Gravity.TOP)
                            .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                                @Override
                                public void onDragStateChanged(int dragState, Badge badge, View targetView) {

                                }
                            }).setBadgeText("");
                }
            }
        }
    };


    public static Badge badge;
    private String[] titles = new String[]{"动态", "搜索", "消息", "我的"};
    private String session = "";
    private View statusBarView;

    //服务
    private UpdateMessageService.MsgBinder msgBinder;
    private UpdateMessageService updateMessageService;

    //服务连接
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            msgBinder = (UpdateMessageService.MsgBinder) service;
            updateMessageService = msgBinder.getService();
            updateMessageService.setUpdateMessageListenser(messageEvent -> {
                EventBus.getDefault().post(messageEvent);
                Logger.e("Service Test");
            });
            updateMessageService.startService();
            //先设置listener
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        //实现渐变式状态栏
        setGradientStatusBarColor(this, statusBarView);
     //  mBottomNav.setItemIconTintList(null);
        ActivityManager.getActivityManager().add(this);
       ButterKnife.bind(this);
        try {
            initView();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        session = intent.getStringExtra(SESSION);
        initPermission();
        initService();
//        fabu11.setOnClickListener(new View.OnClickListener() {
  //          @Override
 //           public void onClick(View view) {
  //              showPopUpWindow();
  //          }
  //      });
    }

    /**
     * 注销EventBus
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        stopService(new Intent(this, UpdateMessageService.class));
    }


    /**
     * 初始化服务
     */
    public void initService() {
        //绑定服务
        Intent intent = new Intent(this, UpdateMessageService.class);
//        startService(intent);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }


    /**
     * Description: 初始化界面
     */
    private void initView() throws NoSuchFieldException, IllegalAccessException {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new DynamicFragment(session));
        fragments.add(SearchFragment.newInstance());
        fragments.add(MessageFragment.newInstance());
        fragments.add(new UserInfoFragment());
         itemView =  mBottomNav.findViewById(R.id.message_ui);

        MainViewAdapter mainViewAdapter = new MainViewAdapter(getSupportFragmentManager());

        mainViewAdapter.setFragments(fragments);

        mViewPager.setAdapter(mainViewAdapter);
        mViewPager.setOffscreenPageLimit(3);


        //划页监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position <= 1) {
                    mBottomNav.getMenu().getItem(position).setChecked(true);
                } else if (position >= 2 && position <= 3) {
                    mBottomNav.getMenu().getItem(position + 1).setChecked(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 1) {
                    mBottomNav.getMenu().getItem(position).setChecked(true);
                } else if (position >= 2 && position <= 3) {
                    mBottomNav.getMenu().getItem(position + 1).setChecked(true);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        disableShiftMode(mBottomNav);

        //加入底部导航监听
        mBottomNav.setOnNavigationItemSelectedListener((@NonNull MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.dongtai_ui: {
                    mViewPager.setCurrentItem(DONGTAI_FRAGMENT);
                    return true;
                }
                case R.id.search_ui: {
                    mViewPager.setCurrentItem(SEARCH_FRAGMENT);
                    return true;
                }
                case R.id.fabu: {
                    showPopUpWindow();
                    return false;
                }
                case R.id.message_ui: {
                    mViewPager.setCurrentItem(MESSAGE_FRAGMENT);
                    Message message = new Message();
                    message.arg1 = 0;
                    handler.sendMessage(message);
                    return true;
                }
                case R.id.mine_ui: {
                    mViewPager.setCurrentItem(MINE_FRAGMENT);
                    return true;
                }
                default: {
                    return true;
                }
            }
        });
    }

    /**
     * popupWindow
     */
    private void showPopUpWindow() {
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.choose, null);
        final PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopWindow.setContentView(contentView);
        //popupWindow出场动画
//        mPopWindow.setAnimationStyle(R.style.xinjian_popUp_style);
        ImageView newShiWu = contentView.findViewById(R.id.newShiWu);
        ImageView newZhaoLing = contentView.findViewById(R.id.newZhaoLing);
        ImageView cancel = contentView.findViewById(R.id.cancel);
        TextView textView=contentView.findViewById(R.id.textView);
        TextView textView1=contentView.findViewById(R.id.textView2);

        float newshiwu =  newShiWu.getY();


        View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);

        //❌号旋转动画
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(cancel, "rotation", 0f, 180f);
        objectAnimator.setDuration(500);
        objectAnimator.start();

        //平移动画
        int bottom = dip2px(this, 220);
        float[] dist = new float[]{bottom, 60, -30, -20 - 10, 0};
        startTranslationAnimation(newShiWu, 500, dist);
        startTranslationAnimation(newZhaoLing, 500, dist);
        startTranslationAnimation(textView, 500, dist);
        startTranslationAnimation(textView1, 500, dist);

        //新建失物
        newShiWu.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), UploadActivity.class);
            intent.putExtra(QISHILEIXING, 0);
            startActivity(intent);
        });

        //ic_newzhaolin
        newZhaoLing.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), UploadActivity.class);
            intent.putExtra(QISHILEIXING, 1);
            startActivity(intent);
        });

        //取消
        cancel.setOnClickListener(view -> {
            closeTranslationAnimation(newShiWu,300,bottom);
            closeTranslationAnimation(newZhaoLing,300,bottom);
            closeTranslationAnimation(textView,300,bottom);
            closeTranslationAnimation(textView1,300,bottom);
            contentView.postDelayed(()-> mPopWindow.dismiss(),300);
        });

        /**
         * 点击外部退出
         */
        mPopWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getY() < newshiwu) {
                    closeTranslationAnimation(newShiWu, 300, bottom);
                    closeTranslationAnimation(newZhaoLing, 300, bottom);
                    closeTranslationAnimation(textView, 300, bottom);
                    closeTranslationAnimation(textView1, 300, bottom);
                    contentView.postDelayed(() -> mPopWindow.dismiss(), 300);
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * 平移动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private void startTranslationAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * 关闭 popupWindow 时的动画
     *
     * @param view     mView
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private void closeTranslationAnimation(View view, int duration, int next) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0f, next);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     * @return 转换之后的px值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 申请权限
     */
    private void initPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    /**
     * 申请权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    FancyToast.makeText(MainActivity.this, "你拒绝了权限", FancyToast.ERROR, Toast.LENGTH_SHORT, false).show();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 重写返回键响应函数，按两次才退出
     */
    @Override
    public void onBackPressed() {
        long backPressed = System.currentTimeMillis();
        if (backPressed - lastBackPressed > QUIT_INTERVAL) {
            lastBackPressed = backPressed;
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_LONG).show();
        } else {
            finish();
            System.exit(0);
        }
    }


    /**
     * Description: 反射解决使用的时候 item 数大于 3 个时会有位移
     */
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) throws NoSuchFieldException, IllegalAccessException {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                // noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

}
