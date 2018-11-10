package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * Description 动态Fragment 中的每个事件的类
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DynamicItemBean {
    public static final int  TYPE_LOST=0; //丢失
    public static final int  TYPE_FIND=1; //招领
    private int cardType;
    private int headImg;  //用户头像
    private String neckName;  //用户呢称
    private String thingType; //物件的类型
    private String time;      //捡到/丢失时间
    private String description; //用户对这个东西的描述

    public DynamicItemBean(int headImg, String neckName, String thingType, String time, String where, String description, int cardType) {
        this.headImg = headImg;
        this.neckName = neckName;
        this.thingType = thingType;
        this.time = time;
        this.description = description;
        this.cardType=cardType;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int headImg) {
        this.headImg = headImg;
    }

    public String getNeckName() {
        return neckName;
    }

    public void setNeckName(String neckName) {
        this.neckName = neckName;
    }

    public String getThingType() {
        return thingType;
    }

    public void setThingType(String thingType) {
        this.thingType = thingType;
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
