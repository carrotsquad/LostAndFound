package com.zhangqianyuan.teamwork.lostandfound.bean;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class UploadItemBean {
    private String  typeImgId;
    private String  typeText;

    public UploadItemBean(String typeImgId, String typeText) {
        this.typeImgId = typeImgId;
        this.typeText = typeText;
    }

    public String getTypeImgId() {
        return typeImgId;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeImgId(String typeImgId) {
        this.typeImgId = typeImgId;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }
}
