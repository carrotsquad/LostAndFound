package com.zhangqianyuan.teamwork.lostandfound.bean;

public class SignInBean {

    private Integer status;
    private String JSESSIONID;
    private User user;

    public String getJSESSIONID() {
        return JSESSIONID;
    }

    public Integer getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setJSESSIONID(String JSESSIONID) {
        this.JSESSIONID = JSESSIONID;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public class User {
        private String username;
        private String password;
        private String nickname;
        private String snumber;
        private String photo;
        private String phonenumber;

        public String getNickname() {
            return nickname;
        }

        public String getStu() {
            return snumber;
        }

        public String getPassword() {
            return password;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public String getPhoto() {
            return photo;
        }

        public String getUsername() {
            return username;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setStu(String sunmber) {
            this.snumber = snumber;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
