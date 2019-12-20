package com.yf107.teamwork.lostandfound.presenter;

import com.yf107.teamwork.lostandfound.bean.DynamicItemBean;
import com.yf107.teamwork.lostandfound.bean.StatusBean;
import com.yf107.teamwork.lostandfound.bean.UpDateMessageBean;
import com.yf107.teamwork.lostandfound.model.CommentedMessageModel;
import com.yf107.teamwork.lostandfound.presenter.interfaces.IMessagePresenter;
import com.yf107.teamwork.lostandfound.view.interfaces.IMessageFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 消息Presenter
 */
public class MessagePresenter extends AbstractBasePresenter<IMessageFragment> implements IMessagePresenter {

    private CommentedMessageModel commentedMessageModel;
    //private MyLoadModel myLoadModel;


    public MessagePresenter(IMessageFragment iMessageFragment) {
        super(iMessageFragment);
    }

    @Override
    public void getMessageData(String usernickname, String userphoto, String username, String jsessionid, int i1, int i) {
        if(isAttachActivity()) {
            commentedMessageModel = new CommentedMessageModel();
            commentedMessageModel.getMyCommentedData(jsessionid, new Observer<UpDateMessageBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(UpDateMessageBean upDateMessageBean) {

                    if (upDateMessageBean.getStatus() == 200 && upDateMessageBean != null) {
                        List<UpDateMessageBean.DynamicsBeanX> dynamicItemBeanList = new ArrayList<>();
                        dynamicItemBeanList.addAll(upDateMessageBean.getDynamics());
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
    public void postdelet(int id, String jsessionid) {
        commentedMessageModel.postdelete(jsessionid, id, new Observer<StatusBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatusBean statusBean) {

                if(statusBean.getStatus() == 200){
                    v.showStatus(true);
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

    @Override
    public void updateIsRead(String session, int lostid) {
        commentedMessageModel = new CommentedMessageModel();
        commentedMessageModel.getupdateIsRead(session, lostid, new Observer<StatusBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(StatusBean statusBean) {

                if(statusBean.getStatus() == 200){
                    v.isRead(true);
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


//    @Override
//    public void getMessageData(String nickname, String userphoto,String username,String jsessionid, int start, int end) {
//        commentedMessageModel = new CommentedMessageModel();
//        myLoadModel = new MyLoadModel();
//        myLoadModel.getMyLoadData(jsessionid, start, end, new Callback<MyHistoryItem>() {
//            @Override
//            public void onResponse(Call<MyHistoryItem> call, Response<MyHistoryItem> response) {
//                if(response.body()==null||response.body().getStatus()==BAD_INTERNET_STATUS){
//                    v.onDataBack(false,null);
//                }else {
//                    commentedMessageModel.getMyCommentedData(jsessionid, new Observer<SearchBean>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//
//                        }
//
//                        @Override
//                        public void onNext(SearchBean searchBean) {
//                            List<DynamicItemBean> dynamicItemBeanList = new ArrayList<>();
//                            for (TheLostBean bean :
//                                    response.body().getData()) {
//                                DynamicItemBean dynamicItemBean = new DynamicItemBean();
//                                dynamicItemBean.setNickname(nickname);
//                                dynamicItemBean.setUsername(username);
//                                dynamicItemBean.setUserphoto(userphoto);
//                                dynamicItemBean.setThelost(bean);
//                                dynamicItemBeanList.add(dynamicItemBean);
//                            }
//                            Log.e("MessageData",dynamicItemBeanList.toString());
//                            //当失败请求自己评论过的数据失败时，进返回自己发表的
//                            if(searchBean==null|| searchBean.getStatus().equals(BAD_INTERNET_STATUS)){
//                                v.onDataBack(true, dynamicItemBeanList);
//                            }else {
//                                dynamicItemBeanList.addAll(searchBean.getDynamics());
//                                v.onDataBack(true, dynamicItemBeanList);
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MyHistoryItem> call, Throwable t) {
//
//            }
//        });
//
//    }


}
