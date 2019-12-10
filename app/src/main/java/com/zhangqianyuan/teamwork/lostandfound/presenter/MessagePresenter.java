package com.zhangqianyuan.teamwork.lostandfound.presenter;

import android.util.Log;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.MyHistoryItem;
import com.zhangqianyuan.teamwork.lostandfound.bean.SearchBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;
import com.zhangqianyuan.teamwork.lostandfound.model.CommentedMessageModel;
import com.zhangqianyuan.teamwork.lostandfound.model.IsReadModel;
import com.zhangqianyuan.teamwork.lostandfound.model.MyLoadModel;
import com.zhangqianyuan.teamwork.lostandfound.presenter.interfaces.IMessagePresenter;
import com.zhangqianyuan.teamwork.lostandfound.view.interfaces.IMessageFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zhangqianyuan.teamwork.lostandfound.view.activity.MainActivity.BAD_INTERNET_STATUS;

/**
 * Description: 消息Presenter
 * Created at: 2018/11/24 16:17
 * @author: zhangqianyuan
 * Email: zhang.qianyuan@foxmail.com
 */
public class MessagePresenter extends AbstractBasePresenter<IMessageFragment> implements IMessagePresenter {

    private CommentedMessageModel commentedMessageModel;
    private MyLoadModel myLoadModel;

    private IsReadModel isReadModel;

    public MessagePresenter(IMessageFragment iMessageFragment) {
        super(iMessageFragment);
    }

    @Override
    public void getMessageData(String nickname, String userphoto,String username,String jsessionid, int start, int end) {
        commentedMessageModel = new CommentedMessageModel();
        myLoadModel = new MyLoadModel();
        myLoadModel.getMyLoadData(jsessionid, start, end, new Callback<MyHistoryItem>() {
            @Override
            public void onResponse(Call<MyHistoryItem> call, Response<MyHistoryItem> response) {
                if(response.body()==null||response.body().getStatus()==BAD_INTERNET_STATUS){
                    v.onDataBack(false,null);
                }else {
                    commentedMessageModel.getMyCommentedData(jsessionid, new Observer<SearchBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(SearchBean searchBean) {
                            List<DynamicItemBean> dynamicItemBeanList = new ArrayList<>();
                            for (TheLostBean bean :
                                    response.body().getData()) {
                                DynamicItemBean dynamicItemBean = new DynamicItemBean();
                                dynamicItemBean.setNickname(nickname);
                                dynamicItemBean.setUsername(username);
                                dynamicItemBean.setUserphoto(userphoto);
                                dynamicItemBean.setThelost(bean);
                                dynamicItemBeanList.add(dynamicItemBean);
                            }
                            Log.e("MessageData",dynamicItemBeanList.toString());
                            //当失败请求自己评论过的数据失败时，进返回自己发表的
                            if(searchBean==null|| searchBean.getStatus().equals(BAD_INTERNET_STATUS)){
                                v.onDataBack(true, dynamicItemBeanList);
                            }else {
                                dynamicItemBeanList.addAll(searchBean.getDynamics());
                                v.onDataBack(true, dynamicItemBeanList);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MyHistoryItem> call, Throwable t) {

            }
        });

    }


}
