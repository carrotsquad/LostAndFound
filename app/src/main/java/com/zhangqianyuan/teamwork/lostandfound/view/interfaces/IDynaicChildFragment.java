package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public interface IDynaicChildFragment extends BaseView {
    void  showData(Boolean status, List<DynamicItemBean> searchItemBeanArrayList);
}
