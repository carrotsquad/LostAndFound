package com.yf107.teamwork.lostandfound.utils;

import com.yf107.teamwork.lostandfound.R;

public class SelectTypeUtils {
    public static SelectTypeUtils selectTypeUtils;

    public SelectTypeUtils(){
    }

    public static SelectTypeUtils getInstance(){
        if(selectTypeUtils == null){
            synchronized (SelectTypeUtil.class){
                if(selectTypeUtils == null){
                    selectTypeUtils = new SelectTypeUtils();
                }
            }
        }
        return selectTypeUtils;
    }

    public String getImage(int type){
        if(type == 1){
            return "钥匙";
        }else if(type == 2){
            return "钱包";
        } else if (type == 3) {

            return "证件";
        }else if(type == 4){
            return "U盘";
        }else if(type == 5){
            return "书";
        }else if(type == 6){
            return "文具";
        }else if(type == 7){
            return "雨伞";
        }else if(type == 8){
            return "书包";
        }else if(type == 9){
            return "衣服";
        }else if(type == 10){
            return "首饰";
        }else if(type == 11){
            return "电脑";
        }else if(type == 12){
            return "手机";
        }else if(type == 13){
            return "充电宝";
        }else if(type == 14){
            return "一卡通";
        }else if(type == 15){
            return "充电器";
        }else {
            return "其他";
        }
    }
}
