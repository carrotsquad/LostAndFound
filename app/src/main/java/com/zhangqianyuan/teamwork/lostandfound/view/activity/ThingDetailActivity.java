package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.image.GetImageFromWeb;
import com.zhangqianyuan.teamwork.lostandfound.presenter.ThingDetailPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IThingDetailActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Description: 失物招领详情页面
 * Created at: 2018/11/10 21:19
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
// TODO: 2018/11/13 需要完善，网络数据获取，添加评论等
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

//    //东西类型列表
//    @BindView(R.id.thing_detail_thingsdetail_types)
//    HorizontalScrollView thingstypes;

    //东西的图片
    @BindView(R.id.thing_detail_thingsdetail_imgs)
    HorizontalScrollView imgs;

    private String ID;
    private SharedPreferences sharedPreferences;
    private ThingDetailPresenter thingDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        thingDetailPresenter = new ThingDetailPresenter(this);
        initDataFromLocal();
//        initDataFromWeb();
    }

    @Override
    protected void onDestroy() {
        thingDetailPresenter.dettachActivity();
        super.onDestroy();
    }

    private void initDataFromLocal() {
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
        String strdiushileixing = intent.getStringExtra(OTHERSDIUSHILEIXING);

        Bundle bundle = intent.getBundleExtra(OTHERSIMGS);
        List<String> strThingsImgs = bundle.getStringArrayList(OTHERSIMGS);
        ID = intent.getStringExtra(OTHERSID);

        nickname.setText(strusernickname);
        GetImageFromWeb.glideSetImageView(struserphoto,userimg,this);
        fabiaodate.setText("发表于"+strfabiaodate);
        diushidate.setText("丢失时间:"+strdiushidate);
        place.setText("丢失地点:"+strplace);
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
        String session=sharedPreferences.getString("SESSION","nought");
        thingDetailPresenter.getDataFromWeb(ID, session);
    }

    // TODO: 2018/11/13 需完善
    @Override
    public void showDataFromWeb() {

    }
}
