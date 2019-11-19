package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.TheLostBean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IMyLoadActivity extends BaseView {
    void showData(List<TheLostBean> bean);
    void showStatus(Boolean seesion);
}
