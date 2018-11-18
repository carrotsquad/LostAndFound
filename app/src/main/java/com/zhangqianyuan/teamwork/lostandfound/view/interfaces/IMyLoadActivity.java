package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.MyLoadItemBean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IMyLoadActivity extends BaseView {
    void showData(List<MyLoadItemBean> bean);
}
