package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UploadItemBean {
    private int  typeImgId;
    private String  typeText;

    public UploadItemBean(int typeImgId, String typeText) {
        this.typeImgId = typeImgId;
        this.typeText = typeText;
    }

    public int getTypeImgId() {
        return typeImgId;
    }

    public void setTypeImgId(int typeImgId) {
        this.typeImgId = typeImgId;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}
