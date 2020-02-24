package com.yf107.teamwork.lostandfound.utils;

import com.yf107.teamwork.lostandfound.R;

public class SelectTypeUtil {
    public static SelectTypeUtil selectTypeUtil;

    public SelectTypeUtil(){
    }

    public static SelectTypeUtil getInstance(){
        if(selectTypeUtil == null){
            synchronized (SelectTypeUtil.class){
                if(selectTypeUtil == null){
                    selectTypeUtil = new SelectTypeUtil();
                }
            }
        }
        return selectTypeUtil;
    }

    public int getImage(int type){
        if(type == 1){
            return R.drawable.type_yaoshi;
        }else if(type == 2){
            return R.drawable.type_qianbao;
        } else if (type == 3) {

            return R.drawable.type_zhengjian;
        }else if(type == 4){
            return R.drawable.type_upan;
        }else if(type == 5){
            return R.drawable.type_shu;
        }else if(type == 6){
            return R.drawable.type_wenju;
        }else if(type == 7){
            return R.drawable.type_yusan;
        }else if(type == 8){
            return R.drawable.type_shubao;
        }else if(type == 9){
            return R.drawable.type_yifu;
        }else if(type == 10){
            return R.drawable.type_shoushi;
        }else if(type == 11){
            return R.drawable.type_diannao;
        }else if(type == 12){
            return R.drawable.type_shouji;
        }else if(type == 13){
            return R.drawable.type_chongdianbao;
        }else if(type == 14){
            return R.drawable.type_yikatong;
        }else if(type == 15){
            return R.drawable.type_chongdianqi;
        }else {
            return R.drawable.type_qita;
        }
    }
}
