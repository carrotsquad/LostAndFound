package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 登录 的json类
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class LoginBean {
        private String snumber;
        private String password;

        public String getStu() {
            return snumber;
        }

        public void setStu(String snumber) {
            this.snumber = snumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }



