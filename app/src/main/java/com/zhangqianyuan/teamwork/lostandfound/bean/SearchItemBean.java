package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.ArrayList;

public class SearchItemBean {
    
    private String ID;
    private String photo;
    private String qishileixing;
    private Boolean isNew;
    private String title;
    private String fabiaodate;
    private String diushidate;
    private String place;
    private ArrayList<String> types;

    public void setFabiaodate(String fabiaodate) {
        this.fabiaodate = fabiaodate;
    }

    public String getDiushidate() {
        return diushidate;
    }

    public String getFabiaodate() {
        return fabiaodate;
    }

    public void setDiushidate(String diushidate) {
        this.diushidate = diushidate;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

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


    public String getPhoto() {
        return photo;
    }

    public String getPlace() {
        return place;
    }

    public String getTitle() {
        return title;
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
