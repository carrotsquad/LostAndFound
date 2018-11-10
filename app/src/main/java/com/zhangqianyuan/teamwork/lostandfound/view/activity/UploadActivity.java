package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhangqianyuan.teamwork.lostandfound.R;
import com.zhangqianyuan.teamwork.lostandfound.adapter.UploadFragmentAdapter;
import com.zhangqianyuan.teamwork.lostandfound.bean.UploadItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description
 * 上传失物/招领启事界面
 * @author zhou
 */
public class UploadActivity extends AppCompatActivity {
  private List<UploadItemBean> lists = new ArrayList<>();

  @BindView(R.id.upload_list)
   RecyclerView mRecyclerView;

  @BindView(R.id.upload_toolbar)
    Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initLists();
        initView();
    }

    public void initView(){
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(new UploadFragmentAdapter(lists));
    }

    public void initLists(){
        lists.add(new UploadItemBean(R.drawable.ic_umbrella,"雨伞"));
        lists.add(new UploadItemBean(R.drawable.ic_wallet,"钱包"));
        lists.add(new UploadItemBean(R.drawable.ic_phone,"手机"));
        lists.add(new UploadItemBean(R.drawable.ic_key,"钥匙"));
        lists.add(new UploadItemBean(R.drawable.ic_book,"书籍"));
        lists.add(new UploadItemBean(R.drawable.ic_identifycard,"身份证"));
        lists.add(new UploadItemBean(R.drawable.ic_card,"卡"));
        lists.add(new UploadItemBean(R.drawable.ic_anythingelse,"其他"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.upload_lost:
                /*
                切换为失物界面
                如果以及是失物界面
                */
                if (item.isChecked())
                {
                    Toast.makeText(UploadActivity.this,"已经是失物启事页面了",Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                break;

            case  R.id.upload_find:
                /*
                 同上
                 */
                if (item.isChecked()){
                    Toast.makeText(UploadActivity.this,"已经是招领启事页面了",Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                 break;
        }
        return  true;
    }

}
