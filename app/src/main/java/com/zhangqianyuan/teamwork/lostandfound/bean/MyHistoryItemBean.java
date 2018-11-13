package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 我的板块中 我的历史界面Item
 * 包括 类型图象  类型（丢捡）  是否成功  对方是谁以及 完成时间
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
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
