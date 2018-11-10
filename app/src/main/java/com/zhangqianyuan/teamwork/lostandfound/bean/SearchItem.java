package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.ArrayList;

public class SearchItem {
    private String photo;
    private String qishileixing;
    private Boolean isNew;
    private String title;
    private String date;
    private String place;
    private ArrayList<String> types;

    public String getQishileixing() {
        return qishileixing;
    }

    public void setQishileixing(String qishileixing) {
        this.qishileixing = qishileixing;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public Boolean getNew() {
        return isNew;
    }

    public String getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }

    public String getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

}
