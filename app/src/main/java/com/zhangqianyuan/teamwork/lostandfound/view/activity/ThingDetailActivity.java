package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.MyViewPagerAdapter;
import com.zhangqianyuan.teamwork.lostandfound.adapter.NetworkImageIndicatorView;
import com.zhangqianyuan.teamwork.lostandfound.image.GetImageFromWeb;
import com.zhangqianyuan.teamwork.lostandfound.model.ThingDetailModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.ThingDetailPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.getLostThingsPhoto;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;


/**
 * Description: 失物招领详情页面
 * Created at: 2018/11/10 21:19
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/11/13 需要完善添加评论
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

    @BindView(R.id.thing_detail_viewpager)
    ViewPager viewPager;

    IndefinitePagerIndicator indicator;


    private String ID;
    private SharedPreferences sharedPreferences;
    private ThingDetailPresenter thingDetailPresenter;
    ///////////////////////
//    private List<ViewPagerAltBean> viewPagerAltBeanList;
    private ViewPager activity_main_viewpager;
    private LinearLayout activity_main_llpoints;
    private List<ImageView> imageViewList;;
    private int viewPagerLastIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        thingDetailPresenter = new ThingDetailPresenter(this, new ThingDetailModel());
        indicator = (IndefinitePagerIndicator)findViewById(R.id.thing_detail_viewpager_indicator);
        String imgs=initDataFromLocal();
        String[] a = imgs.split(",");
        initViewPager(Arrays.asList(a));
//        initDataFromWeb();
    }

    @Override
    protected void onDestroy() {
        thingDetailPresenter.dettachActivity();
        super.onDestroy();
    }

    //初始化viewpager
    private void initViewPager(List<String> urlList){
        Log.e("ThingsDetail",urlList.toString());
        imageViewList = new ArrayList<>();
        ImageView imageView=null;
        View point=null;
        LinearLayout.LayoutParams params=null;
        for (String s :
                urlList) {
            Log.e("ThingsDetail",s);
            imageView = new ImageView(this);
            Glide.with(ThingDetailActivity.this)
                    .load(getLostThingsPhoto(sharedPreferences.getString(SESSION,"null"),s))
                    .asBitmap()
                    .into(imageView);
            imageViewList.add(imageView);
        }
        viewPager.setAdapter(new MyViewPagerAdapter(imageViewList));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==imageViewList.size()){
                    viewPager.setCurrentItem(0);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position==imageViewList.size()){
                    viewPager.setCurrentItem(0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//        });
//        indicator.attachToViewPager(viewPager);
    }

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
        String strThingsImgs = intent.getStringExtra(OTHERSIMGS);
        ID = intent.getStringExtra(OTHERSID);
        int qishileixing = intent.getIntExtra(OTHERSDIUSHILEIXING,1);

        if(qishileixing==0){
            type.setText("失物详情");
        }else {
            type.setText("寻物详情");
        }
        describe.setText(strdesc);
        nickname.setText(strusernickname);
        GetImageFromWeb.glideSetImageView(struserphoto,userimg,this);
        fabiaodate.setText("发表于"+strfabiaodate);
        diushidate.setText(strdiushidate);
        place.setText(strplace);
        return  strThingsImgs;
    }

    @OnClick({R.id.thing_detail_back})
    void onClick(View view){
        switch (view.getId()){
            case R.id.thing_detail_back:{
                finish();
                break;
            }
            default:{
                break;
            }
        }
    }

    /**
     * 从网络获取数据
     */
    private void initDataFromWeb(){
        String session=sharedPreferences.getString(SESSION,"nought");
    }

    @Override
    public void showDataFromWeb(int status) {

    }
}
