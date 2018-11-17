package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.model.EditInfoModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.EditInfoPresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IEditInfoActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description
 * 我的 编辑资料界面
 */
public class UserSettingEditInfoActivity extends AppCompatActivity implements IEditInfoActivity {

    @BindView(R.id.edit_info_neckname)
    EditText  neckname;

    @BindView(R.id.edit_info_phone)
    EditText  phone;

    @BindView(R.id.edit_info_ok)
    ImageButton ok;

    private EditInfoPresenter mEditInfoPresenter;
    private String  name;
    private String  phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting_edit_info);
        initData();
        initView();
    }

    public void  initView(){
        ButterKnife.bind(this);
    }

    public void initData(){
        mEditInfoPresenter= new EditInfoPresenter(new EditInfoModel());
        name= neckname.getText().toString();
          phonenumber= phone.getText().toString();

    }
    @OnClick(R.id.edit_info_ok)
    public void onClick(View view){
       // mEditInfoPresenter.uoloadPhoneNumber();
       // mEditInfoPresenter.uploadNeckName();
    }

    @Override
    public void onSuccess(int status) {
        if (status==200){
            Toast.makeText(UserSettingEditInfoActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
        }else if (status==400){
            Toast.makeText(UserSettingEditInfoActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }


}
