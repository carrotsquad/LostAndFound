package com.zhangqianyuan.teamwork.lostandfound.bean;

import java.util.List;

/**
 * Description
 * 发布失物/招领 的bean 并非recycleview bean
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UploadBean {
    private   int  lostOrFind;   //事务类型
    private   int  thingType;    //物品类型
    private   String  byWho;      //发布人
    private   String  uploadTime; //发布时时间
    private   String  happendTime;   //事件发生时间
    private   String  where;         //发生地点
    private   String  description;   //用户描述
    private  List<Integer>     thingImg;  //物品照片

    public int getLostOrFind() {
        return lostOrFind;
    }

    public void setLostOrFind(int lostOrFind) {
        this.lostOrFind = lostOrFind;
    }

    public int getThingType() {
        return thingType;
    }

    public void setThingType(int thingType) {
        this.thingType = thingType;
    }

    public String getByWho() {
        return byWho;
    }

    public void setByWho(String byWho) {
        this.byWho = byWho;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getHappendTime() {
        return happendTime;
    }

    public void setHappendTime(String happendTime) {
        this.happendTime = happendTime;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getThingImg() {
        return thingImg;
    }

    public void setThingImg(List<Integer> thingImg) {
        this.thingImg = thingImg;
    }
}
