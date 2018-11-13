package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqianyuan.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Description: 失物招领详情页面
 * Created at: 2018/11/10 21:19
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class ThingDetailActivity extends AppCompatActivity {

    public static final String OTHERSNICKNAME = "OTHERSNICKNAME";
    public static final String OTHERSIMG = "OTHERSIMG";
    public static final String OTHERSFABIAODATE = "OTHERSFABIAODATE";
    public static final String OTHERSPLACE = "OTHERSPLACE";
    public static final String OTHERSDIUSHILEIXING = "OTHERSDIUSHILEIXING";
    public static final String OTHERSDIUSHIDATE = "OTHERSDIUSHIDATE";
    public static final String OTHERSTHINGSTYPES = "OTHERSTHINGSTYPES";
    public static final String OTHERSID = "OTHERSID";

    @BindView(R.id.thing_detail_thingsdetail_circleview)
    CircleImageView userimg;

    @BindView(R.id.thing_detail_thingsdetail_nickname)
    TextView nickname;

    @BindView(R.id.thing_detail_thingsdetail_fabiaodate)
    TextView fabiaodate;

    @BindView(R.id.thing_detail_thingsdetail_diushidate)
    TextView diushidate;

    @BindView(R.id.thing_detail_thingsdetail_place)
    TextView place;

    @BindView(R.id.thing_detail_thingsdetail_describe)
    TextView describe;

    @BindView(R.id.thing_detail_comment_recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.thing_detail_type)
    TextView type;

    @BindView(R.id.thing_detail_back)
    ImageView back;

    @BindView(R.id.thing_detail_thingsdetail_types)
    HorizontalListView thingstypes;

    @BindView(R.id.thing_detail_thingsdetail_imgs)
    HorizontalListView imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thing_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

    }
}
