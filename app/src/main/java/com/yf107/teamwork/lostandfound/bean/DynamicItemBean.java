package com.yf107.teamwork.lostandfound.bean;

public class DynamicItemBean {

    public static final int  TYPE_LOST=0; //丢失
    public static final int  TYPE_FIND=1;//招领

    /**
     * username : 706519033@qq.com
     * nickname : 王昊鑫
     * userphoto : 706519033@qq.com
     * thelost : {"id":1,"typeid":1,"losttype":0,"title":"失物发布测试1","description":"我的钥匙丢了，呜呜呜，真的很急","placeid":1,"publishtime":"20181105173056","losttime":"2018110512","photo":"1.jpg","ishandled":0}
     */

    private String username;
    private String nickname;
    private String userphoto;
    private TheLostBean thelost;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserphoto() {
        return userphoto;
    }

    public void setUserphoto(String userphoto) {
        this.userphoto = userphoto;
    }

    public TheLostBean getThelost() {
        return thelost;
    }

    public void setThelost(TheLostBean thelost) {
        this.thelost = thelost;
    }

    //    private int eventType;
//    private int headImg;  //用户头像
//    private String neckName;  //用户呢称
//    private String thingType; //物件的类型
//    private String time;      //捡到/丢失时间
//    private String description; //用户对这个东西的描述
//
//    public DynamicItemBean(int headImg, String neckName, String thingType, String time, String where, String description, int cardType) {
//        this.headImg = headImg;
//        this.neckName = neckName;
//        this.thingType = thingType;
//        this.time = time;
//        this.description = description;
//        this.eventType=cardType;
//    }
//
//    public int getCardType() {
//        return eventType;
//    }
//
//    public void setCardType(int cardType) {
//        this.eventType = cardType;
//    }
//
//    public int getHeadImg() {
//        return headImg;
//    }
//
//    public void setHeadImg(int headImg) {
//        this.headImg = headImg;
//    }
//
//    public String getNeckName() {
//        return neckName;
//    }
//
//    public void setNeckName(String neckName) {
//        this.neckName = neckName;
//    }
//
//    public String getThingType() {
//        return thingType;
//    }
//
//    public void setThingType(String thingType) {
//        this.thingType = thingType;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
