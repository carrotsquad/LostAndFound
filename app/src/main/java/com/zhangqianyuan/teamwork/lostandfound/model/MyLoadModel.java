package com.zhangqianyuan.teamwork.lostandfound.model;

/**
 * @author zhoudada
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyLoadModel extends  BaseModel implements IMyUploadModel{

    public MyLoadModel(){
        super();
    }


    @Override
    public void getMyLoadData() {
        api.getMyLoadData();
    }
}
