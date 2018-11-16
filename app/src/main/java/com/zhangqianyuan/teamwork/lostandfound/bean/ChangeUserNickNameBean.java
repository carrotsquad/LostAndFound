package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 修改用户昵称
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ChangeUserNickNameBean {
        private  RequestData requestData;
       public static class RequestData{
            private String JSESSIONID;
            private String nickname;

            public String getJSESSIONID() {
                return JSESSIONID;
            }

            public void setJSESSIONID(String JSESSIONID) {
                this.JSESSIONID = JSESSIONID;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }

        public RequestData getRequestData() {
            return requestData;
        }

        public void setRequestData(RequestData requestData) {
            this.requestData = requestData;
        }
    }

