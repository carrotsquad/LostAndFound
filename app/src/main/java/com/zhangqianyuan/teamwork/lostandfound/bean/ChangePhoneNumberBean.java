package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * api修改电话号码 参数类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ChangePhoneNumberBean {

    private RequestData  requestData;

    public static class  RequestData{
        private String JSESSIONID;
        private String phonumber;

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public String getPhonumber() {
            return phonumber;
        }

        public void setPhonumber(String phonumber) {
            this.phonumber = phonumber;
        }
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }


}
