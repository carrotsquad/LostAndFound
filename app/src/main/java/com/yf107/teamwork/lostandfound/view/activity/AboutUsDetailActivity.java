package com.yf107.teamwork.lostandfound.view.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yf107.teamwork.lostandfound.utils.StatusBarUtil;
import com.yf107.teamwork.lostandfound.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsDetailActivity extends AppCompatActivity {

    @BindView(R.id.aboutus_detail_title)
    TextView  title;

    @BindView(R.id.aboutus_info)
    TextView  info;

    @BindView(R.id.aboutus_1)
    TextView  one;

    @BindView(R.id.aboutus_detail_back)
    ImageView  back;
    private View statusBarView;

    @BindView(R.id.setting_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置返回键
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }
        one.setText("免责声明");
       info.setText(
                "  1. “递爱”中的个人信息、照片图片、资料的版权归发表者所有，若发表者有版权声明的或从其它网站转载而附带有原所有站的版权声明者，其版权归属以附带声明为准。\n" +
                "\n  2.由于用户将个人密码告知他人或与他人共享注册帐户，由此导致的任何个人资料泄露, “递爱”不负任何责任。\n" +
                "\n  3.当政府司法机关依照法定程序要求“递爱”披露个人资料时，我们将根据执法单位之要求或为公共安全之目的提供个人资料。在此情况下之任何披露，“递爱”均得免责。\n" +
                "\n  4.凡以任何方式登陆“递爱”或直接、间接使用“递爱”资料者，视为自愿接受“递爱”声明的约束。\n" +
                "\n  5.由于与“递爱”链接的其它网站所造成之个人资料泄露及由此而导致的任何法律争议和后果,“递爱”平台均得免责。\n" +
                "\n  6.“递爱”如因系统维护或升级而需暂停服务时，将事先公告。若因线路及非本公司控制范围外的硬件故障或其它不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，“递爱”不负任何责任。\n" +
                "\n  7.任何由于黑客攻击、计算机病毒侵入或发作、因政府管制而造成的暂时性关闭等影响网络正常经营的不可抗力而造成的个人资料泄露、丢失、被盗用或被窜改等，“递爱”均得免责。\n" +
                "\n  8.“递爱”使用者因为违反本声明的规定而触犯中华人民共和国法律的，一切后果自己负责，“递爱”不承担任何责任。\n" +
                " \n9.本声明未涉及的问题参见国家有关法律法规,当本声明");

       back.setOnClickListener(v->finish());

        //实现渐变式状态栏
        StatusBarUtil.setGradientStatusBarColor(this,statusBarView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
