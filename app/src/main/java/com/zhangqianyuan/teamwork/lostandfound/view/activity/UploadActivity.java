package com.zhangqianyuan.teamwork.lostandfound.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
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

import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allPlaceBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeBeanList;
import static com.zhangqianyuan.teamwork.lostandfound.network.AllURI.allTypeImgsList;
import static com.zhangqianyuan.teamwork.lostandfound.view.activity.SignInActivity.SESSION;

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

  private String typesImgBaseUrl = "http://111.230.235.15/passlove/img/losttype?";

  private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        sharedPreferences = getSharedPreferences("users", Context.MODE_PRIVATE);
        initLists();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        initLists();
        super.onResume();
    }

    public void initView(){
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(new UploadFragmentAdapter(lists));
    }

    public void initLists(){
        int i = allTypeImgsList.size();
        String session = sharedPreferences.getString(SESSION," ");
        for(int k = 0; k < i; k++){
            String s = "";
            s = typesImgBaseUrl + "JSESSIONID=" + session + "&" + "name=" + allTypeImgsList.get(k);
            UploadItemBean uploadItemBean = new UploadItemBean(s,allTypeBeanList.get(k));
            lists.add(uploadItemBean);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
            case R.id.upload_lost: {
                /*
                切换为失物界面
                如果以及是失物界面
                */
                if (item.isChecked()) {
                    Toast.makeText(UploadActivity.this, "已经是失物启事页面了", Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                break;
            }
            case  R.id.upload_find: {
                /*
                 同上
                 */
                if (item.isChecked()) {
                    Toast.makeText(UploadActivity.this, "已经是招领启事页面了", Toast.LENGTH_SHORT).show();
                }
                item.setChecked(true);
                break;
            }
            default:{
                break;
            }
        }
        return  true;
    }

}
