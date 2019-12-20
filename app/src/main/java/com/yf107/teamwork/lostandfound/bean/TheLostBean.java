package com.yf107.teamwork.lostandfound.bean;

import java.util.List;

public class TheLostBean {


    public TheLostBean(int typeid, int losttype, String title, String description, int placeid, String publishtime, String losttime, String photo, int ishandled) {
        this.typeid = typeid;
        this.losttype = losttype;
        this.title = title;
        this.description = description;
        this.placeid = placeid;
        this.publishtime = publishtime;
        this.losttime = losttime;
        this.photo = photo;
        this.ishandled = ishandled;
    }

    /**
         * id : 1
         * typeid : 1
         * losttype : 0
         * title : 失物发布测试1
         * description : 我的钥匙丢了，呜呜呜，真的很急
         * placeid : 1
         * publishtime : 20181105173056
         * losttime : 2018110512
         * photo : 1.jpg
         * ishandled : 0
         */



        private int id;
        private int typeid;
        private int losttype;
        private String title;
        private String description;
        private int placeid;
        private String publishtime;
        private String losttime;
        private String photo;
        private int ishandled;

        public int getId() {
        return id;
    }

        public void setId(int id) {
        this.id = id;
    }

        public int getTypeid() {
        return typeid;
    }

        public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

        public int getLosttype() {
        return losttype;
    }

        public void setLosttype(int losttype) {
        this.losttype = losttype;
    }

        public String getTitle() {
        return title;
    }

        public void setTitle(String title) {
        this.title = title;
    }

        public String getDescription() {
        return description;
    }

        public void setDescription(String description) {
        this.description = description;
    }

        public int getPlaceid() {
        return placeid;
    }

        public void setPlaceid(int placeid) {
        this.placeid = placeid;
    }

        public String getPublishtime() {
        return publishtime;
    }

        public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

        public String getLosttime() {
        return losttime;
    }

        public void setLosttime(String losttime) {
        this.losttime = losttime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIshandled() {
        return ishandled;
    }

    public void setIshandled(int ishandled) {
        this.ishandled = ishandled;
    }

}
