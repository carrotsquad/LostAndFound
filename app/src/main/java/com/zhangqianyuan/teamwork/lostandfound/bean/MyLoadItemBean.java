package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description
 * 我的中 我的发布 中事件 Item
 * @author zhoudada
 * @version $Rev$
 * @des
 * @updateAuthor $Author$
 * @updateDes
 */
public class MyLoadItemBean {
    public static final int  TYPE_LOST=0; //丢失
    public static final int  TYPE_FIND=1; //招领
    private  int typeId;                  //物品的类型图
    private  int type;                    //物品是丢失还是捡到
    private  String where;                //地点
    private  String time;                 //发布事件
    private  String description;          //事件的描述

    public int getThingImg() {
        return thingImg;
    }

    public void setThingImg(int thingImg) {
        this.thingImg = thingImg;
    }

    private  int    thingImg ;             //物品具体的图片 用于点击item后的Activity

    public MyLoadItemBean(int typeId, int type, String where, String time, String description) {
        this.typeId = typeId;
        this.type = type;
        this.where = where;
        this.time = time;
        this.description = description;
    }



    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
