package com.zhangqianyuan.teamwork.lostandfound.network;

import android.content.Context;

import com.zhangqianyuan.teamwork.lostandfound.bean.PlaceBean;
import com.zhangqianyuan.teamwork.lostandfound.bean.TypeBean;

import java.util.ArrayList;
import java.util.List;

public class AllURI {
    public static final String BaseUrl = "http://111.230.235.15/";

    public static List<String> allTypeBeanList = new ArrayList<>();
    public static List<String> allPlaceBeanList = new ArrayList<>();
    public static List<String> allTypeImgsList = new ArrayList<>();

    public static String getUserPhoto(String jsessionid,String photo){
       return BaseUrl+"?"+"JSESSIONID="+jsessionid+"&"+"name="+photo;
    }
}
