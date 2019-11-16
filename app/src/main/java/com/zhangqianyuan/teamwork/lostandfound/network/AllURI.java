package com.zhangqianyuan.teamwork.lostandfound.network;

import java.util.ArrayList;
import java.util.List;

public class AllURI {
    public static final String BaseUrl = "http://47.102.206.17:8083";

    //我们的官网
    public static final String OurWebsite = BaseUrl+"passlove/index.html";

    public static List<String> allTypeBeanList = new ArrayList<>();
    public static List<String> allPlaceBeanList = new ArrayList<>();
    public static List<String> allTypeImgsList = new ArrayList<>();
    //小标签list
    public static List<String> allTypeLittleImgsList = new ArrayList<>();

    //得到用户头像图片
    public static String getUserPhoto(String JSESSION, String imgName){
        return BaseUrl+"passlove/img/user?" + "JSESSIONID="+JSESSION +"&name="+imgName;
    }

    //得到类型的图片
    public static String getTypePhoto(String JSESSION, String imgName){
        return BaseUrl+"passlove/img/losttype?" + "JSESSIONID="+JSESSION +"&name="+imgName;
    }

    //得到类型的小标签
    public static String getTypeLittlePhoto(String JSESSION, String imgName){
        return BaseUrl+"passlove/img/losttype_char?" + "JSESSIONID="+JSESSION +"&name="+imgName;
    }

    //得到失物招领东西的图片
    public static String getLostThingsPhoto(String JSESSION, String imgName){
        return BaseUrl+"passlove/img/thelost?" + "JSESSIONID="+JSESSION +"&name="+imgName;
    }
}
