package com.zhangqianyuan.teamwork.lostandfound.bean;

public class PlaceBean {

    private int id;
    private String name;

    @Override
    public String toString() {
        return String.format("Place[id=%s,name=%s]",id, name);
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
}
