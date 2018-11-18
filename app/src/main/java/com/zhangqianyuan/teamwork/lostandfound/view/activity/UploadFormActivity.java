package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.zhangqianyuan.teamwork.lostandfound.R;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static cn.finalteam.toolsfinal.DateUtils.getTime;

/**
 * Description
 * 点击上传界面后用户填写表单的界面
 * @author  zhou
 */
public class UploadFormActivity extends AppCompatActivity {

    @BindView(R.id.upload_lostorfind_back)
    Button btnBack;

    @BindView(R.id.upload_lostorfind_qishitype)
    TextView qishiType;

    @BindView(R.id.upload_lostorfind_time)
    TextView timeText;

    @BindView(R.id.upload_lostorfind_where)
    Spinner where;

    @BindView(R.id.upload_lostorfind_bounty)
    Spinner bounty;

    @BindView(R.id.upload_lostorfind_description_title)
    EditText titleEdit;

    @BindView(R.id.upload_lostorfind_description_text)
    EditText descEdit;

    @BindView(R.id.upload_lostorfind_description_img)
    ImageView img;

    @BindView(R.id.upload_lostorfind_description_upload)
    TextView upload;

    @BindView(R.id.upload_lostorfind_sure)
    Button btnSure;

    private TimePickerView pvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_form);
        ButterKnife.bind(this);
    }

    private void initView(){
        //时间选择器
        pvTime = new TimePickerBuilder(UploadFormActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(UploadFormActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            }
        }).build();

        pvTime.onClick(timeText);
    }

}
