package com.zhangqianyuan.teamwork.lostandfound.model;

import com.zhangqianyuan.teamwork.lostandfound.bean.ChangePhoneNumberBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.ChangeUserNickNameBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.StatusBean;

import retrofit2.Callback;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class EditInfoModel extends BaseModel  implements IEditInfoModel{
    public EditInfoModel(){
        super();
    }

    @Override
    public void uploadNeckName(String jsessionId, String nickName, Callback<StatusBean> callback) {
        ChangeUserNickNameBean bean = new ChangeUserNickNameBean();
        ChangeUserNickNameBean.RequestData requestData = new ChangeUserNickNameBean.RequestData();
        requestData.setJSESSIONID(jsessionId);
        requestData.setNickname(nickName);
        bean.setRequestData(requestData);
        api.uploadNickName(bean).enqueue(callback);
    }

    @Override
    public void uploadPhoneNumber(String jsessionId, String phoneNumber, Callback<StatusBean> callback) {
        ChangePhoneNumberBean bean = new ChangePhoneNumberBean();
        ChangePhoneNumberBean.RequestData requestData = new ChangePhoneNumberBean.RequestData();
        requestData.setJSESSIONID(jsessionId);
        requestData.setPhonumber(phoneNumber);
        bean.setRequestData(requestData);
        api.uploadPhoneNumber(bean).enqueue(callback);
    }


}
