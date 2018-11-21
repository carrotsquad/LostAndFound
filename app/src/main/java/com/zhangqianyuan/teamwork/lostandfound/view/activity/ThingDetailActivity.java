package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.image.GetImageFromWeb;
import com.zhangqianyuan.teamwork.lostandfound.model.ThingDetailModel;
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

    private int  lostid;      //动态item  lostid
    private SharedPreferences sharedPreferences;
    private ThingDetailPresenter thingDetailPresenter;
    private PopupWindow mPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detail);
        ButterKnife.bind(this);
        thingDetailPresenter = new ThingDetailPresenter(this,new ThingDetailModel());
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
     //   thingDetailPresenter = new ThingDetailPresenter(this);
        initDataFromLocal();
        userimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
            }
        });

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
        lostid = intent.getIntExtra(OTHERSID,0);
        Log.d("10086","lostid22="+lostid);

        nickname.setText(strusernickname);
        GetImageFromWeb.glideSetImageView(struserphoto,userimg,this);
        fabiaodate.setText("发表于"+strfabiaodate);
        diushidate.setText(strdiushidate);
        place.setText(strplace);
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
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!commentInput.getText().toString().equals("")){
                    String s = commentInput.getText().toString();
                    Log.d("10086",s);
                    Log.d("10086","lostid="+lostid);
                    thingDetailPresenter.getDataFromWeb(sharedPreferences.getString("SESSION",null),null,lostid,null,s);
                    commentInput.setText("");
                }else{
                    Toast.makeText(ThingDetailActivity.this,"没有输入内容哦",Toast.LENGTH_SHORT).show();
                }
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
    public void showDataFromWeb(int status) {
        if (status==200){
            Toast.makeText(ThingDetailActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
        }else if(status==400){
            Toast.makeText(ThingDetailActivity.this,"发送失败",Toast.LENGTH_SHORT).show();
        }
    }
}
