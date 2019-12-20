package com.yf107.teamwork.lostandfound.bean;

public class MyHistoryItemBean {
    private  int  typeImg;
    private  int  type;
    private  boolean  isDone;
    private  String   who;
    private  String   finishTime;

    public MyHistoryItemBean(int typeImg, int type, boolean isDone, String who, String finishTime) {
        this.typeImg = typeImg;
        this.type = type;
        this.isDone = isDone;
        this.who = who;
        this.finishTime = finishTime;
    }

    public int getTypeImg() {
        return typeImg;
    }

    public void setTypeImg(int typeImg) {
        this.typeImg = typeImg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
