package com.yf107.teamwork.lostandfound.view.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yf107.teamwork.lostandfound.adapter.ThingDetailAdapter;
import com.yf107.teamwork.lostandfound.image.GetImageFromWeb;
import com.yf107.teamwork.lostandfound.image.WebImageLoader;
import com.yf107.teamwork.lostandfound.model.ThingDetailModel;
import com.yf107.teamwork.lostandfound.network.AllURI;
import com.yf107.teamwork.lostandfound.presenter.ThingDetailPresenter;
import com.yf107.teamwork.lostandfound.services.ActivityManager;
import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.view.interfaces.IThingDetailActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.yf107.teamwork.lostandfound.R;
import com.yf107.teamwork.lostandfound.bean.CommentFeedBack;
import com.yf107.teamwork.lostandfound.bean.ThingDetailBean;
import com.yf107.teamwork.lostandfound.presenter.ReturnPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.yf107.teamwork.lostandfound.view.activity.SignInActivity.SESSION;


/**
 * Description: 失物招领详情页面
 */
public class ThingDetailActivity extends AppCompatActivity implements IThingDetailActivity {

    public static final String OTHERSNICKNAME = "OTHERSNICKNAME";
    public static final String OTHERSPHOTO = "OTHERSPHOTO";
    public static final String OTHERSFABIAODATE = "OTHERSFABIAODATE";
    public static final String OTHERSPLACE = "OTHERSPLACE";
    public static final String OTHERSDIUSHILEIXING = "OTHERSDIUSHILEIXING";
    public static final String OTHERSDIUSHIDATE = "OTHERSDIUSHIDATE";
    public static final String OTHERSTHINGSTYPE = "OTHERSTHINGSTYPE";
    public static final String OTHERSIMGS = "OTHERSIMGS";
    public static final String OTHERSID = "OTHERSID";
    public static final String OTHERSDESC = "OTHERSDESC";
    public static final String OTHERSTITLE = "OTHERSTITLE";

    //头像
    @BindView(R.id.thing_detail_thingsdetail_circleview)
    CircleImageView userimg;

    //nickname
    @BindView(R.id.thing_detail_thingsdetail_nickname)
    TextView nickname;

    //发表时间
    @BindView(R.id.thing_detail_thingsdetail_fabiaodate)
    TextView fabiaodate;

    //丢失时间
    @BindView(R.id.thing_detail_thingsdetail_diushidate)
    TextView diushidate;

    //丢失地点
    @BindView(R.id.thing_detail_thingsdetail_place)
    TextView place;

    //失物类型
    @BindView(R.id.thing_detail_thingsdetail_thingstype)
    ImageView thingstype;

    //描述
    @BindView(R.id.thing_detail_thingsdetail_describe)
    TextView describe;

    //评论列表
    @BindView(R.id.thing_detail_comment_recyclerview)
    RecyclerView recyclerView;

    //启示类型
    @BindView(R.id.thing_detail_type)
    TextView type;

    //返回按键
    @BindView(R.id.thing_detail_back)
    ImageView back;

//    //启事图片
//    @BindView(R.id.thing_detail_viewpager)
//    ViewPager viewPager;

    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.thing_detail_usercard)
    CardView usercard;

    //点击评论按键
    @BindView(R.id.comment)
    TextView clicktocomment;

    //确认栏
    @BindView(R.id.tv_return)
    TextView clickreturn;

    @BindView(R.id.thing_detail_thingsdetail_name)
    TextView name;

    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;


    private String imgs = "";
    private int lostid;
    private int intthingstype;
    private SharedPreferences sharedPreferences;
    private ThingDetailPresenter thingDetailPresenter;
    private ReturnPresenter returnPresenter;
    private PopupWindow mPopupWindow;

    private ViewPager activity_main_viewpager;
    private LinearLayout activity_main_llpoints;
    private List<ImageView> imageViewList;;
    private int viewPagerLastIndex;
    private List<CommentFeedBack.Comments> mCommentList  =new ArrayList<>();
    private ThingDetailAdapter adapter;
    private int id;
    private String jsession;
    private View statusBarView;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detail);
        ButterKnife.bind(this);
        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
        ActivityManager.getActivityManager().add(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        thingDetailPresenter = new ThingDetailPresenter(this,new ThingDetailModel());
        imgs=initDataFromLocal();
        Log.e("IMGS",imgs);
        String[] a = imgs.split(",");
//        initViewPager(Arrays.asList(a));
        initBanner(Arrays.asList(a));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ThingDetailAdapter(mCommentList,this);
        recyclerView.setAdapter(adapter);
        thingDetailPresenter.getDataFromWeb(sharedPreferences.getString("SESSION",null),lostid);
        clicktocomment.setOnClickListener(v-> popup());
        returnPresenter = new ReturnPresenter();
        returnPresenter.attachActivity((IThingDetailActivity) this);

    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        thingDetailPresenter.dettachActivity();
        returnPresenter.dettachActivity();
        super.onDestroy();
    }

    private void initBanner(List<String> urlList){
        List<String> s = new ArrayList<>();
        if("".equals(imgs)){
            s.add(AllURI.getTypePhoto(sharedPreferences.getString(SESSION, "null"), AllURI.allTypeImgsList.get(intthingstype - 1)));
            banner.setImages(s)
                    .setImageLoader(new WebImageLoader());
        }else {
            for (String e :
                    urlList) {
                s.add(AllURI.getLostThingsPhoto(sharedPreferences.getString(SESSION, "null"), e));
            }
            banner.setImages(s)
                    .setImageLoader(new WebImageLoader());
        }
        banner.start();
        banner.startAutoPlay();
        //Banner加载图片
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Dialog dialog = new Dialog(ThingDetailActivity.this,R.style.Dialog_Fullscreen);
                dialog.setContentView(R.layout.dialog_pic);
                ImageView image = dialog.findViewById(R.id.dialog_pic);
                Glide.with(dialog.getContext())
                        .load(s.get(position))
                        .into(image);
                dialog.show();
                dialog.setCancelable(true);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

//    //初始化viewpager
//    private void initViewPager(List<String> urlList) {
//        Log.e("ThingsDetail", urlList.toString());
//        imageViewList = new ArrayList<>();
//
//        View point = null;
//        LinearLayout.LayoutParams params = null;
//        //如果长度为0，加入默认图片
//        if (urlList.size() != 0) {
//            for (String s :
//                    urlList) {
//                ImageView imageView = new ImageView(this);
//                Glide.with(this)
//                        .load(getLostThingsPhoto(sharedPreferences.getString(SESSION, "null"), s))
//                        .asBitmap()
//                        .into(imageView);
//                imageViewList.add(imageView);
//            }
//        } else {
//            ImageView imageView = new ImageView(this);
//            Glide.with(this)
//                    .load(getTypePhoto(sharedPreferences.getString(SESSION, "null"), allTypeImgsList.get(intthingstype - 1)))
//                    .asBitmap()
//                    .into(imageView);
//            imageViewList.add(imageView);
//        }
//
//
//            viewPager.setAdapter(new MyViewPagerAdapter(imageViewList));
//            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    if (position == imageViewList.size()) {
//                        viewPager.setCurrentItem(0);
//                    }
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    if (position == imageViewList.size()) {
//                        viewPager.setCurrentItem(0);
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
//        }

    //初始化控件
    private String initDataFromLocal() {
        Intent intent = getIntent();
        /**
         * intent.putExtra(OTHERSIMG, searchItemBean.getPhoto());
         intent.putExtra(OTHERSFABIAODATE,searchItemBean.getFabiaodate());
         intent.putExtra(OTHERSDIUSHILEIXING, searchItemBean.getQishileixing());
         intent.putExtra(OTHERSDIUSHIDATE, searchItemBean.getDiushidate());
         intent.putExtra(OTHERSTHINGSTYPES, searchItemBean.getTypes());
         intent.putExtra(OTHERSID, searchItemBean.getID());
         */
        String strusernickname = intent.getStringExtra(OTHERSNICKNAME);
        String struserphoto = intent.getStringExtra(OTHERSPHOTO);
        String strfabiaodate = intent.getStringExtra(OTHERSFABIAODATE);
        String strplace = intent.getStringExtra(OTHERSPLACE);
        String strdiushidate = intent.getStringExtra(OTHERSDIUSHIDATE);
        String strdesc = intent.getStringExtra(OTHERSDESC);
        String title = intent.getStringExtra(OTHERSTITLE);
        intthingstype = intent.getIntExtra(OTHERSTHINGSTYPE,1);
        String strThingsImgs = intent.getStringExtra(OTHERSIMGS);
        lostid = intent.getIntExtra(OTHERSID,-1);
        int qishileixing = intent.getIntExtra(OTHERSDIUSHILEIXING,1);

        if(qishileixing==0){
            type.setText("失物详情");
        }else {
            type.setText("寻物详情");
        }
//        GetImageFromWeb.glideSetImageView(AllURI.getTypeLittlePhoto(getSharedPreferences("users",MODE_PRIVATE).getString("SESSION",null),
//                AllURI.allTypeImgsList.get(intthingstype-1)),thingstype,this);
        describe.setText(strdesc);
        nickname.setText(strusernickname);
        GetImageFromWeb.httpSetImageView(AllURI.getUserPhoto(getSharedPreferences("users",MODE_PRIVATE).getString("SESSION",null),
                struserphoto),userimg,this);
//        GetImageFromWeb.glideSetImageView(AllURI.getUserPhoto(getSharedPreferences("users",MODE_PRIVATE).getString("SESSION",null),
//                struserphoto),userimg,this);
        fabiaodate.setText("发表于"+strfabiaodate);
        diushidate.setText(strdiushidate);
        place.setText(strplace);
        name.setText(title);
        return  strThingsImgs;
    }

    @OnClick({R.id.thing_detail_back,R.id.thing_detail_usercard,R.id.tv_return})
    void onClick(View view){
        switch (view.getId()){
            case R.id.thing_detail_back:{
                finish();
                break;
            }
            case R.id.thing_detail_usercard:{
                popup();
                break;
            }
            case R.id.tv_return:{
                Intent intent = getIntent();
                id= intent.getIntExtra(OTHERSID,0);
                jsession = sharedPreferences.getString(SESSION, "null");
                returnPresenter.sendMessage(jsession,id);
            }
            default:{
                break;
            }
        }
    }


    //点击评论区内容时 弹出输入框
    public void initPop(){
        mPopupWindow = new PopupWindow(this);
        View view  = LayoutInflater.from(this).inflate(R.layout.comment_input_pop,null);
        EditText commentInput = view.findViewById(R.id.comment_input);
        TextView    ok = view.findViewById(R.id.comment_ok);
        mPopupWindow.setContentView(view);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        ok.setOnClickListener(v->{
            if (!commentInput.getText().toString().equals("")){
                String s = commentInput.getText().toString();
                Log.d("10086",s);
                Log.d("10086","lostid="+lostid);
                ThingDetailBean.Comment newComment = new ThingDetailBean.Comment();
                newComment.setId(null);
                newComment.setTime(null);
                newComment.setContent(s);
                thingDetailPresenter.sendDataToWeb(sharedPreferences.getString("SESSION",null),null,lostid,null,s);
                thingDetailPresenter.getDataFromWeb(sharedPreferences.getString("SESSION",null),lostid);
                commentInput.setText("");
            }else{
                Toast.makeText(ThingDetailActivity.this,"没有输入内容哦",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popup(){
        if (mPopupWindow==null){
            initPop();
        }
        mPopupWindow.showAtLocation(userimg,Gravity.BOTTOM,0,0);
    }

    @Override
    public void sendDataToWeb(int status) {
        if (status==200){
            Toast.makeText(ThingDetailActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
            //刷新列表
            thingDetailPresenter.getDataFromWeb(sharedPreferences.getString("SESSION",null),lostid);
        }else if(status==400){
            Toast.makeText(ThingDetailActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getDataFromWeb(List<CommentFeedBack.Comments> list) {
        if(list!=null){
      //      if(list.size()!=0){
     //           clicktocomment.setVisibility(View.GONE);
      //      }
            mCommentList.clear();
            this.mCommentList.addAll(list);
            adapter.notifyDataSetChanged();
            Log.d("commentfuck",""+list.toString());
        }

    }
    @Override
    public void showStatus(Boolean status) {
        if (status) {
            finish();
            FancyToast.makeText(ThingDetailActivity.this, "成功", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();
        } else {
            FancyToast.makeText(ThingDetailActivity.this, "出现了错误", FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ThingDetailActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

