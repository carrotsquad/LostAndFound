package com.zhangqianyuan.teamwork.lostandfound.view.interfaces;

import com.zhangqianyuan.teamwork.lostandfound.bean.DynamicItemBean;

import java.util.List;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public interface IDynaicFragment extends BaseView {
    void  showData(Boolean status, List<DynamicItemBean> searchItemBeanArrayList);
}
