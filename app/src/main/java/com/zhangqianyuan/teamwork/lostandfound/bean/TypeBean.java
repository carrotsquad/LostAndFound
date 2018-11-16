package com.zhangqianyuan.teamwork.lostandfound.bean;

public class TypeBean {

    private int id;
    private String name;
    private String photo;

    @Override
    public String toString() {
        return String.format("Type[id=%s,name=%s,photo=%s]",id, name, photo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
